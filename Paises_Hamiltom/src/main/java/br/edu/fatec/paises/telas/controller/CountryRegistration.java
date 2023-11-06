package br.edu.fatec.paises.telas.controller;

import br.edu.fatec.paises.enums.CountryRegistrationText;
import br.edu.fatec.paises.implementar.PanelSettings;
import br.edu.fatec.paises.models.Country;
import br.edu.fatec.paises.telas.CountryRegistrationScreen;

import javax.swing.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static br.edu.fatec.paises.Main.COUNTRY_DAO;

public class CountryRegistration {

    public void backMenu(CountryRegistrationScreen countryRegistrationScreen, PanelSettings panel) {
        String name = countryRegistrationScreen.getTxtName().getText();
        String capital = countryRegistrationScreen.getTxtCapital().getText();
        String dimension = Double.parseDouble(countryRegistrationScreen.getTxtDimension().getValue().toString()) == 0 ? "" : countryRegistrationScreen.getTxtDimension().getValue().toString();
        boolean fieldsEmpty = Stream.of(name, capital, dimension).allMatch(String::isEmpty);
        if (!fieldsEmpty && panel.confirmBackMenu()) panel.backMenu(countryRegistrationScreen.getBtnMenu());
        if (fieldsEmpty) panel.backMenu(countryRegistrationScreen.getBtnMenu());
    }

    public void clearFields(CountryRegistrationScreen countryRegistrationScreen) {
        countryRegistrationScreen.getTxtName().setText("");
        countryRegistrationScreen.getTxtCapital().setText("");
        countryRegistrationScreen.getTxtDimension().setValue(0);
        countryRegistrationScreen.getLblSuccess().setText("");
    }

    public void addCountry(CountryRegistrationScreen countryRegistrationScreen) {
        String name = countryRegistrationScreen.getTxtName().getText();
        String capital = countryRegistrationScreen.getTxtCapital().getText();
        double dimension = Double.parseDouble(countryRegistrationScreen.getTxtDimension().getValue().toString());
        Country newCountry = new Country(name, capital, dimension);
        if (checkCreateEditCountry(countryRegistrationScreen, COUNTRY_DAO.getCountries(), newCountry)) return;
        COUNTRY_DAO.addCountry(newCountry);
        clearFields(countryRegistrationScreen);
        labelSaveCountry(countryRegistrationScreen, name);
    }

    public static boolean checkCreateEditCountry(CountryRegistrationScreen countryRegistrationScreen, List<Country> countries, Country newCountry){
        return Stream.of(checkFields(countryRegistrationScreen.getLblSuccess(), newCountry)
        , countryExists(countryRegistrationScreen.getLblSuccess(), countries, newCountry))
                .anyMatch(Boolean::booleanValue) || !confirmCreateEditCountry(countryRegistrationScreen.getLblSuccess(), newCountry);
    }

    public static boolean confirmCreateEditCountry(JLabel lblSuccess, Country country){
        lblSuccess.setText("");
        String name = country.getName();
        String capital = country.getCapital();
        double dimension = country.getDimension();
        String[] options = {CountryRegistrationText.BTN_SAVE.getString(), CountryRegistrationText.BTN_CANCEL.getString()};
        int confirm = JOptionPane.showInternalOptionDialog(
                null, CountryRegistrationText.LBL_NAME.getString() + name +
                        "\n" + CountryRegistrationText.LBL_CAPITAL.getString() + capital +
                        "\n" + CountryRegistrationText.LBL_DIMENSION.getString() + dimension,
                CountryRegistrationText.LBL_TITLE_PANE_CONFIRM.getString(), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);
        return confirm == JOptionPane.YES_OPTION;
    }

    public void labelSaveCountry(CountryRegistrationScreen countryRegistrationScreen, String name) {
        countryRegistrationScreen.getLblSuccess().setText(String.format(CountryRegistrationText.LBL_SUCCESS_TEXT.getString(), name));
    }

    public static boolean checkFields(JLabel lblSuccess, Country newCountry){
        String countrySize = newCountry.getDimension() == 0 ? "" : String.valueOf(newCountry.getDimension());
        Map<Boolean, String> map = new LinkedHashMap<>();
        map.put(Stream.of(newCountry.getName(), newCountry.getCapital()).anyMatch(s -> s.length() < 3 || s.length() > 20), CountryRegistrationText.LBL_ERROR_INVALID_FIELD_SIZE.getString());
        map.put(Stream.of(newCountry.getName(), newCountry.getCapital()).anyMatch(s -> s.matches(".*\\d.*")), CountryRegistrationText.LBL_ERROR_INVALID_FIELD_NUMBER.getString());
        map.put(Stream.of(newCountry.getName(), newCountry.getCapital(), countrySize).anyMatch(String::isEmpty), CountryRegistrationText.LBL_ERROR_EMPTY_FIELD.getString());
        map.entrySet().stream()
                .filter(Map.Entry::getKey)
                .findFirst().ifPresent(e -> lblSuccess.setText(e.getValue()));
        return map.entrySet().stream().anyMatch(Map.Entry::getKey);
    }

    public static boolean countryExists(JLabel lblSuccess, List<Country> countries, Country newCountry) {
        Map<Boolean, String> map = Map.of(countries.contains(newCountry)
                , CountryRegistrationText.LBL_ERROR_EXISTING.getString());
        map.entrySet().stream()
                .filter(Map.Entry::getKey)
                .findFirst().ifPresent(e -> lblSuccess.setText(e.getValue()));
        return map.entrySet().stream().anyMatch(Map.Entry::getKey);
    }
}