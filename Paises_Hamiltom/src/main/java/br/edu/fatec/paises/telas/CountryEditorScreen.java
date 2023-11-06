package br.edu.fatec.paises.telas;

import br.edu.fatec.paises.components_anotation.ComponentMethod;
import br.edu.fatec.paises.enums.CountryEditorText;
import br.edu.fatec.paises.implementar.PanelSettings;
import br.edu.fatec.paises.telas.controller.CountryEditor;

import javax.swing.*;
import java.awt.*;

public class CountryEditorScreen extends CountryEditor implements PanelSettings {
    private final JLabel lblTitle = new JLabel();
    private final JLabel lblCountrySelected = new JLabel();
    private final JComboBox<String> selectCountryCmb = new JComboBox<>();
    private final JComboBox<String> selectNeighborCountryCmb = new JComboBox<>();
    private final JButton btnDeleteNeighbor = new JButton();
    private final JLabel lblDeleteNeighbor = new JLabel();
    private final JButton btnEditCountry = new JButton();
    private final JButton btnMenu = new JButton();
    private static final int COMPONENTS_WIDTH = 200;
    private static final int COMPONENTS_HEIGHT = 25;

    public CountryEditorScreen() {
        initCmbCountry(this);
        selectCountryCmb.addActionListener(e -> initCmbNeighbor(this));
        btnMenu.addActionListener(e -> backMenu(btnMenu));
        btnEditCountry.addActionListener(e -> editCountry(this));
        btnDeleteNeighbor.addActionListener(e -> deleteNeighbor(this));
    }

    @ComponentMethod
    public JLabel getLblTitle() {
        lblTitle.setText(CountryEditorText.LBL_TITLE.getString());
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setBounds(50, 10, COMPONENTS_WIDTH * 2, COMPONENTS_HEIGHT);
        return lblTitle;
    }

    @ComponentMethod
    public JLabel getLblCountrySelected() {
        lblCountrySelected.setText(CountryEditorText.LBL_SELECT_COUNTRY.getString());
        lblCountrySelected.setHorizontalAlignment(SwingConstants.CENTER);
        lblCountrySelected.setBounds(150, 50, COMPONENTS_WIDTH, COMPONENTS_HEIGHT);
        return lblCountrySelected;
    }

    @ComponentMethod
    public JComboBox<String> getSelectCountryCmb() {
        selectCountryCmb.setBounds(150, 80, COMPONENTS_WIDTH, COMPONENTS_HEIGHT);
        return selectCountryCmb;
    }

    @ComponentMethod
    public JComboBox<String> getSelectNeighborCountryCmb() {
        selectNeighborCountryCmb.setBounds(75, 120, COMPONENTS_WIDTH, COMPONENTS_HEIGHT);
        return selectNeighborCountryCmb;
    }

    @ComponentMethod
    public JButton getBtnDeleteNeighbor() {
        btnDeleteNeighbor.setText(CountryEditorText.BTN_DELETE_NEIGHBOR.getString());
        btnDeleteNeighbor.setBounds(275, 120, COMPONENTS_WIDTH - 50, COMPONENTS_HEIGHT);
        return btnDeleteNeighbor;
    }

    @ComponentMethod
    public JLabel getLblDeleteNeighbor() {
        lblDeleteNeighbor.setHorizontalAlignment(SwingConstants.CENTER);
        lblDeleteNeighbor.setBounds(50, 150, COMPONENTS_WIDTH * 2, COMPONENTS_HEIGHT);
        return lblDeleteNeighbor;
    }

    @ComponentMethod
    public JButton getBtnEditCountry() {
        btnEditCountry.setText(CountryEditorText.BTN_EDIT_COUNTRY.getString());
        btnEditCountry.setBounds(175, 185, COMPONENTS_WIDTH - 50, COMPONENTS_HEIGHT);
        return btnEditCountry;
    }

    @ComponentMethod
    public JButton getBtnMenu() {
        btnMenu.setText(CountryEditorText.BTN_MENU.getString());
        btnMenu.setBounds(200, 225, COMPONENTS_WIDTH / 2, COMPONENTS_HEIGHT);
        return btnMenu;
    }
}