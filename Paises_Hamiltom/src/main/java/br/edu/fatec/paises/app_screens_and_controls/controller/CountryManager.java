package br.edu.fatec.paises.app_screens_and_controls.controller;

import br.edu.fatec.paises.app_screens_and_controls.implementar.PanelSettings;
import br.edu.fatec.paises.app_screens_and_controls.screens.CountryManagerScreen;
import br.edu.fatec.paises.app_screens_and_controls.screens.components_anotation.ComponentMethod;
import br.edu.fatec.paises.enums.CountryManagerText;
import br.edu.fatec.paises.models.Country;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static br.edu.fatec.paises.Main.COUNTRY_DAO;
public class CountryManager extends CountryManagerScreen implements PanelSettings {

    private final List<JComponent> componentList = List.of(
            lblTitle, lblCountrySelected, lblDeleteNeighbor, cmbCountry, cmbNeighborCountry,
            btnDeleteCountry, btnDeleteNeighbor, btnEditCountry, btnMenuManager
    );

    @ComponentMethod
    public List<JComponent> getComponentList() {
        return componentList;
    }

    public CountryManager() {
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        initCmbCountry();
        btnDeleteCountry.addActionListener(e -> deleteCountry());
        btnDeleteNeighbor.addActionListener(e -> deleteNeighbor());
        cmbCountry.addActionListener(e -> initCmbNeighbor());
        btnEditCountry.addActionListener(e -> editCountry());
        btnMenuManager.addActionListener(e -> changeScreen(btnMenuManager));
    }

    protected void initCmbCountry() {
        cmbCountry.removeAllItems();
        for (Country country : COUNTRY_DAO.findAll()) cmbCountry.addItem(country.getName());
        initCmbNeighbor();
    }

    protected void deleteCountry() {
        String countryName = Objects.requireNonNull(cmbCountry.getSelectedItem()).toString();
        if (listCountriesEmpty(countryName)) return;
        Country removedCountry = COUNTRY_DAO.findByName(countryName);
        if (!removedCountry.getFrontier().isEmpty())
            for (Country country : removedCountry.getFrontier()) country.getFrontier().remove(removedCountry);
        COUNTRY_DAO.deleteByName(countryName);
        initCmbCountry();
        lblDeleteNeighbor.setText(String.format(CountryManagerText.LBL_DELETE_COUNTRY.getString(), countryName));
    }

    protected boolean listCountriesEmpty(String nameCountry) {
        boolean cancelDelete = cancelCountryDelete(nameCountry);
        boolean listSizeOne = COUNTRY_DAO.findAll().size() == 1;
        if (cancelDelete || !listSizeOne) return cancelDelete;
        JOptionPane.showMessageDialog(null,
                CountryManagerText.MSG_OPTION_PANE_LIST_COUNTRY_EMPTY.getString() +
                        "\n" + CountryManagerText.MSG_OPTION_PANE_BACK_MENU.getString(),
                CountryManagerText.LBL_OPTION_PANE_TITLE.getString(),
                JOptionPane.WARNING_MESSAGE);
        changeScreen(btnDeleteCountry);
        return false;
    }

    protected void initCmbNeighbor() {
        if (cmbCountry.getSelectedItem() == null) return;
        cmbNeighborCountry.removeAllItems();
        Country countrySelected = COUNTRY_DAO.findByName(Objects.requireNonNull(cmbCountry.getSelectedItem()).toString());
        for (String neighbor : getNeighbors(countrySelected)) cmbNeighborCountry.addItem(neighbor);
    }

    protected List<String> getNeighbors(Country countrySelected) {
        if (checkNeighbors(countrySelected))
            return List.of(CountryManagerText.LBL_ERROR_EMPTY_NEIGHBORS.getString());
        List<String> neighbors = new ArrayList<>();
        for (Country neighbor : countrySelected.getFrontier()) neighbors.add(neighbor.getName());
        return neighbors;
    }

    protected boolean checkNeighbors(Country countrySelected) {
        List<Component> components = Arrays.asList(cmbNeighborCountry, btnDeleteNeighbor);
        boolean emptyFrontier = countrySelected.getFrontier().isEmpty();
        components.forEach(component -> component.setEnabled(!emptyFrontier));
        return emptyFrontier;
    }

    public void editCountry() {
        Country countryEdit = COUNTRY_DAO.findByName(Objects.requireNonNull(cmbCountry.getSelectedItem()).toString());
        CountryRegistration countryRegistration = new CountryRegistration();
        changeScreen(btnEditCountry, CountryManagerText.LBL_TITLE.getString(), countryRegistration.mountScreen());
        countryRegistration.configCountryEditScreen(countryEdit);
    }


    public void deleteNeighbor() {
        Country countrySelected = COUNTRY_DAO.findByName(Objects.requireNonNull(cmbCountry.getSelectedItem()).toString());
        Country neighborCountrySelected = COUNTRY_DAO.findByName(Objects.requireNonNull(cmbNeighborCountry.getSelectedItem()).toString());
        if (cancelCountryDelete(neighborCountrySelected.getName())) return;
        countrySelected.getFrontier().remove(neighborCountrySelected);
        neighborCountrySelected.getFrontier().remove(countrySelected);
        initCmbNeighbor();
        lblDeleteNeighbor.setText(String.format(CountryManagerText.LBL_DELETE_COUNTRY.getString(), neighborCountrySelected.getName()));
    }

    public boolean cancelCountryDelete(String countryName) {
        String[] options = {CountryManagerText.BTN_OPTION_PANE_DELETE.getString(), CountryManagerText.BTN_OPTION_PANE_CANCEL.getString()};
        int confirm = JOptionPane.showInternalOptionDialog(
                null, String.format(CountryManagerText.MSG_OPTION_PANE_CONFIRM_DELETE.getString(), countryName),
                CountryManagerText.LBL_OPTION_PANE_TITLE_PANE_CONFIRM.getString(), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);
        return confirm != JOptionPane.YES_OPTION;
    }
}