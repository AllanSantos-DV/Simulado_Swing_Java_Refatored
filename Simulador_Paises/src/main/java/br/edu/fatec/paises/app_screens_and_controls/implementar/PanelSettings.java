package br.edu.fatec.paises.app_screens_and_controls.implementar;

import br.edu.fatec.paises.app_screens_and_controls.controller.Menu;
import br.edu.fatec.paises.app_screens_and_controls.screens.components_anotation.ComponentMethod;
import br.edu.fatec.paises.enums.AppText;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Stream;

import static br.edu.fatec.paises.Main.logger;

public interface PanelSettings {

    default JPanel mountScreen() {
        return mountScreen(500, 300);
    }

    default JPanel mountScreen(int width, int height) {
        JPanel jPanel = new JPanel();
        int border = 10;
        jPanel.setLayout(null);
        jPanel.setBorder(BorderFactory.createMatteBorder(border, border, border, border, Color.LIGHT_GRAY));
        jPanel.setSize(width, height);
        Stream.of(this.getClass().getMethods())
                .filter(method -> method.isAnnotationPresent(ComponentMethod.class))
                .forEach(method -> {
                    try {
                        Object component = method.invoke(this);
                        if (component instanceof List<?>)
                            ((List<?>) component).forEach(item -> jPanel.add((Component) item));
                        else jPanel.add((Component) component);
                    } catch (IllegalAccessException | InvocationTargetException | IllegalArgumentException e) {
                        logger.error(String.format(AppText.MSG_ERROR_MOUNT_SCREEN.getString(), e.getMessage()), e);
                    }
                });
        return jPanel;
    }

    default void changeScreen(JButton button, String screenName, JPanel panel) {
        Menu menu = new Menu();
        menu.closeScreen(button);
        menu.appScreen(screenName, panel);
    }

    default void menuScreen(JButton button) {
        changeScreen(button, AppText.SCREEN_NAME_MENU.getString(), new Menu().mountScreen());
    }

    default boolean confirmBackMenu(String title) {
        String[] options = {AppText.BTN_CONFIRM.getString(), AppText.BTN_CANCEL.getString()};
        int confirm = JOptionPane.showInternalOptionDialog(
                null, AppText.MSG_CONFIRM.getString() +
                        "\n" + AppText.MSG_WARNING.getString()
                , title
                , JOptionPane.YES_NO_OPTION
                , JOptionPane.QUESTION_MESSAGE, null
                , options
                , options[0]);
        return confirm == JOptionPane.YES_OPTION;
    }
}