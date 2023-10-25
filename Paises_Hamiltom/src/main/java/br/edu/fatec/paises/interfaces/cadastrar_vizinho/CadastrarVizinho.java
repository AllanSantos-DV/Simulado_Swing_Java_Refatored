package br.edu.fatec.paises.interfaces.cadastrar_vizinho;

import br.edu.fatec.paises.interfaces.components_anotation.ComponentMethod;
import br.edu.fatec.paises.interfaces.enums.cadastrar_vizinho.CadastrarVizinhoText;
import br.edu.fatec.paises.interfaces.implementar.MontarTelas;

import javax.swing.*;
import java.awt.*;

public class CadastrarVizinho implements MontarTelas {

    private static final int COMPONENTS_WIDTH = 200;
    private static final int COMPONENTS_HEIGHT = 25;

    @ComponentMethod
    public JLabel getLblTitulo() {
        JLabel lblTitulo = new JLabel(CadastrarVizinhoText.LBL_TITLE_REGISTER.getString());
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(150, 10, COMPONENTS_WIDTH, COMPONENTS_HEIGHT);
        return lblTitulo;
    }

    @ComponentMethod
    public JLabel getLblPais() {
        JLabel lblPais = new JLabel(CadastrarVizinhoText.LBL_PAIS.getString());
        lblPais.setHorizontalAlignment(SwingConstants.CENTER);
        lblPais.setBounds(150, 50, COMPONENTS_WIDTH, COMPONENTS_HEIGHT);
        return lblPais;
    }

    @ComponentMethod
    public JComboBox<String> getCmbPais() {
        JComboBox<String> cmbPais = new JComboBox<>();
        cmbPais.setBounds(150, 80, COMPONENTS_WIDTH, COMPONENTS_HEIGHT);
        return cmbPais;
    }

    @ComponentMethod
    public JLabel getLblVizinho() {
        JLabel lblVizinho = new JLabel(CadastrarVizinhoText.LBL_VIZINHO.getString());
        lblVizinho.setHorizontalAlignment(SwingConstants.CENTER);
        lblVizinho.setBounds(150, 120, COMPONENTS_WIDTH, COMPONENTS_HEIGHT);
        return lblVizinho;
    }

    @ComponentMethod
    public JComboBox<String> getCmbVizinho() {
        JComboBox<String> cmbVizinho = new JComboBox<>();
        cmbVizinho.setBounds(100, 150, COMPONENTS_WIDTH, COMPONENTS_HEIGHT);
        return cmbVizinho;
    }

    @ComponentMethod
    public JButton getBtnSelecionar() {
        JButton btnSelecionar = new JButton(CadastrarVizinhoText.BTN_SELECT.getString());
        btnSelecionar.setBounds(300, 150, COMPONENTS_WIDTH/2, COMPONENTS_HEIGHT);
        return btnSelecionar;
    }

    @ComponentMethod
    public JLabel getLblSuccess() {
        JLabel lblSuccess = new JLabel();
        lblSuccess.setHorizontalAlignment(SwingConstants.CENTER);
        lblSuccess.setBounds(150, 175, COMPONENTS_WIDTH, COMPONENTS_HEIGHT);
        return lblSuccess;
    }

    @ComponentMethod
    public JButton getBtnCadastrar() {
        JButton btnCadastrar = new JButton(CadastrarVizinhoText.BTN_REGISTER.getString());
        btnCadastrar.setBounds(100, 210, COMPONENTS_WIDTH/2, COMPONENTS_HEIGHT);
        return btnCadastrar;
    }

    @ComponentMethod
    public JButton getBtnMenu() {
        JButton btnMenu = new JButton(CadastrarVizinhoText.BTN_MENU.getString());
        btnMenu.setBounds(300, 210, COMPONENTS_WIDTH/2, COMPONENTS_HEIGHT);
        return btnMenu;
    }
}
