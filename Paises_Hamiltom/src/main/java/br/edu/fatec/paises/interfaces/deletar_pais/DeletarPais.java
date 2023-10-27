package br.edu.fatec.paises.interfaces.deletar_pais;

import br.edu.fatec.paises.interfaces.components_anotation.ComponentMethod;
import br.edu.fatec.paises.interfaces.enums.deletar_pais.DeletarPaisText;
import br.edu.fatec.paises.interfaces.implementar.MontarTelas;

import javax.swing.*;
import java.awt.*;

public class DeletarPais implements MontarTelas {
    private final JLabel lblTitulo = new JLabel();
    private final JLabel lblSelectPais = new JLabel();
    private final JComboBox<String> cmbSelectPais = new JComboBox<>();
    private final JButton btnDeletePais = new JButton();
    private final JLabel lblDeletePais = new JLabel();
    private final JButton btnMenu = new JButton();
    private static final int COMPONENTS_WIDTH = 200;
    private static final int COMPONENTS_HEIGHT = 25;

    public DeletarPais() {
        btnMenu.addActionListener(e -> voltarMenu(btnMenu));
    }

    @ComponentMethod
    public JLabel getLblTitulo() {
        lblTitulo.setText(DeletarPaisText.LBL_TITLE.getString());
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(150, 10, COMPONENTS_WIDTH, COMPONENTS_HEIGHT);
        return lblTitulo;
    }

    @ComponentMethod
    public JLabel getLblSelectPais() {
        lblSelectPais.setText(DeletarPaisText.LBL_SELECT_PAIS.getString());
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
    public JButton getBtnDeletePais() {
        btnDeletePais.setText(DeletarPaisText.BTN_DELETE_PAIS.getString());
        btnDeletePais.setBounds(175, 120, COMPONENTS_WIDTH - 50, COMPONENTS_HEIGHT);
        return btnDeletePais;
    }

    @ComponentMethod
    public JLabel getLblDeletePais() {
        lblDeletePais.setHorizontalAlignment(SwingConstants.CENTER);
        lblDeletePais.setBounds(150, 160, COMPONENTS_WIDTH, COMPONENTS_HEIGHT);
        return lblDeletePais;
    }

    @ComponentMethod
    public JButton getBtnMenu() {
        btnMenu.setText(DeletarPaisText.BTN_MENU.getString());
        btnMenu.setBounds(200, 200, COMPONENTS_WIDTH / 2, COMPONENTS_HEIGHT);
        return btnMenu;
    }
}
