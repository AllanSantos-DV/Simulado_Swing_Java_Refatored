package br.edu.fatec.paises.app_screens_and_controls.screens;

import br.edu.fatec.paises.enums.AppText;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CountryListScreen {

    private static final int COMPONENTS_WIDTH = 200, COMPONENTS_HEIGHT = 24;
    protected final JLabel lblTitle = createLabel(AppText.LBL_TITLE_COUNTRY_LIST.getString(), 50, 10, COMPONENTS_WIDTH * 2);
    protected final JLabel lblCountriesCount = createLabel(AppText.LBL_QTD_COUNTRIES.getString(), 50, 58, COMPONENTS_WIDTH * 2);
    protected final JLabel lblList = createLabel(AppText.LBL_ORDERING_TITLE.getString(), 50, 38, COMPONENTS_WIDTH / 3);
    protected final JButton btnListNameCountry = createButton(AppText.BTN_NAME.getString(), 120, 38);
    protected final JButton btnListCapitalCountry = createButton(AppText.BTN_CAPITAL.getString(), 220, 38);
    protected final JButton btnListDimensionCountry = createButton(AppText.BTN_DIMENSION.getString(), 320, 38);
    protected final JButton btnMenu = createButton(AppText.BTN_MENU.getString(), 200, 225);
    protected final DefaultTableModel tableModel = new DefaultTableModel();
    protected final JTable table = new JTable(tableModel);
    protected final JScrollPane scrollPane = createScrollPane(table);

    public JLabel createLabel(String text, int x, int y, int width) {
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBounds(x, y, width, COMPONENTS_HEIGHT);
        return label;
    }

    public JButton createButton(String text, int x, int y) {
        JButton button = new JButton(text);
        button.setBounds(x, y, COMPONENTS_WIDTH / 2, COMPONENTS_HEIGHT);
        return button;
    }

    public JScrollPane createScrollPane(JTable table) {
        table.setEnabled(false);
        JScrollPane jScrollPane = new JScrollPane(table);
        jScrollPane.setBounds(25, 77, COMPONENTS_WIDTH * 2 + 35, COMPONENTS_HEIGHT * 6);
        jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        return jScrollPane;
    }
}