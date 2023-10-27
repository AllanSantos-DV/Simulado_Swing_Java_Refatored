package br.edu.fatec.paises.interfaces.implementar;

import br.edu.fatec.paises.interfaces.components_anotation.ComponentMethod;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.stream.Stream;

import static br.edu.fatec.paises.Main.menu;
import static br.edu.fatec.paises.Main.menuServices;
import static java.lang.System.out;

public interface MontarTelas {

    default JPanel montarTela(){
        JPanel panelMenu = new JPanel();
        panelMenu.setSize(500, 300);
        Stream.of(this.getClass().getMethods())
                .filter(method -> method.isAnnotationPresent(ComponentMethod.class))
                .forEach(method -> {
                    try {
                        panelMenu.add((Component) method.invoke(this));
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        out.println("Erro ao montar tela: " + e.getMessage());
                    }
                });
        return panelMenu;
    }

    default void voltarMenu(JButton button) {
        menuServices.telaClose(button);
        menuServices.telaApp("Menu", menu.montarTela());
    }
}
