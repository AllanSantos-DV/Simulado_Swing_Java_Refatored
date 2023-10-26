package br.edu.fatec.paises.interfaces.menu;

import br.edu.fatec.paises.interfaces.components_anotation.ComponentMethod;
import br.edu.fatec.paises.interfaces.enums.menu.MenuText;
import br.edu.fatec.paises.interfaces.implementar.MontarTelas;
import br.edu.fatec.paises.services.menu.MenuServices;

import javax.swing.*;
import java.awt.*;
import java.util.stream.Stream;

public class Menu extends MenuServices implements MontarTelas {
    private final JLabel lblTitulo = new JLabel();
    private final JButton btnCadastrarPais = new JButton(MenuText.BTN_NEW_PAIS.getString());
    private final JButton btnADDVizinho = new JButton(MenuText.BTN_NEW_VIZINHO.getString());
    private final JButton btnEditarPais = new JButton(MenuText.BTN_EDIT_PAIS.getString());
    private final JButton btnListarPais = new JButton(MenuText.BTN_LIST_PAISES.getString());
    private final JButton btnDeletarPais = new JButton(MenuText.BTN_DELETE_PAIS.getString());
    private static final int COMPONENTS_WIDTH = 150;
    private static final int COMPONENTS_HEIGHT = 30;

    public Menu(){
        Stream.of(btnCadastrarPais,btnADDVizinho,btnEditarPais,btnListarPais,btnDeletarPais)
                .forEach(this::addListener);
    }

    @ComponentMethod
    public JLabel getLblTitulo() {
        lblTitulo.setText(MenuText.LBL_TITLE.getString());
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setBounds(50, 10, 400, COMPONENTS_HEIGHT);
        return lblTitulo;
    }

    @ComponentMethod
    public JButton getBtnCadastrarPais() {
        btnCadastrarPais.setBounds(50, 80, COMPONENTS_WIDTH, COMPONENTS_HEIGHT);
        return btnCadastrarPais;
    }

    @ComponentMethod
    public JButton getBtnADDVizinho() {
        btnADDVizinho.setBounds(50, 120, COMPONENTS_WIDTH, COMPONENTS_HEIGHT);
        return btnADDVizinho;
    }

    @ComponentMethod
    public JButton getBtnEditarPais() {
        btnEditarPais.setBounds(250, 80, COMPONENTS_WIDTH, COMPONENTS_HEIGHT);
        return btnEditarPais;
    }

    @ComponentMethod
    public JButton getBtnListarPais() {
        btnListarPais.setBounds(250, 120, COMPONENTS_WIDTH, COMPONENTS_HEIGHT);
        return btnListarPais;
    }

    @ComponentMethod
    public JButton getBtnDeletarPais() {
        btnDeletarPais.setBounds(150, 160, COMPONENTS_WIDTH, COMPONENTS_HEIGHT);
        return btnDeletarPais;
    }
}

