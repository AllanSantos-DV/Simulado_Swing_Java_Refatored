package br.edu.fatec.paises.interfaces.listar_paises;

import br.edu.fatec.paises.interfaces.components_anotation.ComponentMethod;
import br.edu.fatec.paises.interfaces.enums.listar_paises.ListarPaisesText;
import br.edu.fatec.paises.interfaces.implementar.MontarTelas;

import javax.swing.*;
import java.awt.*;

public class ListarPaises implements MontarTelas {

    private static final int COMPONENTS_WIDTH = 200;
    private static final int COMPONENTS_HEIGHT = 25;

    @ComponentMethod
    public JLabel getLblTitulo() {
        JLabel lblTitulo = new JLabel(ListarPaisesText.LBL_TITLE.getString());
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(150, 10, COMPONENTS_WIDTH, COMPONENTS_HEIGHT);
        return lblTitulo;
    }

    @ComponentMethod
    public JLabel getLblQuantityPaises() {
        JLabel lblQuantityPaises = new JLabel(ListarPaisesText.LBL_QTD_PAISES.getString()+ListarPaisesText.LBL_QTD_PAISES_VALUE.getString());
        lblQuantityPaises.setHorizontalAlignment(SwingConstants.LEFT);
        lblQuantityPaises.setBounds(300, 40, COMPONENTS_WIDTH, COMPONENTS_HEIGHT);
        return lblQuantityPaises;
    }

    @ComponentMethod
    public JLabel getLblListar() {
        JLabel lblListar = new JLabel(ListarPaisesText.LBL_LISTAR.getString());
        lblListar.setHorizontalAlignment(SwingConstants.LEFT);
        lblListar.setBounds(50, 40, COMPONENTS_WIDTH/3, COMPONENTS_HEIGHT);
        return lblListar;
    }

    @ComponentMethod
    public JButton getBtnListarNome() {
        JButton btnListarNome = new JButton(ListarPaisesText.BTN_NOME.getString());
        btnListarNome.setBounds(110, 40, 75, COMPONENTS_HEIGHT);
        return btnListarNome;
    }

    @ComponentMethod
    public JButton getBtnListarDimensao() {
        JButton btnListarDimensao = new JButton(ListarPaisesText.BTN_DIMENSION.getString());
        btnListarDimensao.setBounds(190, 40, COMPONENTS_WIDTH/2, COMPONENTS_HEIGHT);
        return btnListarDimensao;
    }

    @ComponentMethod
    public JScrollPane getTxtAreaPaisesComScroll() {
        JTextArea txtAreaPaises = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(txtAreaPaises);
        scrollPane.setBounds(50, 70, COMPONENTS_WIDTH * 2, COMPONENTS_HEIGHT * 6);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        return scrollPane;
    }


    @ComponentMethod
    public JButton getBtnMenu() {
        JButton btnMenu = new JButton(ListarPaisesText.BTN_MENU.getString());
        btnMenu.setBounds(200, 225, COMPONENTS_WIDTH/2, COMPONENTS_HEIGHT);
        return btnMenu;
    }
}
