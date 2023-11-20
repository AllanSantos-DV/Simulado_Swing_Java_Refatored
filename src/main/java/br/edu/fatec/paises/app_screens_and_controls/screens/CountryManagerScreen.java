package br.edu.fatec.paises.app_screens_and_controls.screens;

import br.edu.fatec.paises.enums.AppText;

import javax.swing.*;

public class CountryManagerScreen {

    private static final int COMPONENTS_WIDTH = 200, COMPONENTS_HEIGHT = 25;
    protected final JLabel lblTitle = createLabel(AppText.LBL_TITLE_MANAGER_COUNTRY.getString(), 50, 20);
    protected final JLabel lblCountrySelected = createLabel(AppText.LBL_SELECT_COUNTRY.getString(), 50, 50);
    protected final JLabel lblDeleteNeighbor = createLabel("", 50, 150);
    protected final JComboBox<String> cmbCountry = createComboBox(75, 80);
    protected final JComboBox<String> cmbNeighborCountry = createComboBox(75, 120);
    protected final JButton btnDeleteCountry = createButton(AppText.BTN_DELETE_COUNTRY.getString(), 275, 80, COMPONENTS_WIDTH - 50);
    protected final JButton btnDeleteNeighbor = createButton(AppText.BTN_DELETE_NEIGHBOR.getString(), 275, 120, COMPONENTS_WIDTH - 50);
    protected final JButton btnEditCountry = createButton(AppText.BTN_EDIT_COUNTRY.getString(), 175, 180, COMPONENTS_WIDTH - 50);
    protected final JButton btnMenuManager = createButton(AppText.BTN_MENU.getString(), 200, 220, COMPONENTS_WIDTH / 2);

    public JLabel createLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBounds(x, y, COMPONENTS_WIDTH * 2, COMPONENTS_HEIGHT);
        return label;
    }

    public JComboBox<String> createComboBox(int x, int y) {
        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setBounds(x, y, COMPONENTS_WIDTH, COMPONENTS_HEIGHT);
        return comboBox;
    }

    public JButton createButton(String text, int x, int y, int width) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, COMPONENTS_HEIGHT);
        return button;
    }
}