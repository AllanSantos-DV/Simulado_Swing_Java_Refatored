package br.edu.fatec.paises.app_screens_and_controls.controller;

import br.edu.fatec.paises.enums.CountryRegistrationText;
import br.edu.fatec.paises.app_screens_and_controls.implementar.PanelSettings;
import br.edu.fatec.paises.models.Country;
import br.edu.fatec.paises.app_screens_and_controls.screens.CountryRegistrationScreen;

import javax.swing.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static br.edu.fatec.paises.Main.COUNTRY_DAO;

public class CountryRegistration {

    public void backMenu(CountryRegistrationScreen cRS, PanelSettings panel) {
        String name = cRS.getTxtName().getText();
        String capital = cRS.getTxtCapital().getText();
        String dimension = Double.parseDouble(cRS.getTxtDimension().getValue().toString()) == 0 ? "" : cRS.getTxtDimension().getValue().toString();
        boolean fieldsEmpty = Stream.of(name, capital, dimension).allMatch(String::isEmpty);
        if (!fieldsEmpty && panel.confirmBackMenu()) panel.backMenu(cRS.getBtnMenu());
        if (fieldsEmpty) panel.backMenu(cRS.getBtnMenu());
    }

    public void clearFields(CountryRegistrationScreen cRS) {
        cRS.getTxtName().setText("");
        cRS.getTxtCapital().setText("");
        cRS.getTxtDimension().setValue(0);
        cRS.getLblSuccess().setText("");
    }

    public void addCountry(CountryRegistrationScreen cRS) {
        String name = cRS.getTxtName().getText();
        String capital = cRS.getTxtCapital().getText();
        double dimension = Double.parseDouble(cRS.getTxtDimension().getValue().toString());
        Country newCountry = new Country(name, capital, dimension);
        if (checkCreateEditCountry(cRS, COUNTRY_DAO.findAll(), newCountry)) return;
        COUNTRY_DAO.save(newCountry);
        clearFields(cRS);
        labelSaveCountry(cRS, name);
    }

    public static boolean checkCreateEditCountry(CountryRegistrationScreen cRS, List<Country> countries, Country newCountry){
        return Stream.of(checkFields(cRS.getLblSuccess(), newCountry)
        , countryExists(cRS.getLblSuccess(), countries, newCountry))
                .anyMatch(Boolean::booleanValue) || !confirmCreateEditCountry(cRS.getLblSuccess(), newCountry);
    }

    public static boolean confirmCreateEditCountry(JLabel lblSuccess, Country country){
        lblSuccess.setText("");
        String name = country.getName();
        String capital = country.getCapital();
        double dimension = country.getDimension();
        String[] options = {CountryRegistrationText.BTN_OPTION_PANE_SAVE.getString(), CountryRegistrationText.BTN_OPTION_PANE_CANCEL.getString()};
        int confirm = JOptionPane.showInternalOptionDialog(
                null, CountryRegistrationText.LBL_NAME.getString() + name +
                        "\n" + CountryRegistrationText.LBL_CAPITAL.getString() + capital +
                        "\n" + CountryRegistrationText.LBL_DIMENSION.getString() + dimension,
                CountryRegistrationText.LBL_OPTION_PANE_TITLE_CONFIRM.getString(), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);
        return confirm == JOptionPane.YES_OPTION;
    }

    public void labelSaveCountry(CountryRegistrationScreen cRS, String name) {
        cRS.getLblSuccess().setText(String.format(CountryRegistrationText.LBL_SUCCESS_TEXT.getString(), name));
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