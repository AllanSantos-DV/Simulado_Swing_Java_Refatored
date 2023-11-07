package br.edu.fatec.paises.app_screens_and_controls.screens;

import br.edu.fatec.paises.app_screens_and_controls.screens.components_anotation.ComponentMethod;
import br.edu.fatec.paises.enums.NeighborRegistrationText;
import br.edu.fatec.paises.app_screens_and_controls.implementar.PanelSettings;
import br.edu.fatec.paises.app_screens_and_controls.controller.NeighboringCountryRegistration;

import javax.swing.*;
import java.awt.*;

public class NeighboringCountryRegistrationScreen extends NeighboringCountryRegistration implements PanelSettings {
    private final JLabel lblTitle = new JLabel();
    private final JLabel lblCountry = new JLabel();
    private final JComboBox<String> cmbCountry = new JComboBox<>();
    private final JLabel lblNeighbor = new JLabel();
    private final JComboBox<String> cmbNeighbor = new JComboBox<>();
    private final JButton btnSelected = new JButton();
    private final JLabel lblSuccess = new JLabel();
    private final JButton btnRegister = new JButton();
    private final JButton btnMenu = new JButton();
    private static final int COMPONENTS_WIDTH = 200;
    private static final int COMPONENTS_HEIGHT = 25;

    public NeighboringCountryRegistrationScreen() {
        updateCombs(this);
        cmbCountry.addActionListener(e -> changeCountry(this));
        btnRegister.addActionListener(e -> addSelectedCountries(this));
        btnSelected.addActionListener(e -> selectNeighbor(this));
        btnMenu.addActionListener(e -> backMenu(btnMenu, this));
    }

    @ComponentMethod
    public JLabel getLblTitle() {
        lblTitle.setText(NeighborRegistrationText.LBL_TITLE_REGISTER.getString());
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setBounds(50, 10, COMPONENTS_WIDTH*2, COMPONENTS_HEIGHT);
        return lblTitle;
    }

    @ComponentMethod
    public JLabel getLblCountry() {
        lblCountry.setText(NeighborRegistrationText.LBL_COUNTRY.getString());
        lblCountry.setHorizontalAlignment(SwingConstants.CENTER);
        lblCountry.setBounds(150, 50, COMPONENTS_WIDTH, COMPONENTS_HEIGHT);
        return lblCountry;
    }

    @ComponentMethod
    public JComboBox<String> getCmbCountry() {
        cmbCountry.setBounds(150, 80, COMPONENTS_WIDTH, COMPONENTS_HEIGHT);
        return cmbCountry;
    }

    @ComponentMethod
    public JLabel getLblNeighbor() {
        lblNeighbor.setText(NeighborRegistrationText.LBL_NEIGHBOR.getString());
        lblNeighbor.setHorizontalAlignment(SwingConstants.CENTER);
        lblNeighbor.setBounds(150, 120, COMPONENTS_WIDTH, COMPONENTS_HEIGHT);
        return lblNeighbor;
    }

    @ComponentMethod
    public JComboBox<String> getCmbNeighbor() {
        cmbNeighbor.setBounds(100, 150, COMPONENTS_WIDTH, COMPONENTS_HEIGHT);
        return cmbNeighbor;
    }

    @ComponentMethod
    public JButton getBtnSelected() {
        btnSelected.setText(NeighborRegistrationText.BTN_SELECT.getString());
        btnSelected.setBounds(300, 150, COMPONENTS_WIDTH / 2, COMPONENTS_HEIGHT);
        return btnSelected;
    }

    @ComponentMethod
    public JLabel getLblSuccess() {
        lblSuccess.setHorizontalAlignment(SwingConstants.CENTER);
        lblSuccess.setBounds(50, 175, COMPONENTS_WIDTH*2, COMPONENTS_HEIGHT);
        return lblSuccess;
    }

    @ComponentMethod
    public JButton getBtnRegister() {
        btnRegister.setText(NeighborRegistrationText.BTN_REGISTER.getString());
        btnRegister.setBounds(100, 210, COMPONENTS_WIDTH / 2, COMPONENTS_HEIGHT);
        return btnRegister;
    }

    @ComponentMethod
    public JButton getBtnMenu() {
        btnMenu.setText(NeighborRegistrationText.BTN_MENU.getString());
        btnMenu.setBounds(300, 210, COMPONENTS_WIDTH / 2, COMPONENTS_HEIGHT);
        return btnMenu;
    }
}