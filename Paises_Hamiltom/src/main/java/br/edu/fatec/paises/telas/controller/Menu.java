package br.edu.fatec.paises.telas.controller;

import br.edu.fatec.paises.telas.*;
import br.edu.fatec.paises.enums.MenuText;
import br.edu.fatec.paises.implementar.PanelSettings;
import br.edu.fatec.paises.telas.CountryMenuScreen;

import javax.swing.*;

import static br.edu.fatec.paises.Main.COUNTRY_DAO;

public class Menu {

    public void appScreen(String name, JPanel panel) {
        JFrame frame = new JFrame(name);
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(panel.getSize());
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public void closeScreen(JButton button) {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(button);
        frame.dispose();
    }

    public boolean countriesEmpty(JButton button) {
        if(COUNTRY_DAO.getCountries().isEmpty() && !button.getText().equals(MenuText.BTN_NEW_COUNTRY.getString())) {
            String[] options = {MenuText.BTN_NEW_COUNTRY.getString(), MenuText.BTN_MENU.getString()};
            int choice = JOptionPane.showInternalOptionDialog(null,
                    MenuText.MSG_COUNTRIES_EMPTY.getString() + "\n" + MenuText.MSG_NEW_COUNTRY.getString(),
                    MenuText.LBL_TITLE.getString(), JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null,options, options[0]);
            if ((choice == 0)) appScreen(options[choice], initializeScreens(options[choice]).mountScreen());
            else appScreen(options[choice], new CountryMenuScreen().mountScreen());
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
            case BTN_NEW_COUNTRY -> new CountryRegistrationScreen();
            case BTN_LIST_COUNTRIES -> new CountryListScreen();
            case BTN_NEW_NEIGHBOR -> new NeighboringCountryRegistrationScreen();
            case BTN_EDIT_COUNTRY -> new CountryEditorScreen();
            case BTN_DELETE_COUNTRY -> new CountryDeleteScreen();
            default -> new CountryMenuScreen();
        };
    }
}