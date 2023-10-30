package br.edu.fatec.paises.interfaces.editar_pais;

import br.edu.fatec.paises.components_anotation.ComponentMethod;
import br.edu.fatec.paises.enums.editar_pais.EditarPaisText;
import br.edu.fatec.paises.implementar.MontarTelas;
import br.edu.fatec.paises.services.editar_pais.EditarPaisService;

import javax.swing.*;
import java.awt.*;

public class EditarPais extends EditarPaisService implements MontarTelas {
    private final JLabel lblTitulo = new JLabel();
    private final JLabel lblSelectPais = new JLabel();
    private final JComboBox<String> cmbSelectPais = new JComboBox<>();
    private final JComboBox<String> cmbSelectVizinho = new JComboBox<>();
    private final JButton btnDeleteVizinho = new JButton();
    private final JLabel lblDeleteVizinho = new JLabel();
    private final JButton btnEditPais = new JButton();
    private final JButton btnMenu = new JButton();
    private static final int COMPONENTS_WIDTH = 200;
    private static final int COMPONENTS_HEIGHT = 25;

    public EditarPais() {
        initCmbPais(cmbSelectPais, cmbSelectVizinho, btnDeleteVizinho);
        cmbSelectPais.addActionListener(e -> initCmbVizinho(cmbSelectVizinho, cmbSelectPais, btnDeleteVizinho));
        btnMenu.addActionListener(e -> voltarMenu(btnMenu));
        btnEditPais.addActionListener(e -> editarPais(cmbSelectPais, btnEditPais));
        btnDeleteVizinho.addActionListener(e -> deleteVizinho(lblDeleteVizinho, cmbSelectPais, cmbSelectVizinho, btnDeleteVizinho));
    }

    @ComponentMethod
    public JLabel getLblTitulo() {
        lblTitulo.setText(EditarPaisText.LBL_TITLE.getString());
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(50, 10, COMPONENTS_WIDTH*2, COMPONENTS_HEIGHT);
        return lblTitulo;
    }

    @ComponentMethod
    public JLabel getLblSelectPais() {
        lblSelectPais.setText(EditarPaisText.LBL_SELECT_PAIS.getString());
        lblSelectPais.setHorizontalAlignment(SwingConstants.CENTER);
        lblSelectPais.setBounds(150, 50, COMPONENTS_WIDTH, COMPONENTS_HEIGHT);
        return lblSelectPais;
    }

    @ComponentMethod
    public JComboBox<String> getCmbSelectPais() {
        cmbSelectPais.setBounds(150, 80, COMPONENTS_WIDTH, COMPONENTS_HEIGHT);
        return cmbSelectPais;
    }

    @ComponentMethod
    public JComboBox<String> getCmbSelectVizinho() {
        cmbSelectVizinho.setBounds(75, 120, COMPONENTS_WIDTH, COMPONENTS_HEIGHT);
        return cmbSelectVizinho;
    }

    @ComponentMethod
    public JButton getBtnDeleteVizinho() {
        btnDeleteVizinho.setText(EditarPaisText.BTN_DELETE_VIZINHO.getString());
        btnDeleteVizinho.setBounds(275, 120, COMPONENTS_WIDTH - 50, COMPONENTS_HEIGHT);
        return btnDeleteVizinho;
    }

    @ComponentMethod
    public JLabel getLblDeleteVizinho() {
        lblDeleteVizinho.setHorizontalAlignment(SwingConstants.CENTER);
        lblDeleteVizinho.setBounds(50, 150, COMPONENTS_WIDTH*2, COMPONENTS_HEIGHT);
        return lblDeleteVizinho;
    }

    @ComponentMethod
    public JButton getBtnEditPais() {
        btnEditPais.setText(EditarPaisText.BTN_EDIT_PAIS.getString());
        btnEditPais.setBounds(175, 185, COMPONENTS_WIDTH - 50, COMPONENTS_HEIGHT);
        return btnEditPais;
    }

    @ComponentMethod
    public JButton getBtnMenu() {
        btnMenu.setText(EditarPaisText.BTN_MENU.getString());
        btnMenu.setBounds(200, 225, COMPONENTS_WIDTH / 2, COMPONENTS_HEIGHT);
        return btnMenu;
    }
}
