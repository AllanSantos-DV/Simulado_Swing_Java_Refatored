package br.edu.fatec.paises.app_screens_and_controls.screens;

import br.edu.fatec.paises.enums.CountryRegistrationText;

import javax.swing.*;
import java.awt.*;

public class CountryRegistrationScreen {

    protected final JLabel lblTitle = createLabel(CountryRegistrationText.LBL_TITLE_REGISTER.getString(), SwingConstants.CENTER, 10, COMPONENTS_WIDTH * 2);
    protected final JLabel lblName = createLabel(CountryRegistrationText.LBL_NAME.getString(), SwingConstants.RIGHT, 50, LABEL_WIDTH);
    protected final JLabel lblCapital = createLabel(CountryRegistrationText.LBL_CAPITAL.getString(), SwingConstants.RIGHT, 90, LABEL_WIDTH);
    protected final JLabel lblDimension = createLabel(CountryRegistrationText.LBL_DIMENSION.getString(), SwingConstants.RIGHT, 130, LABEL_WIDTH);
    protected final JLabel lblSuccess = createLabel("", SwingConstants.CENTER, 170, COMPONENTS_WIDTH * 2);
    protected final JTextField txtName = createTextField(50);
    protected final JTextField txtCapital = createTextField(90);
    protected final JSpinner txtDimension = createSpinner();
    protected final JButton btnSave = createButton(CountryRegistrationText.BTN_SAVE.getString(), 125);
    protected final JButton btnEdit = createButton(CountryRegistrationText.BTN_EDIT_COUNTRY.getString(), 125);
    protected final JButton btnCancel = createButton(CountryRegistrationText.BTN_CANCEL_EDIT.getString(), 275);
    protected final JButton btnMenu = createButton(CountryRegistrationText.BTN_MENU.getString(), 275);
    private final Font fontArial15 = new Font("Arial", Font.BOLD, 15);
    private static final int COMPONENTS_WIDTH = 200, COMPONENTS_HEIGHT = 25, LABEL_WIDTH = 90;

    private JLabel createLabel(String text , int horizontalAlignment, int y, int width) {
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(horizontalAlignment);
        label.setBounds(50, y, width, COMPONENTS_HEIGHT);
        return label;
    }

    private JTextField createTextField(int y) {
        JTextField textField = new JTextField();
        textField.setFont(fontArial15);
        textField.setBounds(150, y, COMPONENTS_WIDTH, COMPONENTS_HEIGHT);
        return textField;
    }

    private JSpinner createSpinner() {
        JSpinner spinner = new JSpinner();
        spinner.setFont(fontArial15);
        spinner.setBounds(150, 130, COMPONENTS_WIDTH, COMPONENTS_HEIGHT);
        return spinner;
    }

    private JButton createButton(String text, int x) {
        JButton button = new JButton(text);
        button.setBounds(x, 210, COMPONENTS_WIDTH / 2, COMPONENTS_HEIGHT);
        button.setFont(fontArial15);
        return button;
    }
}