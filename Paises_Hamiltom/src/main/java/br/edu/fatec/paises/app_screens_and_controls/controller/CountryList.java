package br.edu.fatec.paises.app_screens_and_controls.controller;

import br.edu.fatec.paises.enums.CountryListText;
import br.edu.fatec.paises.models.Country;
import br.edu.fatec.paises.app_screens_and_controls.screens.CountryListScreen;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static br.edu.fatec.paises.Main.COUNTRY_DAO;

public class CountryList {

    public void countriesList(CountryListScreen cLS) {
        int sizeCountriesList = COUNTRY_DAO.findAll().size();
        cLS.getLblCountriesCount().setText(CountryListText.LBL_QTD_COUNTRIES.getString() + sizeCountriesList);
        setTableModel(cLS, COUNTRY_DAO.findAll());
    }

    public void listCountriesOrdered(CountryListScreen cLS, String buttonName) {
        List<Country> countries = new ArrayList<>(COUNTRY_DAO.findAll());
        switch (CountryListText.getEnum(buttonName)) {
            case BTN_ORDERING_DIMENSION -> countries.sort(Comparator.comparing(Country::getDimension));
            case BTN_ORDERING_CAPITAL -> countries.sort(Comparator.comparing(Country::getCapital));
            default -> countries.sort(Comparator.comparing(Country::getName));
        }
        setTableModel(cLS, countries);
    }

    public void setTableModel(CountryListScreen cLS, List<Country> countries) {
        int sizeColumn = 12;
        getEditedTableModel(cLS);
        for (Country country : countries){
            StringBuilder neighbor = new StringBuilder();
            if (country.getFrontier().isEmpty())
                cLS.getTableModel()
                        .addRow(new Object[]{country.getName()
                                , country.getCapital()
                                , country.getDimension()
                                , CountryListText.MSG_LIST_NEIGHBOR_EMPTY.getString()});
            else {
                for (Country neighborCountry : country.getFrontier()) neighbor.append(neighborCountry.getName()).append(", ");
                cLS.getTableModel().addRow(new Object[]{country.getName(), country.getCapital(), country.getDimension(), neighbor});
            }
            if(neighbor.length()>sizeColumn) sizeColumn = neighbor.length();
        }
        getEditedTable(cLS, sizeColumn);
    }

    public void getEditedTable(CountryListScreen cLS, int sizeColumn){
        int preferredWidth = 140;
        JTable table = cLS.getTable();
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (int i = 0; i < table.getColumnCount()-1; i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(preferredWidth);
        }
        table.getColumnModel().getColumn(table.getColumnCount()-1).setPreferredWidth(sizeColumn*6);
        table.updateUI();
    }

    public void getEditedTableModel(CountryListScreen cLS) {
        DefaultTableModel tableModel = cLS.getTableModel();
        tableModel.setRowCount(0);
        tableModel.setColumnCount(0);
        tableModel.addColumn(CountryListText.BTN_ORDERING_NAME.getString());
        tableModel.addColumn(CountryListText.BTN_ORDERING_CAPITAL.getString());
        tableModel.addColumn(CountryListText.BTN_ORDERING_DIMENSION.getString());
        tableModel.addColumn(CountryListText.LBL_NEIGHBORS.getString());
    }
}