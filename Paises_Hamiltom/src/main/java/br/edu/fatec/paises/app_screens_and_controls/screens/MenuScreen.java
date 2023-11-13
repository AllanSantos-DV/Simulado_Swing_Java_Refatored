package br.edu.fatec.paises.app_screens_and_controls.screens;

import br.edu.fatec.paises.enums.MenuText;

import javax.swing.*;
import java.awt.*;

public class MenuScreen {

    protected final JLabel lblTitle = createLabel(MenuText.LBL_TITLE.getString(), 10, 20, Cursor.DEFAULT_CURSOR);
    protected final JButton btnRegisterCountry = createButton(MenuText.BTN_NEW_COUNTRY.getString(), 70, 100);
    protected final JButton btnRegisterNeighborCountry = createButton(MenuText.BTN_REGISTER_NEIGHBOR.getString(), 70, 140);
    protected final JButton btnEditCountry = createButton(MenuText.BTN_MANAGE_COUNTRY.getString(), 250, 100);
    protected final JButton btnListCountries = createButton(MenuText.BTN_LIST_COUNTRIES.getString(), 250, 140);
    protected final JLabel linkGitHub = createLabel(MenuText.LBL_NAME_GITHUB.getString(), 180, 15, Cursor.HAND_CURSOR);
    protected final JLabel linkLabelDevelopers = createLabel(MenuText.LBL_DEVELOPERS.getString(), 200, 15, Cursor.HAND_CURSOR);
    private static final int COMPONENTS_WIDTH = 150, COMPONENTS_HEIGHT = 30;

    private JButton createButton(String text, int x, int y) {
        JButton button = new JButton(text);
        button.setBounds(x, y, COMPONENTS_WIDTH, COMPONENTS_HEIGHT);
        return button;
    }

    private JLabel createLabel(String text, int y, int sizeFont, int cursor) {
        JLabel label = new JLabel(text);
        label.setBounds(10, y, COMPONENTS_WIDTH * 3, COMPONENTS_HEIGHT);
        label.setFont(new Font("Arial", Font.BOLD, sizeFont));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setCursor(Cursor.getPredefinedCursor(cursor));
        return label;
    }
}