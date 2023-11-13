package br.edu.fatec.paises.app_screens_and_controls.controller;

import br.edu.fatec.paises.app_screens_and_controls.implementar.PanelSettings;
import br.edu.fatec.paises.app_screens_and_controls.screens.CountryRegistrationScreen;
import br.edu.fatec.paises.app_screens_and_controls.screens.components_anotation.ComponentMethod;
import br.edu.fatec.paises.enums.CountryManagerText;
import br.edu.fatec.paises.enums.CountryRegistrationText;
import br.edu.fatec.paises.enums.MenuText;
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
            lblSuccess, txtName, txtCapital, txtDimension, btnSave, btnEdit, btnCancel, btnMenu);

    @ComponentMethod
    public List<JComponent> listComponents() {
        return components;
    }
    public CountryRegistration() {
        btnSave.addActionListener(e -> addCountry());
        btnMenu.addActionListener(e -> backMenu());
        Stream.of(btnEdit, btnCancel).forEach(btn -> btn.setVisible(false));
    }

    public void backMenu() {
        String nameCountry = txtName.getText();
        String capitalCountry = txtCapital.getText();
        String dimensionCountry = Double.parseDouble(txtDimension.getValue().toString()) == 0 ? "" : txtDimension.getValue().toString();
        boolean fieldsEmpty = Stream.of(nameCountry, capitalCountry, dimensionCountry).allMatch(String::isEmpty);
        if (fieldsEmpty || confirmBackMenu(btnMenu.getText())) changeScreen(btnMenu);
    }

    public void clearFields() {
        txtName.setText("");
        txtCapital.setText("");
        txtDimension.setValue(0);
        lblSuccess.setText("");
    }

    public Country getCountry() {
        return new Country(txtName.getText(), txtCapital.getText(), Double.parseDouble(txtDimension.getValue().toString()));
    }

    public void addCountry() {
        Country newCountry = getCountry();
        if (checkCreateEditCountry(COUNTRY_DAO.findAll(), newCountry)) return;
        COUNTRY_DAO.save(newCountry);
        clearFields();
        labelSaveCountry(lblSuccess, newCountry.getName());
    }

    public void configCountryEditScreen(Country countryEdit) {
        lblTitle.setText(CountryManagerText.LBL_TITLE.getString());
        txtName.setText(countryEdit.getName());
        txtCapital.setText(countryEdit.getCapital());
        txtDimension.setValue(countryEdit.getDimension());
        Stream.of(btnEdit,btnCancel,btnSave,btnMenu).forEach(btn -> btn.setVisible(!btn.isVisible()));
        btnEdit.addActionListener(e -> saveEditedCountry(countryEdit));
        btnCancel.addActionListener(e -> checkCountryChange(countryEdit));
    }

    public void checkCountryChange(Country countryEdited){
        Country newCountry = new Country(txtName.getText(), txtCapital.getText(), Double.parseDouble(txtDimension.getValue().toString()));
        if (countryEdited.countryEquals(newCountry) || confirmBackMenu(btnCancel.getText())) {
            changeScreen(btnCancel
                    , MenuText.BTN_MANAGE_COUNTRY.getString()
                    , new CountryManager().mountScreen());
        }
    }

    public void saveEditedCountry(Country countryEdit) {
        Country newCountry = getCountry();
        List<Country> countries = getCountries();
        countries.remove(countryEdit);
        if (checkCreateEditCountry(countries, newCountry)) return;
        COUNTRY_DAO.editCountry(countryEdit.getName(), newCountry);
        changeScreen(btnEdit, CountryManagerText.LBL_TITLE.getString(), new CountryManager().mountScreen());
        JOptionPane.showMessageDialog(null, String.format(CountryManagerText.LBL_SUCCESS_TEXT.getString(), countryEdit.getName()));
    }

    public List<Country> getCountries() {
        return new ArrayList<>(COUNTRY_DAO.findAll());
    }

    public boolean checkCreateEditCountry(List<Country> countries, Country newCountry) {
        return Stream.of(checkFields(newCountry)
                        , countryExists(countries, newCountry))
                .anyMatch(Boolean::booleanValue) || !confirmCreateEditCountry(lblSuccess, newCountry);
    }

    public boolean confirmCreateEditCountry(JLabel lblSuccess, Country country) {
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

    public void labelSaveCountry(JLabel lblSuccess, String name) {
        lblSuccess.setText(String.format(CountryRegistrationText.LBL_SUCCESS_TEXT.getString(), name));
    }

    public boolean checkFields(Country newCountry) {
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

    public boolean countryExists(List<Country> countries, Country newCountry) {
        Map<Boolean, String> map = Map.of(countries.contains(newCountry)
                , CountryRegistrationText.LBL_ERROR_EXISTING.getString());
        map.entrySet().stream()
                .filter(Map.Entry::getKey)
                .findFirst().ifPresent(e -> lblSuccess.setText(e.getValue()));
        return map.entrySet().stream().anyMatch(Map.Entry::getKey);
    }
}