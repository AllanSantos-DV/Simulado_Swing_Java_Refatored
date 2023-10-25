package br.edu.fatec.paises.interfaces.adicionar_pais;

import br.edu.fatec.paises.interfaces.components_anotation.ComponentMethod;
import br.edu.fatec.paises.interfaces.enums.adicionar_paises.AdicionarPaisText;
import br.edu.fatec.paises.interfaces.implementar.MontarTelas;

import javax.swing.*;
import java.awt.*;

public class AdicionarPais implements MontarTelas {

    private static final int COMPONENTS_WIDTH = 200;
    private static final int COMPONENTS_HEIGHT = 25;
    private static final int LABEL_WIDTH = 90;



    @ComponentMethod
    public JLabel getLblTitulo() {
        JLabel lblTitulo = new JLabel(AdicionarPaisText.LBL_TITLE_REGISTER.getString());
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setBounds(150, 10, 200, COMPONENTS_HEIGHT);
        return lblTitulo;
    }

    @ComponentMethod
    public JLabel getLblNome() {
        JLabel lblNome = new JLabel(AdicionarPaisText.LBL_NOME.getString());
        lblNome.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNome.setBounds(50, 50, LABEL_WIDTH, COMPONENTS_HEIGHT);
        return lblNome;
    }

    @ComponentMethod
    public JTextField getTxtNome() {
        JTextField txtNome = new JTextField();
        txtNome.setBounds(150, 50, COMPONENTS_WIDTH, COMPONENTS_HEIGHT);
        return txtNome;
    }

    @ComponentMethod
    public JLabel getLblCapital() {
        JLabel lblCapital = new JLabel(AdicionarPaisText.LBL_CAPITAL.getString());
        lblCapital.setHorizontalAlignment(SwingConstants.RIGHT);
        lblCapital.setBounds(50, 90, LABEL_WIDTH, COMPONENTS_HEIGHT);
        return lblCapital;
    }

    @ComponentMethod
    public JTextField getTxtCapital() {
        JTextField txtCapital = new JTextField();
        txtCapital.setBounds(150, 90, COMPONENTS_WIDTH, COMPONENTS_HEIGHT);
        return txtCapital;
    }

    @ComponentMethod
    public JLabel getLblDimensao() {
        JLabel lblDimensao = new JLabel(AdicionarPaisText.LBL_DIMENSION.getString());
        lblDimensao.setHorizontalAlignment(SwingConstants.RIGHT);
        lblDimensao.setBounds(50, 130, LABEL_WIDTH, COMPONENTS_HEIGHT);
        return lblDimensao;
    }

    @ComponentMethod
    public JSpinner getTxtDimensao() {
        JSpinner txtDimensao = new JSpinner();
        txtDimensao.setBounds(150, 130, COMPONENTS_WIDTH, COMPONENTS_HEIGHT);
        return txtDimensao;
    }

    @ComponentMethod
    public JLabel getLblSucess() {
        JLabel lblSuccess = new JLabel();
        lblSuccess.setHorizontalAlignment(SwingConstants.CENTER);
        lblSuccess.setBounds(150, 170, 200, COMPONENTS_HEIGHT);
        return lblSuccess;
    }

    @ComponentMethod
    public JButton getBtnSalvar() {
        JButton btnSalvar = new JButton(AdicionarPaisText.BTN_SAVE.getString());
        btnSalvar.setBounds(125, 210, COMPONENTS_WIDTH /2, COMPONENTS_HEIGHT);
        return btnSalvar;
    }

    @ComponentMethod
    public JButton getBtnMenu() {
        JButton btnMenu = new JButton(AdicionarPaisText.BTN_MENU.getString());
        btnMenu.setBounds(275, 210, COMPONENTS_WIDTH /2, COMPONENTS_HEIGHT);
        return btnMenu;
    }
}
