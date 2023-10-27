package br.edu.fatec.paises.interfaces.adicionar_pais;

import br.edu.fatec.paises.interfaces.components_anotation.ComponentMethod;
import br.edu.fatec.paises.interfaces.enums.adicionar_paises.AdicionarPaisText;
import br.edu.fatec.paises.interfaces.implementar.MontarTelas;
import br.edu.fatec.paises.services.adicionar_pais.AdicionarPaisService;

import javax.swing.*;
import java.awt.*;

public class AdicionarPais extends AdicionarPaisService implements MontarTelas {
    private final JLabel lblTitulo = new JLabel();
    private final JLabel lblNome = new JLabel();
    private final JTextField txtNome = new JTextField();
    private final JLabel lblCapital = new JLabel();
    private final JTextField txtCapital = new JTextField();
    private final JLabel lblDimensao = new JLabel();
    private final JSpinner txtDimensao = new JSpinner();
    private final JLabel lblSucess = new JLabel();
    private final JButton btnSalvar = new JButton();
    private final JButton btnMenu = new JButton();
    private static final int COMPONENTS_WIDTH = 200;
    private static final int COMPONENTS_HEIGHT = 25;
    private static final int LABEL_WIDTH = 90;

    public AdicionarPais() {
        btnSalvar.addActionListener(e -> adicionarPais(this));
        btnMenu.addActionListener(e -> voltarMenu(btnMenu));
    }

    @ComponentMethod
    public JLabel getLblTitulo() {
        lblTitulo.setText(AdicionarPaisText.LBL_TITLE_REGISTER.getString());
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setBounds(150, 10, 200, COMPONENTS_HEIGHT);
        return lblTitulo;
    }

    @ComponentMethod
    public JLabel getLblNome() {
        lblNome.setText(AdicionarPaisText.LBL_NOME.getString());
        lblNome.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNome.setBounds(50, 50, LABEL_WIDTH, COMPONENTS_HEIGHT);
        return lblNome;
    }

    @ComponentMethod
    public JTextField getTxtNome() {
        txtNome.setBounds(150, 50, COMPONENTS_WIDTH, COMPONENTS_HEIGHT);
        return txtNome;
    }

    @ComponentMethod
    public JLabel getLblCapital() {
        lblCapital.setText(AdicionarPaisText.LBL_CAPITAL.getString());
        lblCapital.setHorizontalAlignment(SwingConstants.RIGHT);
        lblCapital.setBounds(50, 90, LABEL_WIDTH, COMPONENTS_HEIGHT);
        return lblCapital;
    }

    @ComponentMethod
    public JTextField getTxtCapital() {
        txtCapital.setBounds(150, 90, COMPONENTS_WIDTH, COMPONENTS_HEIGHT);
        return txtCapital;
    }

    @ComponentMethod
    public JLabel getLblDimensao() {
        lblDimensao.setText(AdicionarPaisText.LBL_DIMENSION.getString());
        lblDimensao.setHorizontalAlignment(SwingConstants.RIGHT);
        lblDimensao.setBounds(50, 130, LABEL_WIDTH, COMPONENTS_HEIGHT);
        return lblDimensao;
    }

    @ComponentMethod
    public JSpinner getTxtDimensao() {
        txtDimensao.setBounds(150, 130, COMPONENTS_WIDTH, COMPONENTS_HEIGHT);
        return txtDimensao;
    }

    @ComponentMethod
    public JLabel getLblSucess() {
        lblSucess.setHorizontalAlignment(SwingConstants.CENTER);
        lblSucess.setBounds(50, 170, COMPONENTS_WIDTH * 2, COMPONENTS_HEIGHT);
        return lblSucess;
    }

    @ComponentMethod
    public JButton getBtnSalvar() {
        btnSalvar.setText(AdicionarPaisText.BTN_SAVE.getString());
        btnSalvar.setBounds(125, 210, COMPONENTS_WIDTH / 2, COMPONENTS_HEIGHT);
        return btnSalvar;
    }

    @ComponentMethod
    public JButton getBtnMenu() {
        btnMenu.setText(AdicionarPaisText.BTN_MENU.getString());
        btnMenu.setBounds(275, 210, COMPONENTS_WIDTH / 2, COMPONENTS_HEIGHT);
        return btnMenu;
    }
}
