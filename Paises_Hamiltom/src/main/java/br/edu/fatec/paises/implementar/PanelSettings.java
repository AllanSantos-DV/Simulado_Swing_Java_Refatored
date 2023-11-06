package br.edu.fatec.paises.implementar;

import br.edu.fatec.paises.components_anotation.ComponentMethod;
import br.edu.fatec.paises.enums.PanelSettingsText;
import br.edu.fatec.paises.telas.CountryMenuScreen;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.stream.Stream;

import static br.edu.fatec.paises.Main.logger;
import static br.edu.fatec.paises.Main.MENU;

public interface PanelSettings {

    default JPanel mountScreen(){
        return mountScreen(500, 300);
    }

    default JPanel mountScreen(int width, int height) {
        JPanel panelMenu = new JPanel();
        int border = 10;
        panelMenu.setLayout(null);
        panelMenu.setBorder(BorderFactory.createMatteBorder(border, border, border, border, Color.LIGHT_GRAY));
        panelMenu.setSize(width, height);
        Stream.of(this.getClass().getMethods())
                .filter(method -> method.isAnnotationPresent(ComponentMethod.class))
                .forEach(method -> {
                    try {
                        panelMenu.add((Component) method.invoke(this));
                    } catch (IllegalAccessException | InvocationTargetException | IllegalArgumentException e) {
                        logger.error(String.format(PanelSettingsText.MSG_ERROR.getString(), e.getMessage()), e);
                    }
                });
        return panelMenu;
    }

    default void backMenu(JButton button) {
        MENU.closeScreen(button);
        MENU.appScreen(PanelSettingsText.TELA_MENU.getString(), new CountryMenuScreen().mountScreen());
    }

    default boolean confirmBackMenu(){
        String[] options = {PanelSettingsText.BTN_CONFIRM.getString(), PanelSettingsText.BTN_CANCEL.getString()};
        int confirm = JOptionPane.showInternalOptionDialog(
                null, PanelSettingsText.MSG_CONFIRM.getString() +
                        "\n" + PanelSettingsText.MSG_WARNING.getString()
                , PanelSettingsText.MSG_TITLE_CONFIRM.getString()
                , JOptionPane.YES_NO_OPTION
                , JOptionPane.QUESTION_MESSAGE, null
                , options
                , options[0]);
        return confirm == JOptionPane.YES_OPTION;
    }
}