package br.edu.fatec.paises.app_screens_and_controls.screens;

import br.edu.fatec.paises.app_screens_and_controls.screens.components_anotation.ComponentMethod;
import br.edu.fatec.paises.enums.MenuText;
import br.edu.fatec.paises.app_screens_and_controls.implementar.PanelSettings;
import br.edu.fatec.paises.app_screens_and_controls.controller.Menu;

import javax.swing.*;
import java.awt.*;
import java.util.stream.Stream;

public class MenuScreen extends Menu implements PanelSettings {
    private final JLabel lblTitle = new JLabel();
    private final JButton btnRegisterCountry = new JButton(MenuText.BTN_NEW_COUNTRY.getString());
    private final JButton btnRegisterNeighborCountry = new JButton(MenuText.BTN_REGISTER_NEIGHBOR.getString());
    private final JButton btnEditCountry = new JButton(MenuText.BTN_MANAGE_COUNTRY.getString());
    private final JButton btnListCountries = new JButton(MenuText.BTN_LIST_COUNTRIES.getString());
    private static final int COMPONENTS_WIDTH = 150;
    private static final int COMPONENTS_HEIGHT = 30;

    public MenuScreen() {
        Stream.of(btnRegisterCountry, btnRegisterNeighborCountry, btnEditCountry, btnListCountries)
                .forEach(this::addListener);
    }

    @ComponentMethod
    public JLabel getLblTitle() {
        lblTitle.setText(MenuText.LBL_TITLE.getString());
        lblTitle.setFont(new Font("Arial", Font.BOLD, 25));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setBounds(15, 10, COMPONENTS_WIDTH*3, COMPONENTS_HEIGHT*3);
        return lblTitle;
    }

    @ComponentMethod
    public JButton getBtnRegisterCountry() {
        btnRegisterCountry.setBounds(70, 100, COMPONENTS_WIDTH, COMPONENTS_HEIGHT);
        return btnRegisterCountry;
    }

    @ComponentMethod
    public JButton getBtnRegisterNeighborCountry() {
        btnRegisterNeighborCountry.setBounds(70, 140, COMPONENTS_WIDTH, COMPONENTS_HEIGHT);
        return btnRegisterNeighborCountry;
    }

    @ComponentMethod
    public JButton getBtnEditCountry() {
        btnEditCountry.setBounds(270, 100, COMPONENTS_WIDTH, COMPONENTS_HEIGHT);
        return btnEditCountry;
    }

    @ComponentMethod
    public JButton getBtnListCountries() {
        btnListCountries.setBounds(270, 140, COMPONENTS_WIDTH, COMPONENTS_HEIGHT);
        return btnListCountries;
    }
}