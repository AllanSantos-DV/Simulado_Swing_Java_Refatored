package br.edu.fatec.paises.app_screens_and_controls.screens;

import br.edu.fatec.paises.enums.NeighborRegistrationText;

import javax.swing.*;

public class NeighboringCountryRegistrationScreen {

    protected final JLabel lblTitle = creatLabel(NeighborRegistrationText.LBL_TITLE_REGISTER.getString(), 10);
    protected final JLabel lblCountry = creatLabel(NeighborRegistrationText.LBL_COUNTRY.getString(), 50);
    protected final JLabel lblNeighbor = creatLabel(NeighborRegistrationText.LBL_NEIGHBOR.getString(), 120);
    protected final JLabel lblSuccess = creatLabel("", 175);
    protected final JComboBox<String> cmbCountry = creatComboBox(150, 80);
    protected final JComboBox<String> cmbNeighbor = creatComboBox(100, 150);
    protected final JButton btnSelected = creatButton(NeighborRegistrationText.BTN_SELECT.getString(), 300, 150);
    protected final JButton btnRegister = creatButton(NeighborRegistrationText.BTN_REGISTER.getString(), 100, 210);
    protected final JButton btnMenu = creatButton(NeighborRegistrationText.BTN_MENU.getString(), 300, 210);
    private static final int COMPONENTS_WIDTH = 200, COMPONENTS_HEIGHT = 25;

    private JComboBox<String> creatComboBox(int x, int y) {
        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setBounds(x, y, COMPONENTS_WIDTH, COMPONENTS_HEIGHT);
        return comboBox;
    }

    private JButton creatButton(String text, int x, int y) {
        JButton button = new JButton(text);
        button.setBounds(x, y, COMPONENTS_WIDTH/2, COMPONENTS_HEIGHT);
        return button;
    }

    private JLabel creatLabel(String text, int y) {
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBounds(50, y, COMPONENTS_WIDTH*2, COMPONENTS_HEIGHT);
        return label;
    }
}