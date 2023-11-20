package br.edu.fatec.paises.app_screens_and_controls.controller;

import br.edu.fatec.paises.app_screens_and_controls.implementar.PanelSettings;
import br.edu.fatec.paises.app_screens_and_controls.screens.MenuScreen;
import br.edu.fatec.paises.app_screens_and_controls.screens.components_anotation.ComponentMethod;
import br.edu.fatec.paises.enums.AppText;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import static br.edu.fatec.paises.Main.COUNTRY_DAO;
import static br.edu.fatec.paises.Main.logger;

public class Menu extends MenuScreen implements PanelSettings {

    private final List<JComponent> components = List.of(lblTitle, lblLanguage, cmbLanguage, btnRegisterCountry, btnRegisterNeighborCountry,
            btnEditCountry, btnListCountries, linkGitHub, linkLabelDevelopers);

    private final Map<String, String> languages = new LinkedHashMap<>();

    public Menu() {
        initCmbLanguage();
        Stream.of(btnRegisterCountry, btnRegisterNeighborCountry, btnEditCountry, btnListCountries)
                .forEach(this::addListener);
        linkGitHub.addMouseListener(mouseListenerGitHub());
        linkLabelDevelopers.addMouseListener(mouseListenerCredits());
    }

    private void initCmbLanguage() {
        languages.put("Português", "lang/AppText_pt_BR");
        languages.put("English", "lang/AppText_en_US");
        languages.put("Español", "lang/AppText_es_ES");
        languages.put("Mandarim", "lang/AppText_zh_CN");
        languages.put("Français", "lang/AppText_fr_FR");
        languages.keySet().forEach(cmbLanguage::addItem);
        cmbLanguage.setSelectedItem(getLanguageName(AppText.getLanguage()));
        cmbLanguage.addActionListener(e -> changeLanguage(languages));
    }

    private String getLanguageName(String language) {
        return languages.entrySet().stream()
                .filter(entry -> entry.getValue().equals(language))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse("Português");
    }

    private void changeLanguage(Map<String, String> languagesMap) {
        String selectedLanguage = Objects.requireNonNull(cmbLanguage.getSelectedItem()).toString();
        String languageFile = languagesMap.get(selectedLanguage);
        if (!languageFile.equals(AppText.getLanguage())) {
            AppText.changeLanguage(languageFile);
            changeScreen(btnRegisterCountry, AppText.SCREEN_NAME_MENU.getString(), new Menu().mountScreen());
        }
    }

    @ComponentMethod
    public List<JComponent> listComponents() {
        return components;
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
        if (COUNTRY_DAO.findAll().isEmpty() && !button.getText().equals(AppText.BTN_NEW_COUNTRY.getString())) {
            String[] options = {AppText.BTN_NEW_COUNTRY.getString(), AppText.BTN_OPTION_PANE_MENU.getString()};
            int choice = JOptionPane.showInternalOptionDialog(null,
                    AppText.MSG_OPTION_PANE_COUNTRIES_EMPTY.getString() + "\n" + AppText.MSG_OPTION_PANE_NEW_COUNTRY.getString(),
                    AppText.LBL_TITLE_MENU.getString(), JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, options, options[0]);
            if ((choice == 0)) appScreen(options[choice], initializeScreens(options[choice]).mountScreen());
            else appScreen(options[choice], mountScreen());
            return true;
        }
        return false;
    }

    public void addListener(JButton button) {
        button.addActionListener(e -> {
            closeScreen(button);
            if (countriesEmpty(button)) return;
            appScreen(button.getText(), initializeScreens(button.getText()).mountScreen());
        });
    }

    public PanelSettings initializeScreens(String buttonText) {
        return switch (AppText.getEnum(buttonText)) {
            case BTN_NEW_COUNTRY -> new CountryRegistration();
            case BTN_LIST_COUNTRIES -> new CountryList();
            case BTN_REGISTER_NEIGHBOR -> new NeighboringCountryRegistration();
            case BTN_MANAGE_COUNTRY -> new CountryManager();
            default -> this;
        };
    }

    public MouseAdapter mouseListenerGitHub() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                try {
                    Desktop.getDesktop().browse(URI.create(AppText.LINK_GITHUB.getString()));
                } catch (IOException e) {
                    logger.error(AppText.MSG_ERROR.getString(), e);
                }
            }
        };
    }

    public MouseAdapter mouseListenerCredits() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                appScreenDevelopers(AppText.LBL_TITLE_DEVELOPERS.getString(), new Developers().mountScreen(800, 800));
            }
        };
    }
}