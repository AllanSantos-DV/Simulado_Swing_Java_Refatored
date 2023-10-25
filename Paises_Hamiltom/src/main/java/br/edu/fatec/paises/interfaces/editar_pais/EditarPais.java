package br.edu.fatec.paises.interfaces.editar_pais;

import br.edu.fatec.paises.interfaces.components_anotation.ComponentMethod;
import br.edu.fatec.paises.interfaces.enums.editar_pais.EditarPaisText;
import br.edu.fatec.paises.interfaces.implementar.MontarTelas;

import javax.swing.*;
import java.awt.*;

public class EditarPais implements MontarTelas {

    private static final int COMPONENTS_WIDTH = 200;
    private static final int COMPONENTS_HEIGHT = 25;

    @ComponentMethod
    public JLabel getLblTitulo() {
        JLabel lblTitulo = new JLabel(EditarPaisText.LBL_TITLE.getString());
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(150, 10, COMPONENTS_WIDTH, COMPONENTS_HEIGHT);
        return lblTitulo;
    }

    @ComponentMethod
    public JLabel getLblSelectPais() {
        JLabel lblSelectPais = new JLabel(EditarPaisText.LBL_SELECT_PAIS.getString());
        lblSelectPais.setHorizontalAlignment(SwingConstants.CENTER);
        lblSelectPais.setBounds(150, 50, COMPONENTS_WIDTH, COMPONENTS_HEIGHT);
        return lblSelectPais;
    }

    @ComponentMethod
    public JComboBox<String> getCmbSelectPais() {
        JComboBox<String> cmbSelectPais = new JComboBox<>();
        cmbSelectPais.setBounds(150, 80, COMPONENTS_WIDTH, COMPONENTS_HEIGHT);
        return cmbSelectPais;
    }

    @ComponentMethod
    public JComboBox<String> getCmbSelectVizinho() {
        JComboBox<String> cmbSelectVizinho = new JComboBox<>();
        cmbSelectVizinho.setBounds(75, 120, COMPONENTS_WIDTH, COMPONENTS_HEIGHT);
        return cmbSelectVizinho;
    }

    @ComponentMethod
    public JButton getBtnDeleteVizinho() {
        JButton btnDeleteVizinho = new JButton(EditarPaisText.BTN_DELETE_VIZINHO.getString());
        btnDeleteVizinho.setBounds(275, 120, COMPONENTS_WIDTH-50, COMPONENTS_HEIGHT);
        return btnDeleteVizinho;
    }

    @ComponentMethod
    public JLabel getLblDeleteVizinho() {
        JLabel lblDeleteVizinho = new JLabel();
        lblDeleteVizinho.setHorizontalAlignment(SwingConstants.CENTER);
        lblDeleteVizinho.setBounds(150, 150, COMPONENTS_WIDTH, COMPONENTS_HEIGHT);
        return lblDeleteVizinho;
    }

    @ComponentMethod
    public JButton getBtnEditPais() {
        JButton btnEditPais = new JButton(EditarPaisText.BTN_EDIT_PAIS.getString());
        btnEditPais.setBounds(175, 185, COMPONENTS_WIDTH-50, COMPONENTS_HEIGHT);
        return btnEditPais;
    }

    @ComponentMethod
    public JButton getBtnMenu() {
        JButton btnMenu = new JButton(EditarPaisText.BTN_MENU.getString());
        btnMenu.setBounds(200, 225, COMPONENTS_WIDTH/2, COMPONENTS_HEIGHT);
        return btnMenu;
    }
}
