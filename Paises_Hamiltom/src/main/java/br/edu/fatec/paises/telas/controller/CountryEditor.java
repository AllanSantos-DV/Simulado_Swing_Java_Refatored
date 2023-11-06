package br.edu.fatec.paises.telas.controller;

import br.edu.fatec.paises.enums.CountryEditorText;
import br.edu.fatec.paises.models.Country;
import br.edu.fatec.paises.telas.CountryRegistrationScreen;
import br.edu.fatec.paises.telas.CountryEditorScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

import static br.edu.fatec.paises.Main.MENU;
import static br.edu.fatec.paises.Main.COUNTRY_DAO;

public class CountryEditor {

    protected void initCmbCountry(CountryEditorScreen countryEditorScreen) {
        countryEditorScreen.getSelectCountryCmb().removeAllItems();
        for (Country country : COUNTRY_DAO.getCountries()) countryEditorScreen.getSelectCountryCmb().addItem(country.getName());
        initCmbNeighbor(countryEditorScreen);
    }

    protected void initCmbNeighbor(CountryEditorScreen countryEditorScreen) {
        countryEditorScreen.getSelectNeighborCountryCmb().removeAllItems();
        Country countrySelected = COUNTRY_DAO.getCountry(Objects.requireNonNull(countryEditorScreen.getSelectCountryCmb().getSelectedItem()).toString());
        for (String neighbor : getNeighbors(countrySelected, countryEditorScreen)) countryEditorScreen.getSelectNeighborCountryCmb().addItem(neighbor);
    }

    protected List<String> getNeighbors(Country countrySelected, CountryEditorScreen countryEditorScreen) {
        if (checkNeighbors(countrySelected, countryEditorScreen)) {
            return List.of(CountryEditorText.LBL_ERROR_EMPTY_NEIGHBORS.getString());
        }
        List<String> vizinhos = new ArrayList<>();
        for (Country vizinho : countrySelected.getFrontier()) vizinhos.add(vizinho.getName());
        return vizinhos;
    }

    protected boolean checkNeighbors(Country countrySelected, CountryEditorScreen countryEditorScreen) {
        List<Component> components = Arrays.asList(countryEditorScreen.getSelectNeighborCountryCmb(), countryEditorScreen.getBtnDeleteNeighbor());
        boolean emptyFrontier = countrySelected.getFrontier().isEmpty();
        components.forEach(component -> component.setEnabled(!emptyFrontier));
        return emptyFrontier;
    }

    public void editCountry(CountryEditorScreen countryEditorScreen) {
        Country country = COUNTRY_DAO.getCountry(Objects.requireNonNull(countryEditorScreen.getSelectCountryCmb().getSelectedItem()).toString());
        CountryRegistrationScreen countryRegistrationScreen = new CountryRegistrationScreen();
        Menu menu = new Menu();
        updateFrame(countryRegistrationScreen, menu, countryEditorScreen);
        editCountryPanel(countryRegistrationScreen, country);
    }

    public void updateFrame(CountryRegistrationScreen countryRegistrationScreen, Menu menu, CountryEditorScreen countryEditorScreen) {
        menu.closeScreen(countryEditorScreen.getBtnEditCountry());
        menu.appScreen(countryEditorScreen.getBtnEditCountry().getText(), countryRegistrationScreen.mountScreen());
    }

    public void editCountryPanel(CountryRegistrationScreen countryRegistrationScreen, Country country) {
        JButton btnSave = countryRegistrationScreen.getBtnSave();
        ActionListener listenerSaveCountry = Arrays.stream(btnSave.getActionListeners()).iterator().next();
        countryRegistrationScreen.getLblTitle().setHorizontalAlignment(SwingConstants.CENTER);
        countryRegistrationScreen.getLblTitle().setText(CountryEditorText.LBL_TITLE.getString());
        countryRegistrationScreen.getTxtName().setText(country.getName());
        countryRegistrationScreen.getTxtCapital().setText(country.getCapital());
        countryRegistrationScreen.getTxtDimension().setValue(country.getDimension());
        btnSave.setText(CountryEditorText.BTN_EDIT_COUNTRY.getString());
        btnSave.removeActionListener(listenerSaveCountry);
        ActionListener listenerEditCountry = e -> saveEditedCountry(btnSave, listenerSaveCountry, countryRegistrationScreen, country);
        btnSave.addActionListener(listenerEditCountry);
    }

    public void saveEditedCountry(JButton btnSave, ActionListener listener, CountryRegistrationScreen countryRegistrationScreen, Country country) {
        String name = countryRegistrationScreen.getTxtName().getText();
        String capital = countryRegistrationScreen.getTxtCapital().getText();
        double dimension = Double.parseDouble(countryRegistrationScreen.getTxtDimension().getValue().toString());
        Country newCountry = new Country(name, capital, dimension);
        List<Country> countries = new ArrayList<>(COUNTRY_DAO.getCountries());
        countries.remove(country);
        if (CountryRegistration.checkCreateEditCountry(countryRegistrationScreen, countries, newCountry)) return;
        COUNTRY_DAO.editCountry(country.getName(), newCountry);
        btnSave.removeActionListener(countryRegistrationScreen.getBtnSave().getActionListeners()[0]);
        btnSave.addActionListener(listener);
        countryEditorScreen(btnSave);
        JOptionPane.showMessageDialog(null, String.format(CountryEditorText.LBL_SUCCESS_TEXT.getString(), country.getName()));
    }

    public void countryEditorScreen(JButton btnSave) {
        CountryEditorScreen newInstance = new CountryEditorScreen();
        MENU.closeScreen(btnSave);
        MENU.appScreen(btnSave.getText(), newInstance.mountScreen());
    }

    public void deleteNeighbor(CountryEditorScreen countryEditorScreen) {
        Country countrySelected = COUNTRY_DAO.getCountry(Objects.requireNonNull(countryEditorScreen.getSelectCountryCmb().getSelectedItem()).toString());
        Country neighborCountrySelected = COUNTRY_DAO.getCountry(Objects.requireNonNull(countryEditorScreen.getSelectNeighborCountryCmb().getSelectedItem()).toString());
        if (!confirmCountryDelete(neighborCountrySelected)) return;
        countrySelected.getFrontier().remove(neighborCountrySelected);
        neighborCountrySelected.getFrontier().remove(countrySelected);
        initCmbNeighbor(countryEditorScreen);
        countryEditorScreen.getLblDeleteNeighbor().setText(String.format(CountryEditorText.LBL_DELETE_NEIGHBOR.getString(), neighborCountrySelected.getName()));
    }

    public boolean confirmCountryDelete(Country country){
        String[] options = {CountryEditorText.BTN_DELETE.getString(), CountryEditorText.BTN_CANCEL.getString()};
        int confirm = JOptionPane.showInternalOptionDialog(
                null, String.format(CountryEditorText.LBL_CONFIRM_DELETE.getString(), country.getName()),
                CountryEditorText.LBL_TITLE_PANE_CONFIRM.getString(), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);
        return confirm == JOptionPane.YES_OPTION;
    }
}