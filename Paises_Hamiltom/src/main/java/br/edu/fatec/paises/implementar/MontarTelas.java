package br.edu.fatec.paises.implementar;

import br.edu.fatec.paises.components_anotation.ComponentMethod;
import br.edu.fatec.paises.enums.implementar.MontarTelasText;
import br.edu.fatec.paises.interfaces.menu.Menu;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.stream.Stream;

import static br.edu.fatec.paises.Main.*;

public interface MontarTelas {

    default JPanel montarTela(){
        return montarTela(500, 300);
    }

    default JPanel montarTela(int larguraTela, int alturaTela) {
        JPanel panelMenu = new JPanel();
        int border = 10;
        panelMenu.setLayout(null);
        panelMenu.setBorder(BorderFactory.createMatteBorder(border, border, border, border, Color.LIGHT_GRAY));
        panelMenu.setSize(larguraTela, alturaTela);
        Stream.of(this.getClass().getMethods())
                .filter(method -> method.isAnnotationPresent(ComponentMethod.class))
                .forEach(method -> {
                    try {
                        panelMenu.add((Component) method.invoke(this));
                    } catch (IllegalAccessException | InvocationTargetException | IllegalArgumentException e) {
                        logger.error(String.format(MontarTelasText.MSG_ERROR.getString(), e.getMessage()), e);
                    }
                });
        return panelMenu;
    }

    default void voltarMenu(JButton button) {
        menuServices.telaClose(button);
        menuServices.telaApp(MontarTelasText.TELA_MENU.getString(), new Menu().montarTela());
    }
}