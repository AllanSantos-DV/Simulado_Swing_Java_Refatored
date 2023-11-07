package br.edu.fatec.paises.app_screens_and_controls.controller;

import br.edu.fatec.paises.enums.NeighborRegistrationText;
import br.edu.fatec.paises.app_screens_and_controls.implementar.PanelSettings;
import br.edu.fatec.paises.models.Country;
import br.edu.fatec.paises.app_screens_and_controls.screens.NeighboringCountryRegistrationScreen;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static br.edu.fatec.paises.Main.COUNTRY_DAO;

public class NeighboringCountryRegistration {
    private final List<Country> neighbors = new ArrayList<>();
    private Object comboBoxPaisSelected;

    public void backMenu(JButton button, PanelSettings panel) {
        if (!neighbors.isEmpty() && panel.confirmBackMenu()) panel.backMenu(button);
        if (neighbors.isEmpty()) panel.backMenu(button);
    }

    protected void updateCombs(NeighboringCountryRegistrationScreen nCRS) {
        nCRS.getCmbCountry().removeAllItems();
        COUNTRY_DAO.findAll().forEach(pais -> nCRS.getCmbCountry().addItem(pais.getName()));
        updateNeighborCmb(nCRS);
    }

    protected void updateNeighborCmb(NeighboringCountryRegistrationScreen nCRS) {
        nCRS.getCmbNeighbor().removeAllItems();
        Country countrySelected = COUNTRY_DAO.findByName(Objects.requireNonNull(nCRS.getCmbCountry().getSelectedItem()).toString());
        COUNTRY_DAO.findAll().stream()
                .filter(country -> !country.equals(countrySelected)
                        && !neighbors.contains(country)
                        && !countrySelected.getFrontier().contains(country))
                .forEach(country -> nCRS.getCmbNeighbor().addItem(country.getName()));
        checkEmptyNeighborCmb(nCRS);
    }

    protected void selectNeighbor(NeighboringCountryRegistrationScreen nCRS) {
        comboBoxPaisSelected = nCRS.getCmbCountry().getSelectedItem();
        Country selectedNeighbor = COUNTRY_DAO.findByName(Objects.requireNonNull(nCRS.getCmbNeighbor().getSelectedItem()).toString());
        neighbors.add(selectedNeighbor);
        lblCountrySelected(nCRS, selectedNeighbor.getName());
        updateNeighborCmb(nCRS);
        checkEmptyNeighborCmb(nCRS);
    }

    protected void lblCountrySelected(NeighboringCountryRegistrationScreen nCRS
            , String selectedCountry) {
        nCRS.getLblSuccess().setText(NeighborRegistrationText.LBL_COUNTRY_SELECTED.getString() + selectedCountry);
    }

    protected void checkEmptyNeighborCmb(NeighboringCountryRegistrationScreen nCRS) {
        if(nCRS.getCmbNeighbor().getItemCount() == 0) {
            nCRS.getCmbNeighbor().addItem(NeighborRegistrationText.LBL_ERROR_EMPTY_FIELD.getString());
            nCRS.getCmbNeighbor().setEnabled(false);
            nCRS.getBtnSelected().setEnabled(false);
        }
    }

    public void changeCountry(NeighboringCountryRegistrationScreen nCRS){
        if (!neighbors.isEmpty() && confirmChanges()){
            neighbors.clear();
            nCRS.getLblSuccess().setText(NeighborRegistrationText.LBL_CLEAR_LIST_CHANGE.getString());
            updateNeighborCmbChangeCountry(nCRS);
            return;
        }
        if (neighbors.isEmpty()){
            updateNeighborCmbChangeCountry(nCRS);
            return;
        }
        nCRS.getCmbCountry().setSelectedItem(comboBoxPaisSelected);
    }

    protected void updateNeighborCmbChangeCountry(NeighboringCountryRegistrationScreen nCRS) {
        updateNeighborCmb(nCRS);
        nCRS.getBtnSelected().setEnabled(true);
        nCRS.getCmbNeighbor().setEnabled(true);
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

    protected void addSelectedCountries(NeighboringCountryRegistrationScreen nCRS){
        if (neighbors.isEmpty()) {
            nCRS.getLblSuccess().setText(NeighborRegistrationText.LBL_ERROR_EMPTY_FIELD.getString());
            return;
        }
        List<Country> neighborsSelected = new ArrayList<>(neighbors);
        Country countrySelected = COUNTRY_DAO.findByName(Objects.requireNonNull(nCRS.getCmbCountry().getSelectedItem()).toString());
        for (Country frontierCountry : neighborsSelected) frontierCountry.setFrontier(List.of(countrySelected));
        countrySelected.setFrontier(neighborsSelected);
        nCRS.getLblSuccess().setText(NeighborRegistrationText.LBL_SUCCESS_TEXT.getString());
        neighbors.clear();
    }
}