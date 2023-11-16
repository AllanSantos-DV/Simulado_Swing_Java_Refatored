package br.edu.fatec.paises.app_screens_and_controls.screens;

import br.edu.fatec.paises.enums.AppText;

import javax.swing.*;
import java.awt.*;

public class MenuScreen {

    private static final int COMPONENTS_WIDTH = 150, COMPONENTS_HEIGHT = 30;
    protected final JComboBox<String> cmbLanguage = createComboBoxLanguage();
    protected final JLabel lblLanguage = creatLabelLanguage(AppText.LBL_CURRENT_LANGUAGE.getString());
    protected final JLabel lblTitle = createLabel(AppText.LBL_TITLE_MENU.getString(), 10, 20, Cursor.DEFAULT_CURSOR);
    protected final JLabel linkGitHub = createLabel(AppText.LBL_NAME_GITHUB.getString(), 180, 15, Cursor.HAND_CURSOR);
    protected final JLabel linkLabelDevelopers = createLabel(AppText.LBL_DEVELOPERS.getString(), 200, 15, Cursor.HAND_CURSOR);
    protected final JButton btnRegisterCountry = createButton(AppText.BTN_NEW_COUNTRY.getString(), 70, 100);
    protected final JButton btnRegisterNeighborCountry = createButton(AppText.BTN_REGISTER_NEIGHBOR.getString(), 70, 140);
    protected final JButton btnEditCountry = createButton(AppText.BTN_MANAGE_COUNTRY.getString(), 250, 100);
    protected final JButton btnListCountries = createButton(AppText.BTN_LIST_COUNTRIES.getString(), 250, 140);

    private JLabel creatLabelLanguage(String text) {
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(SwingConstants.LEFT);
        label.setBounds(375 - (text.length() * 8), 45, 100, COMPONENTS_HEIGHT);
        return label;
    }

    private JLabel createLabel(String text, int y, int sizeFont, int cursor) {
        JLabel label = new JLabel(text);
        label.setBounds(10, y, COMPONENTS_WIDTH * 3, COMPONENTS_HEIGHT);
        label.setFont(new Font("Arial", Font.BOLD, sizeFont));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setCursor(Cursor.getPredefinedCursor(cursor));
        return label;
    }

    private JComboBox<String> createComboBoxLanguage() {
        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setBounds(375, 50, 100, 20);
        return comboBox;
    }

    private JButton createButton(String text, int x, int y) {
        JButton button = new JButton(text);
        button.setBounds(x, y, COMPONENTS_WIDTH, COMPONENTS_HEIGHT);
        return button;
    }
}