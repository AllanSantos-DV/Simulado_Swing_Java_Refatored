package br.edu.fatec.paises.app_screens_and_controls.controller;

import br.edu.fatec.paises.app_screens_and_controls.implementar.PanelSettings;
import br.edu.fatec.paises.app_screens_and_controls.screens.CountryManagerScreen;
import br.edu.fatec.paises.app_screens_and_controls.screens.components_anotation.ComponentMethod;
import br.edu.fatec.paises.enums.AppText;
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

    public CountryManager() {
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        initCmbCountry();
        btnDeleteCountry.addActionListener(e -> deleteCountry());
        btnDeleteNeighbor.addActionListener(e -> deleteNeighbor());
        cmbCountry.addActionListener(e -> initCmbNeighbor());
        btnEditCountry.addActionListener(e -> editCountry());
        btnMenuManager.addActionListener(e -> menuScreen(btnMenuManager));
    }

    @ComponentMethod
    public List<JComponent> getComponentList() {
        return componentList;
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
        lblDeleteNeighbor.setText(String.format(AppText.LBL_INFO_SUCCESS_COUNTRY_DELETED.getString(), countryName));
    }

    protected boolean listCountriesEmpty(String nameCountry) {
        boolean cancelDelete = cancelCountryDelete(nameCountry, AppText.MSG_OPTION_CONFIRM_COUNTRY_DELETE.getString());
        boolean listSizeOne = COUNTRY_DAO.findAll().size() == 1;
        if (cancelDelete || !listSizeOne) return cancelDelete;
        JOptionPane.showMessageDialog(null,
                AppText.MSG_OPTION_LIST_COUNTRY_EMPTY.getString() +
                        "\n" + AppText.MSG_OPTION_BACKING_MENU.getString(),
                AppText.LBL_OPTION_TITLE_WARNING.getString(),
                JOptionPane.WARNING_MESSAGE);
        menuScreen(btnDeleteCountry);
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
            return List.of(AppText.LBL_INFO_LIST_EMPTY.getString());
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
        changeScreen(btnEditCountry, AppText.LBL_TITLE_MANAGER_COUNTRY.getString(), countryRegistration.mountScreen());
        countryRegistration.configCountryEditScreen(countryEdit);
    }


    public void deleteNeighbor() {
        Country countrySelected = COUNTRY_DAO.findByName(Objects.requireNonNull(cmbCountry.getSelectedItem()).toString());
        Country neighborCountrySelected = COUNTRY_DAO.findByName(Objects.requireNonNull(cmbNeighborCountry.getSelectedItem()).toString());
        if (cancelCountryDelete(neighborCountrySelected.getName(),
                AppText.MSG_OPTION_CONFIRM_NEIGHBOR_DELETE.getString())) return;
        countrySelected.getFrontier().remove(neighborCountrySelected);
        neighborCountrySelected.getFrontier().remove(countrySelected);
        initCmbNeighbor();
        lblDeleteNeighbor.setText(String.format(AppText.LBL_INFO_SUCCESS_NEIGHBOR_DELETED.getString(), neighborCountrySelected.getName()));
    }

    public boolean cancelCountryDelete(String countryName, String neighborOrCountry) {
        String[] options = {AppText.BTN_OPTION_DELETE.getString(), AppText.BTN_CANCEL.getString()};
        int confirm = JOptionPane.showInternalOptionDialog(
                null, String.format(neighborOrCountry, countryName),
                AppText.LBL_OPTION_TITLE_CONFIRM_COUNTRY_DELETE.getString(), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);
        return confirm != JOptionPane.YES_OPTION;
    }
}