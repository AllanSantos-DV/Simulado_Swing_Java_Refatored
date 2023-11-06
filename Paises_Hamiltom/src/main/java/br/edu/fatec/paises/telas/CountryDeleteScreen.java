package br.edu.fatec.paises.telas;

import br.edu.fatec.paises.components_anotation.ComponentMethod;
import br.edu.fatec.paises.enums.CountryDeleteText;
import br.edu.fatec.paises.implementar.PanelSettings;
import br.edu.fatec.paises.telas.controller.CountryDelete;

import javax.swing.*;
import java.awt.*;

public class CountryDeleteScreen extends CountryDelete implements PanelSettings {
    private final JLabel lblTitle = new JLabel();
    private final JLabel lblSelectCountry = new JLabel();
    private final JComboBox<String> cmbCountrySelected = new JComboBox<>();
    private final JButton btnDeleteCountry = new JButton();
    private final JLabel lblDeleteCountry = new JLabel();
    private final JButton btnMenu = new JButton();
    private static final int COMPONENTS_WIDTH = 200;
    private static final int COMPONENTS_HEIGHT = 25;

    public CountryDeleteScreen() {
        initCmb(this);
        btnDeleteCountry.addActionListener(e -> deleteCountry(this));
        btnMenu.addActionListener(e -> backMenu(btnMenu));
    }

    @ComponentMethod
    public JLabel getLblTitle() {
        lblTitle.setText(CountryDeleteText.LBL_TITLE.getString());
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setBounds(50, 10, COMPONENTS_WIDTH*2, COMPONENTS_HEIGHT);
        return lblTitle;
    }

    @ComponentMethod
    public JLabel getLblSelectCountry() {
        lblSelectCountry.setText(CountryDeleteText.LBL_SELECT_COUNTRY.getString());
        lblSelectCountry.setHorizontalAlignment(SwingConstants.CENTER);
        lblSelectCountry.setBounds(150, 50, COMPONENTS_WIDTH, COMPONENTS_HEIGHT);
        return lblSelectCountry;
    }

    @ComponentMethod
    public JComboBox<String> getCmbCountrySelected() {
        cmbCountrySelected.setBounds(150, 80, COMPONENTS_WIDTH, COMPONENTS_HEIGHT);
        return cmbCountrySelected;
    }

    @ComponentMethod
    public JButton getBtnDeleteCountry() {
        btnDeleteCountry.setText(CountryDeleteText.BTN_DELETE_COUNTRY.getString());
        btnDeleteCountry.setBounds(175, 120, COMPONENTS_WIDTH - 50, COMPONENTS_HEIGHT);
        return btnDeleteCountry;
    }

    @ComponentMethod
    public JLabel getLblDeleteCountry() {
        lblDeleteCountry.setHorizontalAlignment(SwingConstants.CENTER);
        lblDeleteCountry.setBounds(50, 160, COMPONENTS_WIDTH*2, COMPONENTS_HEIGHT);
        return lblDeleteCountry;
    }

    @ComponentMethod
    public JButton getBtnMenu() {
        btnMenu.setText(CountryDeleteText.BTN_MENU.getString());
        btnMenu.setBounds(200, 200, COMPONENTS_WIDTH / 2, COMPONENTS_HEIGHT);
        return btnMenu;
    }
}