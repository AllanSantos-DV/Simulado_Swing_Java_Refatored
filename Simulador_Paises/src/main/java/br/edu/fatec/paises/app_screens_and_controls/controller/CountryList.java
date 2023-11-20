package br.edu.fatec.paises.app_screens_and_controls.controller;

import br.edu.fatec.paises.app_screens_and_controls.implementar.PanelSettings;
import br.edu.fatec.paises.app_screens_and_controls.screens.CountryListScreen;
import br.edu.fatec.paises.app_screens_and_controls.screens.components_anotation.ComponentMethod;
import br.edu.fatec.paises.enums.AppText;
import br.edu.fatec.paises.models.Country;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static br.edu.fatec.paises.Main.COUNTRY_DAO;

public class CountryList extends CountryListScreen implements PanelSettings {

    private final List<JComponent> components = List.of(
            lblTitle, lblCountriesCount, lblList, btnListNameCountry,
            btnListDimensionCountry, btnListCapitalCountry, btnMenu, scrollPane);

    public CountryList() {
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        countriesList();
        Stream.of(btnListNameCountry, btnListCapitalCountry, btnListDimensionCountry)
                .forEach(btn -> btn.addActionListener(e -> listCountriesOrdered(btn.getText())));
        btnMenu.addActionListener(e -> menuScreen(btnMenu));
    }

    @ComponentMethod
    public List<JComponent> getComponents() {
        return components;
    }

    public void countriesList() {
        int sizeCountriesList = COUNTRY_DAO.findAll().size();
        lblCountriesCount.setText(AppText.LBL_QTD_COUNTRIES.getString() + sizeCountriesList);
        setTableModel(COUNTRY_DAO.findAll());
    }

    public void listCountriesOrdered(String buttonName) {
        List<Country> countries = new ArrayList<>(COUNTRY_DAO.findAll());
        switch (AppText.getEnum(buttonName)) {
            case BTN_DIMENSION -> countries.sort(Comparator.comparing(Country::getDimension));
            case BTN_CAPITAL -> countries.sort(Comparator.comparing(Country::getCapital));
            default -> countries.sort(Comparator.comparing(Country::getName));
        }
        setTableModel(countries);
    }

    public void setTableModel(List<Country> countries) {
        int sizeColumn = 12;
        getEditedTableModel();
        for (Country country : countries) {
            StringBuilder neighbor = new StringBuilder();
            if (country.getFrontier().isEmpty())
                tableModel
                        .addRow(new Object[]{country.getName()
                                , country.getCapital()
                                , country.getDimension()
                                , AppText.MSG_LIST_NEIGHBOR_EMPTY.getString()});
            else {
                for (Country neighborCountry : country.getFrontier())
                    neighbor.append(neighborCountry.getName()).append(", ");
                tableModel.addRow(new Object[]{country.getName(), country.getCapital(), country.getDimension(), neighbor});
            }
            if (neighbor.length() > sizeColumn) sizeColumn = neighbor.length();
        }
        getEditedTable(sizeColumn);
    }

    public void getEditedTable(int sizeColumn) {
        int preferredWidth = 140;
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (int i = 0; i < table.getColumnCount() - 1; i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(preferredWidth);
        }
        table.getColumnModel().getColumn(table.getColumnCount() - 1).setPreferredWidth(sizeColumn * 6);
        table.updateUI();
    }

    public void getEditedTableModel() {
        tableModel.setRowCount(0);
        tableModel.setColumnCount(0);
        tableModel.addColumn(AppText.BTN_NAME.getString());
        tableModel.addColumn(AppText.BTN_CAPITAL.getString());
        tableModel.addColumn(AppText.BTN_DIMENSION.getString());
        tableModel.addColumn(AppText.LBL_NEIGHBORS.getString());
    }
}