package br.edu.fatec.paises.app_screens_and_controls.controller;

import br.edu.fatec.paises.enums.CountryManagerText;
import br.edu.fatec.paises.enums.MenuText;
import br.edu.fatec.paises.models.Country;
import br.edu.fatec.paises.app_screens_and_controls.screens.CountryManagerScreen;
import br.edu.fatec.paises.app_screens_and_controls.screens.CountryRegistrationScreen;
import br.edu.fatec.paises.app_screens_and_controls.screens.MenuScreen;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static br.edu.fatec.paises.Main.COUNTRY_DAO;
import static br.edu.fatec.paises.Main.MENU;

public class CountryManager {

    private Country countryEdit = new Country("","",0);

    protected void initCmbCountry(CountryManagerScreen cMS) {
        cMS.getSelectCountryCmb().removeAllItems();
        for (Country country : COUNTRY_DAO.findAll()) cMS.getSelectCountryCmb().addItem(country.getName());
        initCmbNeighbor(cMS);
    }

    protected void deleteCountry(CountryManagerScreen cMS) {
        String countryName = Objects.requireNonNull(cMS.getSelectCountryCmb().getSelectedItem()).toString();
        if (listCountriesEmpty(cMS, countryName)) return;
        Country removedCountry = COUNTRY_DAO.findByName(countryName);
        if (!removedCountry.getFrontier().isEmpty())
            for (Country country : removedCountry.getFrontier()) country.getFrontier().remove(removedCountry);
        COUNTRY_DAO.deleteByName(countryName);
        initCmbCountry(cMS);
        cMS.getLblDeleteNeighbor().setText(String.format(CountryManagerText.LBL_DELETE_COUNTRY.getString(), countryName));
    }

    protected boolean listCountriesEmpty(CountryManagerScreen cMS, String nameCountry){
        boolean cancelDelete = cancelCountryDelete(nameCountry);
        boolean listSizeOne = COUNTRY_DAO.findAll().size() == 1;
        if (cancelDelete || !listSizeOne) return cancelDelete;
        JOptionPane.showMessageDialog(null,
                CountryManagerText.MSG_OPTION_PANE_LIST_COUNTRY_EMPTY.getString() +
                "\n" + CountryManagerText.MSG_OPTION_PANE_BACK_MENU.getString(),
                CountryManagerText.LBL_OPTION_PANE_TITLE.getString(),
                JOptionPane.WARNING_MESSAGE);
        MENU.closeScreen(cMS.getBtnDeleteCountry());
        MENU.appScreen(cMS.getBtnDeleteCountry().getText(), new MenuScreen().mountScreen());
        return false;
    }

    protected void initCmbNeighbor(CountryManagerScreen cMS) {
        if (cMS.getSelectCountryCmb().getSelectedItem() == null) return;
        cMS.getSelectNeighborCountryCmb().removeAllItems();
        Country countrySelected = COUNTRY_DAO.findByName(Objects.requireNonNull(cMS.getSelectCountryCmb().getSelectedItem()).toString());
        for (String neighbor : getNeighbors(countrySelected, cMS)) cMS.getSelectNeighborCountryCmb().addItem(neighbor);
    }

    protected List<String> getNeighbors(Country countrySelected, CountryManagerScreen cMS) {
        if (checkNeighbors(countrySelected, cMS))
            return List.of(CountryManagerText.LBL_ERROR_EMPTY_NEIGHBORS.getString());
        List<String> neighbors = new ArrayList<>();
        for (Country neighbor : countrySelected.getFrontier()) neighbors.add(neighbor.getName());
        return neighbors;
    }

    protected boolean checkNeighbors(Country countrySelected, CountryManagerScreen cMS) {
        List<Component> components = Arrays.asList(cMS.getSelectNeighborCountryCmb(), cMS.getBtnDeleteNeighbor());
        boolean emptyFrontier = countrySelected.getFrontier().isEmpty();
        components.forEach(component -> component.setEnabled(!emptyFrontier));
        return emptyFrontier;
    }

    public void editCountry(CountryManagerScreen cMS) {
        countryEdit = COUNTRY_DAO.findByName(Objects.requireNonNull(cMS.getSelectCountryCmb().getSelectedItem()).toString());
        CountryRegistrationScreen cRS = new CountryRegistrationScreen();
        Stream.of(cRS.getBtnSave(), cRS.getBtnMenu(), cRS.getBtnEdit(), cRS.getBtnCancel())
                        .forEach(btn -> btn.setVisible(!btn.isVisible()));
        countryEditorScreen(cMS.getBtnEditCountry(), cMS.getBtnEditCountry().getText(), cRS.mountScreen());
        editCountryPanel(cRS);
    }

    public void editCountryPanel(CountryRegistrationScreen cRS) {
        cRS.getLblTitle().setHorizontalAlignment(SwingConstants.CENTER);
        cRS.getLblTitle().setText(CountryManagerText.LBL_TITLE.getString());
        cRS.getTxtName().setText(countryEdit.getName());
        cRS.getTxtCapital().setText(countryEdit.getCapital());
        cRS.getTxtDimension().setValue(countryEdit.getDimension());
        cRS.getBtnEdit().addActionListener(e -> saveEditedCountry(cRS.getBtnEdit(), cRS));
        cRS.getBtnCancel().addActionListener(e ->
                countryEditorScreen(cRS.getBtnCancel()
                        , MenuText.BTN_MANAGE_COUNTRY.getString()
                        , new CountryManagerScreen().mountScreen()));
    }

    public void saveEditedCountry(JButton btnEditCountry, CountryRegistrationScreen cRS) {
        double dimension = Double.parseDouble(cRS.getTxtDimension().getValue().toString());
        Country newCountry = new Country(cRS.getTxtName().getText(), cRS.getTxtCapital().getText(), dimension);
        List<Country> countries = new ArrayList<>(COUNTRY_DAO.findAll());
        countries.remove(countryEdit);
        if (CountryRegistration.checkCreateEditCountry(cRS, countries, newCountry)) return;
        COUNTRY_DAO.editCountry(countryEdit.getName(), newCountry);
        countryEditorScreen(btnEditCountry, btnEditCountry.getText(), new CountryManagerScreen().mountScreen());
        JOptionPane.showMessageDialog(null, String.format(CountryManagerText.LBL_SUCCESS_TEXT.getString(), countryEdit.getName()));
    }

    public void countryEditorScreen(JButton btnSaveCancelEditCountry, String screenTitle, JPanel screen) {
        MENU.closeScreen(btnSaveCancelEditCountry);
        MENU.appScreen(screenTitle, screen);
    }

    public void deleteNeighbor(CountryManagerScreen cMS) {
        Country countrySelected = COUNTRY_DAO.findByName(Objects.requireNonNull(cMS.getSelectCountryCmb().getSelectedItem()).toString());
        Country neighborCountrySelected = COUNTRY_DAO.findByName(Objects.requireNonNull(cMS.getSelectNeighborCountryCmb().getSelectedItem()).toString());
        if (cancelCountryDelete(neighborCountrySelected.getName())) return;
        countrySelected.getFrontier().remove(neighborCountrySelected);
        neighborCountrySelected.getFrontier().remove(countrySelected);
        initCmbNeighbor(cMS);
        cMS.getLblDeleteNeighbor().setText(String.format(CountryManagerText.LBL_DELETE_COUNTRY.getString(), neighborCountrySelected.getName()));
    }

    public boolean cancelCountryDelete(String countryName){
        String[] options = {CountryManagerText.BTN_OPTION_PANE_DELETE.getString(), CountryManagerText.BTN_OPTION_PANE_CANCEL.getString()};
        int confirm = JOptionPane.showInternalOptionDialog(
                null, String.format(CountryManagerText.MSG_OPTION_PANE_CONFIRM_DELETE.getString(), countryName),
                CountryManagerText.LBL_OPTION_PANE_TITLE_PANE_CONFIRM.getString(), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);
        return confirm != JOptionPane.YES_OPTION;
    }
}