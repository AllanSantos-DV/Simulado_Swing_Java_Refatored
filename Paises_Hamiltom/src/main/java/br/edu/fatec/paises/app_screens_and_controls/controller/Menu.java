package br.edu.fatec.paises.app_screens_and_controls.controller;

import br.edu.fatec.paises.app_screens_and_controls.implementar.PanelSettings;
import br.edu.fatec.paises.app_screens_and_controls.screens.MenuScreen;
import br.edu.fatec.paises.app_screens_and_controls.screens.components_anotation.ComponentMethod;
import br.edu.fatec.paises.enums.DevelopersText;
import br.edu.fatec.paises.enums.MenuText;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static br.edu.fatec.paises.Main.COUNTRY_DAO;
import static br.edu.fatec.paises.Main.logger;

public class Menu extends MenuScreen implements PanelSettings {

    private final java.util.List<JComponent> components = List.of(lblTitle, btnRegisterCountry, btnRegisterNeighborCountry,
            btnEditCountry, btnListCountries, linkGitHub, linkLabelDevelopers);

    @ComponentMethod
    public List<JComponent> listComponents() {
        return components;
    }

    public Menu() {
        Stream.of(btnRegisterCountry, btnRegisterNeighborCountry, btnEditCountry, btnListCountries)
                .forEach(this::addListener);
        linkGitHub.addMouseListener(mouseListenerGitHub());
        linkLabelDevelopers.addMouseListener(mouseListenerCredits());
    }
    public JFrame appScreen(String name, JPanel panel) {
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(
                getClass().getClassLoader().getResource("img/Countries.png")));
        JFrame frame = new JFrame(name);
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(panel.getSize());
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setIconImage(icon.getImage());
        frame.setVisible(true);
        return frame;
    }

    public void appScreenDevelopers(String name, JPanel panel) {
        JFrame frame = appScreen(name, panel);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    public void closeScreen(JButton button) {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(button);
        frame.dispose();
    }

    public boolean countriesEmpty(JButton button) {
        if(COUNTRY_DAO.findAll().isEmpty() && !button.getText().equals(MenuText.BTN_NEW_COUNTRY.getString())) {
            String[] options = {MenuText.BTN_NEW_COUNTRY.getString(), MenuText.BTN_OPTION_PANE_MENU.getString()};
            int choice = JOptionPane.showInternalOptionDialog(null,
                    MenuText.MSG_OPTION_PANE_COUNTRIES_EMPTY.getString() + "\n" + MenuText.MSG_OPTION_PANE_NEW_COUNTRY.getString(),
                    MenuText.LBL_TITLE.getString(), JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null,options, options[0]);
            if ((choice == 0)) appScreen(options[choice], initializeScreens(options[choice]).mountScreen());
            else appScreen(options[choice], new Menu().mountScreen());
            return true;
        }
        return false;
    }

    public void addListener(JButton button) {
        button.addActionListener(e -> {
            closeScreen(button);
            if(countriesEmpty(button)) return;
            appScreen(button.getText(), initializeScreens(button.getText()).mountScreen() );
        });
    }

    public PanelSettings initializeScreens(String buttonText) {
        return switch (MenuText.getEnum(buttonText)) {
            case BTN_NEW_COUNTRY -> new CountryRegistration();
            case BTN_LIST_COUNTRIES -> new CountryList();
            case BTN_REGISTER_NEIGHBOR -> new NeighboringCountryRegistration();
            case BTN_MANAGE_COUNTRY -> new CountryManager();
            default -> new Menu();
        };
    }

    public MouseAdapter mouseListenerGitHub() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                try {
                    Desktop.getDesktop().browse(URI.create(MenuText.LINK_GITHUB.getString()));
                } catch (IOException e) {
                    logger.error(MenuText.MSG_ERROR.getString(), e);
                }
            }
        };
    }

    public MouseAdapter mouseListenerCredits() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                    appScreenDevelopers(DevelopersText.LBL_TITLE.getString(), new Developers().mountScreen(800, 800));
            }
        };
    }
}