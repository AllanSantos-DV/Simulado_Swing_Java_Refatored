package br.edu.fatec.paises.app_screens_and_controls.controller;

import br.edu.fatec.paises.app_screens_and_controls.implementar.PanelSettings;
import br.edu.fatec.paises.app_screens_and_controls.screens.CountryRegistrationScreen;
import br.edu.fatec.paises.app_screens_and_controls.screens.components_anotation.ComponentMethod;
import br.edu.fatec.paises.enums.AppText;
import br.edu.fatec.paises.models.Country;

import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static br.edu.fatec.paises.Main.COUNTRY_DAO;

public class CountryRegistration extends CountryRegistrationScreen implements PanelSettings {

    private final List<JComponent> components = java.util.List.of(lblTitle, lblName, lblCapital, lblDimension,
            lblInfo, txtName, txtCapital, txtDimension, btnSave, btnEdit, btnCancel, btnMenu);

    public CountryRegistration() {
        btnSave.addActionListener(e -> addCountry());
        btnMenu.addActionListener(e -> backMenu());
        Stream.of(btnEdit, btnCancel).forEach(btn -> btn.setVisible(false));
    }

    @ComponentMethod
    public List<JComponent> listComponents() {
        return components;
    }

    public void backMenu() {
        String nameCountry = txtName.getText();
        String capitalCountry = txtCapital.getText();
        String dimensionCountry = Double.parseDouble(txtDimension.getValue().toString()) == 0 ? "" : txtDimension.getValue().toString();
        boolean fieldsEmpty = Stream.of(nameCountry, capitalCountry, dimensionCountry).allMatch(String::isEmpty);
        if (fieldsEmpty || confirmBackMenu(btnMenu.getText())) menuScreen(btnMenu);
    }

    public void clearFields() {
        txtName.setText("");
        txtCapital.setText("");
        txtDimension.setValue(0);
        lblInfo.setText("");
    }

    public Country getCountry() {
        String name = txtName.getText().toLowerCase();
        String nameFormatted = name.substring(0, 1).toUpperCase() + name.substring(1);
        String capital = txtCapital.getText().toLowerCase();
        String capitalFormatted = capital.substring(0, 1).toUpperCase() + capital.substring(1);
        return new Country(nameFormatted, capitalFormatted, Double.parseDouble(txtDimension.getValue().toString()));
    }

    public void addCountry() {
        Country newCountry = getCountry();
        if (checkCreateEditCountry(COUNTRY_DAO.findAll(), newCountry)) return;
        COUNTRY_DAO.save(newCountry);
        clearFields();
        labelSaveCountry(lblInfo, newCountry.getName());
    }

    public void configCountryEditScreen(Country countryEdit) {
        lblTitle.setText(AppText.LBL_TITLE_MANAGER_COUNTRY.getString());
        txtName.setText(countryEdit.getName());
        txtCapital.setText(countryEdit.getCapital());
        txtDimension.setValue(countryEdit.getDimension());
        Stream.of(btnEdit, btnCancel, btnSave, btnMenu).forEach(btn -> btn.setVisible(!btn.isVisible()));
        btnEdit.addActionListener(e -> saveEditedCountry(countryEdit));
        btnCancel.addActionListener(e -> checkCountryChange(countryEdit));
    }

    public void checkCountryChange(Country countryEdited) {
        Country newCountry = new Country(txtName.getText(), txtCapital.getText(), Double.parseDouble(txtDimension.getValue().toString()));
        if (countryEdited.countryEquals(newCountry) || confirmBackMenu(btnCancel.getText())) {
            changeScreen(btnCancel
                    , AppText.BTN_MANAGE_COUNTRY.getString()
                    , new CountryManager().mountScreen());
        }
    }

    public void saveEditedCountry(Country countryEdit) {
        Country newCountry = getCountry();
        List<Country> countries = getCountries();
        countries.remove(countryEdit);
        if (checkCreateEditCountry(countries, newCountry)) return;
        COUNTRY_DAO.editCountry(countryEdit.getName(), newCountry);
        changeScreen(btnEdit, AppText.LBL_TITLE_MANAGER_COUNTRY.getString(), new CountryManager().mountScreen());
        JOptionPane.showMessageDialog(null, String.format(AppText.LBL_INFO_SUCCESS_COUNTRY_EDITED.getString(), countryEdit.getName()));
    }

    public List<Country> getCountries() {
        return new ArrayList<>(COUNTRY_DAO.findAll());
    }

    public boolean checkCreateEditCountry(List<Country> countries, Country newCountry) {
        return Stream.of(checkFields(newCountry)
                        , countryExists(countries, newCountry))
                .anyMatch(Boolean::booleanValue) || !confirmCreateEditCountry(lblInfo, newCountry);
    }

    public boolean confirmCreateEditCountry(JLabel lblSuccess, Country country) {
        lblSuccess.setText("");
        String name = country.getName();
        String capital = country.getCapital();
        double dimension = country.getDimension();
        String[] options = {AppText.BTN_OPTION_SAVE.getString(), AppText.BTN_CANCEL.getString()};
        int confirm = JOptionPane.showInternalOptionDialog(
                null, AppText.LBL_COUNTRY_NAME.getString() + name +
                        "\n" + AppText.LBL_COUNTRY_CAPITAL.getString() + capital +
                        "\n" + AppText.LBL_COUNTRY_DIMENSION.getString() + dimension,
                AppText.LBL_OPTION_TITLE_CREATE_COUNTRY_CONFIRM.getString(), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);
        return confirm == JOptionPane.YES_OPTION;
    }

    public void labelSaveCountry(JLabel lblSuccess, String name) {
        lblSuccess.setText(String.format(AppText.LBL_INFO_COUNTRY_SUCCESS_TEXT.getString(), name));
    }

    public boolean checkFields(Country newCountry) {
        String countrySize = newCountry.getDimension() == 0 ? "" : String.valueOf(newCountry.getDimension());
        Map<Boolean, String> map = new LinkedHashMap<>();
        map.put(Stream.of(newCountry.getName(), newCountry.getCapital()).
                anyMatch(s -> s.length() < 3 || s.length() > 20), AppText.LBL_INFO_INVALID_TEXT_SIZE.getString());
        map.put(Stream.of(newCountry.getName(), newCountry.getCapital()).
                anyMatch(s -> s.matches(".*\\d.*")), AppText.LBL_INFO_INVALID_NUMBER_FIELD.getString());
        map.put(Stream.of(newCountry.getName(), newCountry.getCapital(), countrySize).
                anyMatch(String::isEmpty), AppText.LBL_INFO_EMPTY_FIELD.getString());
        map.entrySet().stream()
                .filter(Map.Entry::getKey)
                .findFirst().ifPresent(e -> lblInfo.setText(e.getValue()));
        return map.entrySet().stream().anyMatch(Map.Entry::getKey);
    }

    public boolean countryExists(List<Country> countries, Country newCountry) {
        Map<Boolean, String> map = Map.of(countries.contains(newCountry)
                , AppText.LBL_INFO_COUNTRY_EXISTING.getString());
        map.entrySet().stream()
                .filter(Map.Entry::getKey)
                .findFirst().ifPresent(e -> lblInfo.setText(e.getValue()));
        return map.entrySet().stream().anyMatch(Map.Entry::getKey);
    }
}