package br.edu.fatec.paises.app_screens_and_controls.screens;

import br.edu.fatec.paises.app_screens_and_controls.screens.components_anotation.ComponentMethod;
import br.edu.fatec.paises.enums.CountryManagerText;
import br.edu.fatec.paises.app_screens_and_controls.implementar.PanelSettings;
import br.edu.fatec.paises.app_screens_and_controls.controller.CountryManager;

import javax.swing.*;
import java.awt.*;

public class CountryManagerScreen extends CountryManager implements PanelSettings {
    private final JLabel lblTitle = new JLabel();
    private final JLabel lblCountrySelected = new JLabel();
    private final JComboBox<String> selectCountryCmb = new JComboBox<>();
    private final JComboBox<String> selectNeighborCountryCmb = new JComboBox<>();
    private final JButton btnDeleteCountry = new JButton();
    private final JButton btnDeleteNeighbor = new JButton();
    private final JLabel lblDeleteNeighbor = new JLabel();
    private final JButton btnEditCountry = new JButton();
    private final JButton btnMenu = new JButton();
    private static final int COMPONENTS_WIDTH = 200;
    private static final int COMPONENTS_HEIGHT = 25;

    public CountryManagerScreen() {
        initCmbCountry(this);
        btnDeleteCountry.addActionListener(e -> deleteCountry(this));
        btnDeleteNeighbor.addActionListener(e -> deleteNeighbor(this));
        selectCountryCmb.addActionListener(e -> initCmbNeighbor(this));
        btnEditCountry.addActionListener(e -> editCountry(this));
        btnMenu.addActionListener(e -> backMenu(btnMenu));
    }

    @ComponentMethod
    public JLabel getLblTitle() {
        lblTitle.setText(CountryManagerText.LBL_TITLE.getString());
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setBounds(50, 20, COMPONENTS_WIDTH * 2, COMPONENTS_HEIGHT);
        return lblTitle;
    }

    @ComponentMethod
    public JLabel getLblCountrySelected() {
        lblCountrySelected.setText(CountryManagerText.LBL_SELECT_COUNTRY.getString());
        lblCountrySelected.setHorizontalAlignment(SwingConstants.CENTER);
        lblCountrySelected.setBounds(150, 50, COMPONENTS_WIDTH, COMPONENTS_HEIGHT);
        return lblCountrySelected;
    }

    @ComponentMethod
    public JComboBox<String> getSelectCountryCmb() {
        selectCountryCmb.setBounds(75, 80, COMPONENTS_WIDTH, COMPONENTS_HEIGHT);
        return selectCountryCmb;
    }

    @ComponentMethod
    public JButton getBtnDeleteCountry() {
        btnDeleteCountry.setText(CountryManagerText.BTN_DELETE_COUNTRY.getString());
        btnDeleteCountry.setBounds(275, 80, COMPONENTS_WIDTH - 50, COMPONENTS_HEIGHT);
        return btnDeleteCountry;
    }

    @ComponentMethod
    public JComboBox<String> getSelectNeighborCountryCmb() {
        selectNeighborCountryCmb.setBounds(75, 120, COMPONENTS_WIDTH, COMPONENTS_HEIGHT);
        return selectNeighborCountryCmb;
    }

    @ComponentMethod
    public JButton getBtnDeleteNeighbor() {
        btnDeleteNeighbor.setText(CountryManagerText.BTN_DELETE_NEIGHBOR.getString());
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
        btnEditCountry.setText(CountryManagerText.BTN_EDIT_COUNTRY.getString());
        btnEditCountry.setBounds(175, 180, COMPONENTS_WIDTH - 50, COMPONENTS_HEIGHT);
        return btnEditCountry;
    }

    @ComponentMethod
    public JButton getBtnMenu() {
        btnMenu.setText(CountryManagerText.BTN_MENU.getString());
        btnMenu.setBounds(200, 220, COMPONENTS_WIDTH / 2, COMPONENTS_HEIGHT);
        return btnMenu;
    }
}