package br.edu.fatec.paises.app_screens_and_controls.screens;

import br.edu.fatec.paises.app_screens_and_controls.controller.Menu;
import br.edu.fatec.paises.app_screens_and_controls.implementar.PanelSettings;
import br.edu.fatec.paises.app_screens_and_controls.screens.components_anotation.ComponentMethod;
import br.edu.fatec.paises.enums.MenuText;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.stream.Stream;

import static br.edu.fatec.paises.Main.logger;

public class MenuScreen extends Menu implements PanelSettings {
    private final JLabel lblTitle = new JLabel();
    private final JButton btnRegisterCountry = new JButton(MenuText.BTN_NEW_COUNTRY.getString());
    private final JButton btnRegisterNeighborCountry = new JButton(MenuText.BTN_REGISTER_NEIGHBOR.getString());
    private final JButton btnEditCountry = new JButton(MenuText.BTN_MANAGE_COUNTRY.getString());
    private final JButton btnListCountries = new JButton(MenuText.BTN_LIST_COUNTRIES.getString());
    private final JLabel linkGitHub = new JLabel("<html><a href=\"https://github.com/AllanSantos-DV/Simulado_Swing_Java_Refatored\">GitHub</a></html>");
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
        lblTitle.setBounds(15, 10, COMPONENTS_WIDTH * 3, COMPONENTS_HEIGHT * 3);
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
    public JButton getBtnManageCountry() {
        btnEditCountry.setBounds(250, 100, COMPONENTS_WIDTH, COMPONENTS_HEIGHT);
        return btnEditCountry;
    }

    @ComponentMethod
    public JButton getBtnListCountries() {
        btnListCountries.setBounds(250, 140, COMPONENTS_WIDTH, COMPONENTS_HEIGHT);
        return btnListCountries;
    }

    @ComponentMethod
    public JLabel getLinkGitHub() {
        linkGitHub.setBounds(10, 200, COMPONENTS_WIDTH * 3, COMPONENTS_HEIGHT);
        linkGitHub.setFont(new Font("Arial", Font.BOLD, 20));
        linkGitHub.setHorizontalAlignment(SwingConstants.CENTER);
        linkGitHub.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        linkGitHub.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    Desktop.getDesktop().browse(java.net.URI.create("https://github.com/AllanSantos-DV/Simulado_Swing_Java_Refatored"));
                } catch (IOException e) {
                    logger.error("Error to open GitHub link", e);
                }
            }
        });
        return linkGitHub;
    }
}