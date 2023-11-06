package br.edu.fatec.paises.telas.controller;

import br.edu.fatec.paises.enums.NeighborRegistrationText;
import br.edu.fatec.paises.implementar.PanelSettings;
import br.edu.fatec.paises.models.Country;
import br.edu.fatec.paises.telas.NeighboringCountryRegistrationScreen;

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

    protected void updateCombs(NeighboringCountryRegistrationScreen neighboringCountryRegistrationScreen) {
        neighboringCountryRegistrationScreen.getCmbCountry().removeAllItems();
        COUNTRY_DAO.getCountries().forEach(pais -> neighboringCountryRegistrationScreen.getCmbCountry().addItem(pais.getName()));
        updateNeighborCmb(neighboringCountryRegistrationScreen);
    }

    protected void updateNeighborCmb(NeighboringCountryRegistrationScreen neighboringCountryRegistrationScreen) {
        neighboringCountryRegistrationScreen.getCmbNeighbor().removeAllItems();
        Country countrySelected = COUNTRY_DAO.getCountry(Objects.requireNonNull(neighboringCountryRegistrationScreen.getCmbCountry().getSelectedItem()).toString());
        COUNTRY_DAO.getCountries().stream()
                .filter(country -> !country.equals(countrySelected)
                        && !neighbors.contains(country)
                        && !countrySelected.getFrontier().contains(country))
                .forEach(country -> neighboringCountryRegistrationScreen.getCmbNeighbor().addItem(country.getName()));
        checkEmptyNeighborCmb(neighboringCountryRegistrationScreen);
    }

    protected void selectNeighbor(NeighboringCountryRegistrationScreen neighboringCountryRegistrationScreen) {
        comboBoxPaisSelected = neighboringCountryRegistrationScreen.getCmbCountry().getSelectedItem();
        Country selectedNeighbor = COUNTRY_DAO.getCountry(Objects.requireNonNull(neighboringCountryRegistrationScreen.getCmbNeighbor().getSelectedItem()).toString());
        neighbors.add(selectedNeighbor);
        lblCountrySelected(neighboringCountryRegistrationScreen, selectedNeighbor.getName());
        updateNeighborCmb(neighboringCountryRegistrationScreen);
        checkEmptyNeighborCmb(neighboringCountryRegistrationScreen);
    }

    protected void lblCountrySelected(NeighboringCountryRegistrationScreen neighboringCountryRegistrationScreen
            , String selectedCountry) {
        neighboringCountryRegistrationScreen.getLblSuccess().setText(NeighborRegistrationText.LBL_COUNTRY_SELECTED.getString() + selectedCountry);
    }

    protected void checkEmptyNeighborCmb(NeighboringCountryRegistrationScreen neighboringCountryRegistrationScreen) {
        if(neighboringCountryRegistrationScreen.getCmbNeighbor().getItemCount() == 0) {
            neighboringCountryRegistrationScreen.getCmbNeighbor().addItem(NeighborRegistrationText.LBL_ERROR_EMPTY_FIELD.getString());
            neighboringCountryRegistrationScreen.getCmbNeighbor().setEnabled(false);
            neighboringCountryRegistrationScreen.getBtnSelected().setEnabled(false);
        }
    }

    public void changeCountry(NeighboringCountryRegistrationScreen neighboringCountryRegistrationScreen){
        if (!neighbors.isEmpty() && confirmChanges()){
            neighbors.clear();
            neighboringCountryRegistrationScreen.getLblSuccess().setText(NeighborRegistrationText.LBL_CLEAR_LIST_CHANGE.getString());
            updateNeighborCmbChangeCountry(neighboringCountryRegistrationScreen);
            return;
        }
        if (neighbors.isEmpty()){
            updateNeighborCmbChangeCountry(neighboringCountryRegistrationScreen);
            return;
        }
        neighboringCountryRegistrationScreen.getCmbCountry().setSelectedItem(comboBoxPaisSelected);
    }

    protected void updateNeighborCmbChangeCountry(NeighboringCountryRegistrationScreen neighboringCountryRegistrationScreen) {
        updateNeighborCmb(neighboringCountryRegistrationScreen);
        neighboringCountryRegistrationScreen.getBtnSelected().setEnabled(true);
        neighboringCountryRegistrationScreen.getCmbNeighbor().setEnabled(true);
    }

    public boolean confirmChanges(){
        int confirm = JOptionPane.showInternalOptionDialog(
                null, NeighborRegistrationText.MSG_CHANGE_COMBOBOX.getString() +
                        "\n" + NeighborRegistrationText.MSG_WARNING.getString()
                , NeighborRegistrationText.LBL_TITLE_CHANGE.getString()
                , JOptionPane.YES_NO_OPTION
                , JOptionPane.QUESTION_MESSAGE, null
                , new Object[]{NeighborRegistrationText.BTN_CHANGE.getString(), NeighborRegistrationText.BTN_CANCEL.getString()}
                , NeighborRegistrationText.BTN_CHANGE.getString());
        return confirm == JOptionPane.YES_OPTION;
    }

    protected void addSelectedCountries(NeighboringCountryRegistrationScreen neighboringCountryRegistrationScreen){
        List<Country> neighborsSelected = new ArrayList<>(neighbors);
        Country countrySelected = COUNTRY_DAO.getCountry(Objects.requireNonNull(neighboringCountryRegistrationScreen.getCmbCountry().getSelectedItem()).toString());
        for (Country frontierCountry : neighborsSelected) frontierCountry.setFrontier(List.of(countrySelected));
        countrySelected.setFrontier(neighborsSelected);
        neighboringCountryRegistrationScreen.getLblSuccess().setText(NeighborRegistrationText.LBL_SUCCESS_TEXT.getString());
        neighbors.clear();
    }
}