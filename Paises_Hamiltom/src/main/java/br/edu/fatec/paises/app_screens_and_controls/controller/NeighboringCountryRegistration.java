package br.edu.fatec.paises.app_screens_and_controls.controller;

import br.edu.fatec.paises.app_screens_and_controls.implementar.PanelSettings;
import br.edu.fatec.paises.app_screens_and_controls.screens.NeighboringCountryRegistrationScreen;
import br.edu.fatec.paises.app_screens_and_controls.screens.components_anotation.ComponentMethod;
import br.edu.fatec.paises.enums.NeighborRegistrationText;
import br.edu.fatec.paises.models.Country;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static br.edu.fatec.paises.Main.COUNTRY_DAO;

public class NeighboringCountryRegistration extends NeighboringCountryRegistrationScreen implements PanelSettings {
    private final List<Country> neighbors = new ArrayList<>();
    private Object comboBoxPaisSelected;

    private final List<JComponent> components = List.of(lblTitle, lblCountry, lblNeighbor, lblSuccess, cmbCountry,
            cmbNeighbor, btnSelected, btnRegister, btnMenu);

    @ComponentMethod
    public List<JComponent> listComponents() {
        return components;
    }

    public NeighboringCountryRegistration() {
        updateCombs();
        cmbCountry.addActionListener(e -> changeCountry());
        btnRegister.addActionListener(e -> addSelectedCountries());
        btnSelected.addActionListener(e -> selectNeighbor());
        btnMenu.addActionListener(e -> backMenu(btnMenu));
        lblTitle.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 20));
    }

    public void backMenu(JButton button) {
        if (neighbors.isEmpty() || confirmBackMenu(button.getText())) changeScreen(button);
    }

    protected void updateCombs() {
        cmbCountry.removeAllItems();
        COUNTRY_DAO.findAll().forEach(pais -> cmbCountry.addItem(pais.getName()));
        updateNeighborCmb();
    }

    protected void updateNeighborCmb() {
        cmbNeighbor.removeAllItems();
        String selectedCountryName = Objects.requireNonNull(cmbCountry.getSelectedItem()).toString();
        Country countrySelected = findCountryByName(selectedCountryName);
        List<Country> allCountries = findAllCountries();
        List<Country> filteredCountries = filterCountries(countrySelected, allCountries);
        addCountriesToCmbNeighbors(filteredCountries);
        checkEmptyNeighborCmb();
    }

    private Country findCountryByName(String name) {
        return COUNTRY_DAO.findByName(name);
    }

    private List<Country> findAllCountries() {
        return COUNTRY_DAO.findAll();
    }

    private List<Country> filterCountries(Country countrySelected, List<Country> allCountries) {
        return allCountries.stream()
                .filter(country -> !country.equals(countrySelected)
                        && !neighbors.contains(country)
                        && !countrySelected.getFrontier().contains(country))
                .toList();
    }

    private void addCountriesToCmbNeighbors(List<Country> countries) {
        countries.forEach(country -> cmbNeighbor.addItem(country.getName()));
    }

    protected void selectNeighbor() {
        comboBoxPaisSelected = cmbCountry.getSelectedItem();
        Country selectedNeighbor = COUNTRY_DAO.findByName(Objects.requireNonNull(cmbNeighbor.getSelectedItem()).toString());
        neighbors.add(selectedNeighbor);
        lblCountrySelected(selectedNeighbor.getName());
        updateNeighborCmb();
        checkEmptyNeighborCmb();
    }

    protected void lblCountrySelected(String selectedCountry) {
        lblSuccess.setText(NeighborRegistrationText.LBL_COUNTRY_SELECTED.getString() + selectedCountry);
    }

    protected void checkEmptyNeighborCmb() {
        if(cmbNeighbor.getItemCount() == 0) {
            cmbNeighbor.addItem(NeighborRegistrationText.LBL_ERROR_EMPTY_FIELD.getString());
            cmbNeighbor.setEnabled(false);
            btnSelected.setEnabled(false);
        }
    }

    public void changeCountry() {
        if (neighbors.isEmpty()){
            updateNeighborCmbChangeCountry();
            lblSuccess.setText("");
            return;
        }
        if (confirmChanges()){
            neighbors.clear();
            lblSuccess.setText(NeighborRegistrationText.LBL_CLEAR_LIST_CHANGE.getString());
            updateNeighborCmbChangeCountry();
            return;
        }
        cmbCountry.setSelectedItem(comboBoxPaisSelected);
    }

    protected void updateNeighborCmbChangeCountry() {
        updateNeighborCmb();
        btnSelected.setEnabled(true);
        cmbNeighbor.setEnabled(true);
    }

    public boolean confirmChanges(){
        int confirm = JOptionPane.showInternalOptionDialog(
                null, NeighborRegistrationText.MSG_OPTION_PANE_CHANGE_COMBOBOX.getString() +
                        "\n" + NeighborRegistrationText.MSG_OPTION_PANE_WARNING.getString()
                , NeighborRegistrationText.LBL_OPTION_PANE_TITLE_CHANGE.getString()
                , JOptionPane.YES_NO_OPTION
                , JOptionPane.QUESTION_MESSAGE, null
                , new Object[]{NeighborRegistrationText.BTN_OPTION_PANE_CHANGE.getString(), NeighborRegistrationText.BTN_OPTION_PANE_CANCEL.getString()}
                , NeighborRegistrationText.BTN_OPTION_PANE_CHANGE.getString());
        return confirm == JOptionPane.YES_OPTION;
    }

    protected void addSelectedCountries() {
        if (neighbors.isEmpty()) {
            lblSuccess.setText(NeighborRegistrationText.LBL_ERROR_EMPTY_FIELD.getString());
            return;
        }
        List<Country> neighborsSelected = new ArrayList<>(neighbors);
        Country countrySelected = COUNTRY_DAO.findByName(Objects.requireNonNull(cmbCountry.getSelectedItem()).toString());
        for (Country frontierCountry : neighborsSelected) frontierCountry.setFrontier(List.of(countrySelected));
        countrySelected.setFrontier(neighborsSelected);
        lblSuccess.setText(NeighborRegistrationText.LBL_SUCCESS_TEXT.getString());
        neighbors.clear();
    }
}