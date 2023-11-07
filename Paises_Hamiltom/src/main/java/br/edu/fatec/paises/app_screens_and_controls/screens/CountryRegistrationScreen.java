package br.edu.fatec.paises.app_screens_and_controls.screens;

import br.edu.fatec.paises.app_screens_and_controls.screens.components_anotation.ComponentMethod;
import br.edu.fatec.paises.enums.CountryRegistrationText;
import br.edu.fatec.paises.app_screens_and_controls.implementar.PanelSettings;
import br.edu.fatec.paises.app_screens_and_controls.controller.CountryRegistration;

import javax.swing.*;
import java.awt.*;
import java.util.stream.Stream;

public class CountryRegistrationScreen extends CountryRegistration implements PanelSettings {
    private final JLabel lblTitle = new JLabel();
    private final JLabel lblName = new JLabel();
    private final JTextField txtName = new JTextField();
    private final JLabel lblCapital = new JLabel();
    private final JTextField txtCapital = new JTextField();
    private final JLabel lblDimension = new JLabel();
    private final JSpinner txtDimension = new JSpinner();
    private final JLabel lblSuccess = new JLabel();
    private final JButton btnSave = new JButton();
    private final JButton btnEdit = new JButton();
    private final JButton btnCancel = new JButton();
    private final JButton btnMenu = new JButton();
    private static final int COMPONENTS_WIDTH = 200;
    private static final int COMPONENTS_HEIGHT = 25;
    private static final int LABEL_WIDTH = 90;

    public CountryRegistrationScreen() {
        btnSave.addActionListener(e -> addCountry(this));
        btnMenu.addActionListener(e -> backMenu(this, this));
        Stream.of(btnEdit, btnCancel).forEach(btn -> btn.setVisible(false));
    }

    @ComponentMethod
    public JLabel getLblTitle() {
        lblTitle.setText(CountryRegistrationText.LBL_TITLE_REGISTER.getString());
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setBounds(50, 10, COMPONENTS_WIDTH * 2, COMPONENTS_HEIGHT);
        return lblTitle;
    }

    @ComponentMethod
    public JLabel getLblName() {
        lblName.setText(CountryRegistrationText.LBL_NAME.getString());
        lblName.setHorizontalAlignment(SwingConstants.RIGHT);
        lblName.setBounds(50, 50, LABEL_WIDTH, COMPONENTS_HEIGHT);
        return lblName;
    }

    @ComponentMethod
    public JTextField getTxtName() {
        txtName.setBounds(150, 50, COMPONENTS_WIDTH, COMPONENTS_HEIGHT);
        return txtName;
    }

    @ComponentMethod
    public JLabel getLblCapital() {
        lblCapital.setText(CountryRegistrationText.LBL_CAPITAL.getString());
        lblCapital.setHorizontalAlignment(SwingConstants.RIGHT);
        lblCapital.setBounds(50, 90, LABEL_WIDTH, COMPONENTS_HEIGHT);
        return lblCapital;
    }

    @ComponentMethod
    public JTextField getTxtCapital() {
        txtCapital.setBounds(150, 90, COMPONENTS_WIDTH, COMPONENTS_HEIGHT);
        return txtCapital;
    }

    @ComponentMethod
    public JLabel getLblDimension() {
        lblDimension.setText(CountryRegistrationText.LBL_DIMENSION.getString());
        lblDimension.setHorizontalAlignment(SwingConstants.RIGHT);
        lblDimension.setBounds(50, 130, LABEL_WIDTH, COMPONENTS_HEIGHT);
        return lblDimension;
    }

    @ComponentMethod
    public JSpinner getTxtDimension() {
        txtDimension.setBounds(150, 130, COMPONENTS_WIDTH, COMPONENTS_HEIGHT);
        return txtDimension;
    }

    @ComponentMethod
    public JLabel getLblSuccess() {
        lblSuccess.setHorizontalAlignment(SwingConstants.CENTER);
        lblSuccess.setBounds(50, 170, COMPONENTS_WIDTH * 2, COMPONENTS_HEIGHT);
        return lblSuccess;
    }

    @ComponentMethod
    public JButton getBtnSave() {
        btnSave.setText(CountryRegistrationText.BTN_SAVE.getString());
        btnSave.setBounds(125, 210, COMPONENTS_WIDTH / 2, COMPONENTS_HEIGHT);
        return btnSave;
    }

    @ComponentMethod
    public JButton getBtnEdit() {
        btnEdit.setText(CountryRegistrationText.BTN_EDIT_COUNTRY.getString());
        btnEdit.setBounds(125, 210, COMPONENTS_WIDTH / 2, COMPONENTS_HEIGHT);
        return btnEdit;
    }

    @ComponentMethod
    public JButton getBtnMenu() {
        btnMenu.setText(CountryRegistrationText.BTN_MENU.getString());
        btnMenu.setBounds(275, 210, COMPONENTS_WIDTH / 2, COMPONENTS_HEIGHT);
        return btnMenu;
    }

    @ComponentMethod
    public JButton getBtnCancel() {
        btnCancel.setText(CountryRegistrationText.BTN_CANCEL_EDIT.getString());
        btnCancel.setBounds(275, 210, COMPONENTS_WIDTH / 2, COMPONENTS_HEIGHT);
        return btnCancel;
    }
}