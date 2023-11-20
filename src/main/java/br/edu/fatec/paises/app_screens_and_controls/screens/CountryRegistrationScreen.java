package br.edu.fatec.paises.app_screens_and_controls.screens;

import br.edu.fatec.paises.enums.AppText;

import javax.swing.*;

public class CountryRegistrationScreen {

    private static final int COMPONENTS_WIDTH = 200, COMPONENTS_HEIGHT = 25, LABEL_WIDTH = 90;
    protected final JLabel lblTitle = createLabel(AppText.LBL_TITLE_COUNTRY_REGISTRATION.getString(), SwingConstants.CENTER, 50, 10, COMPONENTS_WIDTH * 2);
    protected final JLabel lblName = createLabel(AppText.LBL_COUNTRY_NAME.getString(), SwingConstants.RIGHT, 50, 50, LABEL_WIDTH);
    protected final JLabel lblCapital = createLabel(AppText.LBL_COUNTRY_CAPITAL.getString(), SwingConstants.RIGHT, 50, 90, LABEL_WIDTH);
    protected final JLabel lblDimension = createLabel(AppText.LBL_COUNTRY_DIMENSION.getString(), SwingConstants.RIGHT, 10, 130, 130);
    protected final JLabel lblInfo = createLabel("", SwingConstants.CENTER, 50, 170, COMPONENTS_WIDTH * 2);
    protected final JTextField txtName = createTextField(50);
    protected final JTextField txtCapital = createTextField(90);
    protected final JSpinner txtDimension = createSpinner();
    protected final JButton btnSave = createButton(AppText.BTN_SAVE.getString(), 120);
    protected final JButton btnEdit = createButton(AppText.BTN_EDIT_COUNTRY.getString(), 120);
    protected final JButton btnCancel = createButton(AppText.BTN_CANCEL.getString(), 270);
    protected final JButton btnMenu = createButton(AppText.BTN_MENU.getString(), 270);

    private JLabel createLabel(String text, int horizontalAlignment, int x, int y, int width) {
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(horizontalAlignment);
        label.setBounds(x, y, width, COMPONENTS_HEIGHT);
        return label;
    }

    private JTextField createTextField(int y) {
        JTextField textField = new JTextField();
        textField.setBounds(150, y, COMPONENTS_WIDTH, COMPONENTS_HEIGHT);
        return textField;
    }

    private JSpinner createSpinner() {
        JSpinner spinner = new JSpinner();
        spinner.setBounds(150, 130, COMPONENTS_WIDTH, COMPONENTS_HEIGHT);
        return spinner;
    }

    private JButton createButton(String text, int x) {
        JButton button = new JButton(text);
        button.setBounds(x, 210, 123, COMPONENTS_HEIGHT);
        return button;
    }
}