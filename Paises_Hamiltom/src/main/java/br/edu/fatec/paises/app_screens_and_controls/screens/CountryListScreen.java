package br.edu.fatec.paises.app_screens_and_controls.screens;

import br.edu.fatec.paises.app_screens_and_controls.screens.components_anotation.ComponentMethod;
import br.edu.fatec.paises.enums.CountryListText;
import br.edu.fatec.paises.app_screens_and_controls.implementar.PanelSettings;
import br.edu.fatec.paises.app_screens_and_controls.controller.CountryList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.stream.Stream;

public class CountryListScreen extends CountryList implements PanelSettings {
    private final JLabel lblTitle = new JLabel();
    private final JLabel lblCountriesCount = new JLabel();
    private final JLabel lblList = new JLabel();
    private final JButton btnListNameCountry = new JButton();
    private final JButton btnListDimensionCountry = new JButton();
    private final JButton btnListCapitalCountry = new JButton();
    private final DefaultTableModel tableModel = new DefaultTableModel();
    private final JTable table = new JTable(tableModel);
    private final JScrollPane scrollPane = new JScrollPane(table);
    private final JButton btnMenu = new JButton();
    private static final int COMPONENTS_WIDTH = 200;
    private static final int COMPONENTS_HEIGHT = 24;

    public CountryListScreen() {
        countriesList(this);
        Stream.of(btnListNameCountry, btnListDimensionCountry, btnListCapitalCountry)
                .forEach(btn -> btn.addActionListener(e -> listCountriesOrdered(this, btn.getText())));
        btnMenu.addActionListener(e -> backMenu(btnMenu));
    }

    @ComponentMethod
    public JLabel getLblTitle() {
        lblTitle.setText(CountryListText.LBL_TITLE.getString());
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setBounds(50, 10, COMPONENTS_WIDTH*2, COMPONENTS_HEIGHT);
        return lblTitle;
    }

    @ComponentMethod
    public JLabel getLblCountriesCount() {
        lblCountriesCount.setHorizontalAlignment(SwingConstants.CENTER);
        lblCountriesCount.setBounds(50, 58, COMPONENTS_WIDTH*2, COMPONENTS_HEIGHT);
        return lblCountriesCount;
    }

    @ComponentMethod
    public JLabel getLblList() {
        lblList.setText(CountryListText.LBL_ORDERING_TITLE.getString());
        lblList.setHorizontalAlignment(SwingConstants.CENTER);
        lblList.setBounds(50, 38, COMPONENTS_WIDTH / 3, COMPONENTS_HEIGHT);
        return lblList;
    }

    @ComponentMethod
    public JButton getBtnListNameCountry() {
        btnListNameCountry.setText(CountryListText.BTN_ORDERING_NAME.getString());
        btnListNameCountry.setBounds(120, 38, COMPONENTS_WIDTH / 2, COMPONENTS_HEIGHT);
        return btnListNameCountry;
    }

    @ComponentMethod
    public JButton getBtnListCapitalCountry(){
        btnListCapitalCountry.setText(CountryListText.BTN_ORDERING_CAPITAL.getString());
        btnListCapitalCountry.setBounds(220, 38, COMPONENTS_WIDTH/2, COMPONENTS_HEIGHT);
        return btnListCapitalCountry;
    }

    @ComponentMethod
    public JButton getBtnListDimensionCountry() {
        btnListDimensionCountry.setText(CountryListText.BTN_ORDERING_DIMENSION.getString());
        btnListDimensionCountry.setBounds(320, 38, COMPONENTS_WIDTH / 2, COMPONENTS_HEIGHT);
        return btnListDimensionCountry;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JTable getTable() {
        table.setEnabled(false);
        return table;
    }

    @ComponentMethod
    public JScrollPane getTxtAreaCountriesScroll() {
        scrollPane.setBounds(25, 77, COMPONENTS_WIDTH*2+35, COMPONENTS_HEIGHT * 6);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        return scrollPane;
    }

    @ComponentMethod
    public JButton getBtnMenu() {
        btnMenu.setText(CountryListText.BTN_MENU.getString());
        btnMenu.setBounds(200, 225, COMPONENTS_WIDTH / 2, COMPONENTS_HEIGHT);
        return btnMenu;
    }
}