package br.edu.fatec.paises.interfaces.listar_paises;

import br.edu.fatec.paises.interfaces.components_anotation.ComponentMethod;
import br.edu.fatec.paises.interfaces.enums.listar_paises.ListarPaisesText;
import br.edu.fatec.paises.interfaces.implementar.MontarTelas;

import javax.swing.*;
import java.awt.*;

public class ListarPaises implements MontarTelas {
    private final JLabel lblTitulo = new JLabel();
    private final JLabel lblQuantityPaises = new JLabel();
    private final JLabel lblListar = new JLabel();
    private final JButton btnListarNome = new JButton();
    private final JButton btnListarDimensao = new JButton();
    private final JTextArea txtAreaPaises = new JTextArea();
    private final JScrollPane scrollPane = new JScrollPane(txtAreaPaises);
    private final JButton btnMenu = new JButton();

    private static final int COMPONENTS_WIDTH = 200;
    private static final int COMPONENTS_HEIGHT = 25;

    @ComponentMethod
    public JLabel getLblTitulo() {
        lblTitulo.setText(ListarPaisesText.LBL_TITLE.getString());
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(150, 10, COMPONENTS_WIDTH, COMPONENTS_HEIGHT);
        return lblTitulo;
    }

    @ComponentMethod
    public JLabel getLblQuantityPaises() {
        lblQuantityPaises.setText(ListarPaisesText.LBL_QTD_PAISES.getString()+ListarPaisesText.LBL_QTD_PAISES_VALUE.getString());
        lblQuantityPaises.setHorizontalAlignment(SwingConstants.LEFT);
        lblQuantityPaises.setBounds(300, 40, COMPONENTS_WIDTH, COMPONENTS_HEIGHT);
        return lblQuantityPaises;
    }

    @ComponentMethod
    public JLabel getLblListar() {
        lblListar.setText(ListarPaisesText.LBL_LISTAR.getString());
        lblListar.setHorizontalAlignment(SwingConstants.LEFT);
        lblListar.setBounds(50, 40, COMPONENTS_WIDTH/3, COMPONENTS_HEIGHT);
        return lblListar;
    }

    @ComponentMethod
    public JButton getBtnListarNome() {
        btnListarNome.setText(ListarPaisesText.BTN_NOME.getString());
        btnListarNome.setBounds(110, 40, 75, COMPONENTS_HEIGHT);
        return btnListarNome;
    }

    @ComponentMethod
    public JButton getBtnListarDimensao() {
        btnListarDimensao.setText(ListarPaisesText.BTN_DIMENSION.getString());
        btnListarDimensao.setBounds(190, 40, COMPONENTS_WIDTH/2, COMPONENTS_HEIGHT);
        return btnListarDimensao;
    }

    @ComponentMethod
    public JScrollPane getTxtAreaPaisesComScroll() {
        txtAreaPaises.setEditable(false);
        scrollPane.setBounds(25, 70, COMPONENTS_WIDTH * 2+35, COMPONENTS_HEIGHT * 6);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        return scrollPane;
    }


    @ComponentMethod
    public JButton getBtnMenu() {
        btnMenu.setText(ListarPaisesText.BTN_MENU.getString());
        btnMenu.setBounds(200, 225, COMPONENTS_WIDTH/2, COMPONENTS_HEIGHT);
        return btnMenu;
    }
}
