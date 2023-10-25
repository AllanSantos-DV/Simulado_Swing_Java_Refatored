package br.edu.fatec.paises.interfaces.menu;

import br.edu.fatec.paises.interfaces.components_anotation.ComponentMethod;
import br.edu.fatec.paises.interfaces.enums.menu.MenuText;
import br.edu.fatec.paises.interfaces.implementar.MontarTelas;

import javax.swing.*;
import java.awt.*;

public class Menu implements MontarTelas {

    private static final int COMPONENTS_WIDTH = 150;
    private static final int COMPONENTS_HEIGHT = 30;

    @ComponentMethod
    public JLabel getLblTitulo() {
        JLabel lblTitulo = new JLabel(MenuText.LBL_TITLE.getString());
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setBounds(50, 10, 400, COMPONENTS_HEIGHT);
        return lblTitulo;
    }

    @ComponentMethod
    public JButton getBtnCadastrarPais() {
        JButton btnCadastrarPais = new JButton(MenuText.BTN_NEW_PAIS.getString());
        btnCadastrarPais.setBounds(50, 80, COMPONENTS_WIDTH, COMPONENTS_HEIGHT);
        return btnCadastrarPais;
    }

    @ComponentMethod
    public JButton getBtnADDVizinho() {
        JButton btnADDVizinho = new JButton(MenuText.BTN_NEW_VIZINHO.getString());
        btnADDVizinho.setBounds(50, 120, COMPONENTS_WIDTH, COMPONENTS_HEIGHT);
        return btnADDVizinho;
    }

    @ComponentMethod
    public JButton getBtnEditarPais() {
        JButton btnEditarPais = new JButton(MenuText.BTN_EDIT_PAIS.getString());
        btnEditarPais.setBounds(250, 80, COMPONENTS_WIDTH, COMPONENTS_HEIGHT);
        return btnEditarPais;
    }

    @ComponentMethod
    public JButton getBtnListarPais() {
        JButton btnListarPais = new JButton(MenuText.BTN_LIST_PAISES.getString());
        btnListarPais.setBounds(250, 120, COMPONENTS_WIDTH, COMPONENTS_HEIGHT);
        return btnListarPais;
    }

    @ComponentMethod
    public JButton getBtnDeletarPais() {
        JButton btnDeletarPais = new JButton(MenuText.BTN_DELETE_PAIS.getString());
        btnDeletarPais.setBounds(150, 160, COMPONENTS_WIDTH, COMPONENTS_HEIGHT);
        return btnDeletarPais;
    }
}

