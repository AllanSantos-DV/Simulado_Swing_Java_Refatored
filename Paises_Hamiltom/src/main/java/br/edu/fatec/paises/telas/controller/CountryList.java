package br.edu.fatec.paises.telas.controller;

import br.edu.fatec.paises.enums.CountryListText;
import br.edu.fatec.paises.models.Country;
import br.edu.fatec.paises.telas.CountryListScreen;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static br.edu.fatec.paises.Main.COUNTRY_DAO;

public class CountryList {

    public void countriesList(CountryListScreen countryListScreen) {
        int sizeCountriesList = COUNTRY_DAO.getCountries().size();
        countryListScreen.getLblCountriesCount().setText(CountryListText.LBL_QTD_COUNTRIES.getString() + sizeCountriesList);
        setTableModel(countryListScreen, COUNTRY_DAO.getCountries());
    }

    public void listCountriesOrdered(CountryListScreen countryListScreen, String buttonName) {
        List<Country> countries = new ArrayList<>(COUNTRY_DAO.getCountries());
        switch (CountryListText.getEnum(buttonName)) {
            case BTN_DIMENSION -> countries.sort(Comparator.comparing(Country::getDimension));
            case BTN_CAPITAL -> countries.sort(Comparator.comparing(Country::getCapital));
            default -> countries.sort(Comparator.comparing(Country::getName));
        }
        setTableModel(countryListScreen, countries);
    }

    public void setTableModel(CountryListScreen countryListScreen, List<Country> countries) {
        int sizeColumn = 0;
        getEditedTableModel(countryListScreen);
        for (Country country : countries){
            StringBuilder neighbor = new StringBuilder();
            if (country.getFrontier().isEmpty()) countryListScreen.getTableModel().addRow(new Object[]{country.getName(), country.getCapital(), country.getDimension(), "Sem Vizinhos"});
            else {
                for (Country neighborCountry : country.getFrontier()) neighbor.append(neighborCountry.getName()).append(", ");
                countryListScreen.getTableModel().addRow(new Object[]{country.getName(), country.getCapital(), country.getDimension(), neighbor});
            }
            if(neighbor.length()>sizeColumn) sizeColumn = neighbor.length();
        }
        getEditedTable(countryListScreen, sizeColumn);
    }

    public void getEditedTable(CountryListScreen countryListScreen, int sizeColumn){
        int preferredWidth = 140;
        JTable table = countryListScreen.getTable();
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (int i = 0; i < table.getColumnCount()-1; i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(preferredWidth);
        }
        table.getColumnModel().getColumn(table.getColumnCount()-1).setPreferredWidth(sizeColumn*6);
        table.updateUI();
    }

    public void getEditedTableModel(CountryListScreen countryListScreen) {
        DefaultTableModel tableModel = countryListScreen.getTableModel();
        tableModel.setRowCount(0);
        tableModel.setColumnCount(0);
        tableModel.addColumn(CountryListText.BTN_NAME.getString());
        tableModel.addColumn(CountryListText.BTN_CAPITAL.getString());
        tableModel.addColumn(CountryListText.BTN_DIMENSION.getString());
        tableModel.addColumn(CountryListText.LBL_NEIGHBORS.getString());
    }
}