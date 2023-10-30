package br.edu.fatec.paises.interfaces.cadastrar_vizinho;

import br.edu.fatec.paises.components_anotation.ComponentMethod;
import br.edu.fatec.paises.enums.cadastrar_vizinho.CadastrarVizinhoText;
import br.edu.fatec.paises.implementar.MontarTelas;
import br.edu.fatec.paises.services.cadastrar_vizinho.CadastrarVizinhoService;

import javax.swing.*;
import java.awt.*;

public class CadastrarVizinho extends CadastrarVizinhoService implements MontarTelas {
    private final JLabel lblTitulo = new JLabel();
    private final JLabel lblPais = new JLabel();
    private final JComboBox<String> cmbPais = new JComboBox<>();
    private final JLabel lblVizinho = new JLabel();
    private final JComboBox<String> cmbVizinho = new JComboBox<>();
    private final JButton btnSelecionar = new JButton();
    private final JLabel lblSuccess = new JLabel();
    private final JButton btnCadastrar = new JButton();
    private final JButton btnMenu = new JButton();
    private static final int COMPONENTS_WIDTH = 200;
    private static final int COMPONENTS_HEIGHT = 25;

    public CadastrarVizinho() {
        updateCombs(cmbPais, cmbVizinho, btnSelecionar);
        cmbPais.addActionListener(e -> updateCmbVizinhoPaisChange(btnSelecionar,cmbPais, cmbVizinho));
        btnCadastrar.addActionListener(e -> addPaisesSelected(lblSuccess, cmbPais));
        btnSelecionar.addActionListener(e -> selecionarVizinho(lblSuccess, cmbPais, cmbVizinho, btnSelecionar));
        btnMenu.addActionListener(e -> voltarMenu(btnMenu));
    }

    @ComponentMethod
    public JLabel getLblTitulo() {
        lblTitulo.setText(CadastrarVizinhoText.LBL_TITLE_REGISTER.getString());
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(50, 10, COMPONENTS_WIDTH*2, COMPONENTS_HEIGHT);
        return lblTitulo;
    }

    @ComponentMethod
    public JLabel getLblPais() {
        lblPais.setText(CadastrarVizinhoText.LBL_PAIS.getString());
        lblPais.setHorizontalAlignment(SwingConstants.CENTER);
        lblPais.setBounds(150, 50, COMPONENTS_WIDTH, COMPONENTS_HEIGHT);
        return lblPais;
    }

    @ComponentMethod
    public JComboBox<String> getCmbPais() {
        cmbPais.setBounds(150, 80, COMPONENTS_WIDTH, COMPONENTS_HEIGHT);
        return cmbPais;
    }

    @ComponentMethod
    public JLabel getLblVizinho() {
        lblVizinho.setText(CadastrarVizinhoText.LBL_VIZINHO.getString());
        lblVizinho.setHorizontalAlignment(SwingConstants.CENTER);
        lblVizinho.setBounds(150, 120, COMPONENTS_WIDTH, COMPONENTS_HEIGHT);
        return lblVizinho;
    }

    @ComponentMethod
    public JComboBox<String> getCmbVizinho() {
        cmbVizinho.setBounds(100, 150, COMPONENTS_WIDTH, COMPONENTS_HEIGHT);
        return cmbVizinho;
    }

    @ComponentMethod
    public JButton getBtnSelecionar() {
        btnSelecionar.setText(CadastrarVizinhoText.BTN_SELECT.getString());
        btnSelecionar.setBounds(300, 150, COMPONENTS_WIDTH / 2, COMPONENTS_HEIGHT);
        return btnSelecionar;
    }

    @ComponentMethod
    public JLabel getLblSuccess() {
        lblSuccess.setHorizontalAlignment(SwingConstants.CENTER);
        lblSuccess.setBounds(50, 175, COMPONENTS_WIDTH*2, COMPONENTS_HEIGHT);
        return lblSuccess;
    }

    @ComponentMethod
    public JButton getBtnCadastrar() {
        btnCadastrar.setText(CadastrarVizinhoText.BTN_REGISTER.getString());
        btnCadastrar.setBounds(100, 210, COMPONENTS_WIDTH / 2, COMPONENTS_HEIGHT);
        return btnCadastrar;
    }

    @ComponentMethod
    public JButton getBtnMenu() {
        btnMenu.setText(CadastrarVizinhoText.BTN_MENU.getString());
        btnMenu.setBounds(300, 210, COMPONENTS_WIDTH / 2, COMPONENTS_HEIGHT);
        return btnMenu;
    }
}
