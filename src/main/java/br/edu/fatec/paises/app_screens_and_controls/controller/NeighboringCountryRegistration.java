package br.edu.fatec.paises.app_screens_and_controls.controller;

import br.edu.fatec.paises.app_screens_and_controls.implementar.PanelSettings;
import br.edu.fatec.paises.app_screens_and_controls.screens.NeighboringCountryRegistrationScreen;
import br.edu.fatec.paises.app_screens_and_controls.screens.components_anotation.ComponentMethod;
import br.edu.fatec.paises.enums.AppText;
import br.edu.fatec.paises.models.Country;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static br.edu.fatec.paises.Main.COUNTRY_DAO;

public class NeighboringCountryRegistration extends NeighboringCountryRegistrationScreen implements PanelSettings {
    private final List<Country> neighbors = new ArrayList<>();
    private final List<JComponent> components = List.of(lblTitle, lblCountry, lblNeighbor, lblInfo, cmbCountry,
            cmbNeighbor, btnSelected, btnRegister, btnMenu);
    private Object comboBoxPaisSelected;

    public NeighboringCountryRegistration() {
        updateCombs();
        cmbCountry.addActionListener(e -> changeCountry());
        btnRegister.addActionListener(e -> addSelectedCountries());
        btnSelected.addActionListener(e -> selectNeighbor());
        btnMenu.addActionListener(e -> backMenu(btnMenu));
        lblTitle.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 20));
    }

    @ComponentMethod
    public List<JComponent> listComponents() {
        return components;
    }

    public void backMenu(JButton button) {
        if (neighbors.isEmpty() || confirmBackMenu(button.getText())) menuScreen(button);
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
        lblInfo.setText(AppText.LBL_INFO_COUNTRY_SELECTED.getString() + selectedCountry);
    }

    protected void checkEmptyNeighborCmb() {
        if (cmbNeighbor.getItemCount() == 0) {
            cmbNeighbor.addItem(AppText.LBL_INFO_LIST_EMPTY.getString());
            cmbNeighbor.setEnabled(false);
            btnSelected.setEnabled(false);
        }
    }

    public void changeCountry() {
        if (neighbors.isEmpty()) {
            updateNeighborCmbChangeCountry();
            lblInfo.setText("");
            return;
        }
        if (confirmChanges()) {
            neighbors.clear();
            lblInfo.setText(AppText.LBL_INFO_LIST_EMPTY.getString());
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

    public boolean confirmChanges() {
        int confirm = JOptionPane.showInternalOptionDialog(
                null, AppText.MSG_OPTION_CHANGE_COUNTRY_SELECTED.getString() +
                        "\n" + AppText.MSG_OPTION_WARNING_CLEAR_LIST.getString()
                , AppText.LBL_OPTION_TITLE_COUNTRY_CHANGE.getString()
                , JOptionPane.YES_NO_OPTION
                , JOptionPane.QUESTION_MESSAGE, null
                , new Object[]{AppText.BTN_OPTION_CHANGE.getString(), AppText.BTN_CANCEL.getString()}
                , AppText.BTN_OPTION_CHANGE.getString());
        return confirm == JOptionPane.YES_OPTION;
    }

    protected void addSelectedCountries() {
        if (neighbors.isEmpty()) {
            lblInfo.setText(AppText.LBL_INFO_LIST_EMPTY.getString());
            return;
        }
        List<Country> neighborsSelected = new ArrayList<>(neighbors);
        Country countrySelected = COUNTRY_DAO.findByName(Objects.requireNonNull(cmbCountry.getSelectedItem()).toString());
        for (Country frontierCountry : neighborsSelected) frontierCountry.setFrontier(List.of(countrySelected));
        countrySelected.setFrontier(neighborsSelected);
        lblInfo.setText(AppText.LBL_INFO_NEIGHBOR_SUCCESS_TEXT.getString());
        neighbors.clear();
    }
}