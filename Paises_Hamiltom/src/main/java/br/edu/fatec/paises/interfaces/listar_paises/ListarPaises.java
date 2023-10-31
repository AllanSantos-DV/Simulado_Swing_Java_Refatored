package br.edu.fatec.paises.interfaces.listar_paises;

import br.edu.fatec.paises.components_anotation.ComponentMethod;
import br.edu.fatec.paises.enums.listar_paises.ListarPaisesText;
import br.edu.fatec.paises.implementar.MontarTelas;
import br.edu.fatec.paises.services.listar_paises.ListarPaisesService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.stream.Stream;

public class ListarPaises extends ListarPaisesService implements MontarTelas {
    private final JLabel lblTitulo = new JLabel();
    private final JLabel lblQuantityPaises = new JLabel();
    private final JLabel lblListar = new JLabel();
    private final JButton btnListarNome = new JButton();
    private final JButton btnListarDimensao = new JButton();
    private final JButton btnListarNomeCapital = new JButton();
    private final DefaultTableModel tableModel = new DefaultTableModel();
    private final JTable table = new JTable(tableModel);
    private final JScrollPane scrollPane = new JScrollPane(table);
    private final JButton btnMenu = new JButton();
    private static final int COMPONENTS_WIDTH = 200;
    private static final int COMPONENTS_HEIGHT = 24;

    public ListarPaises() {
        listarPaises(this);
        Stream.of(btnListarNome, btnListarDimensao, btnListarNomeCapital)
                .forEach(btn -> btn.addActionListener(e -> listarPaisesOrdenado(this, btn.getText())));
        btnMenu.addActionListener(e -> voltarMenu(btnMenu));
    }

    @ComponentMethod
    public JLabel getLblTitulo() {
        lblTitulo.setText(ListarPaisesText.LBL_TITLE.getString());
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(50, 10, COMPONENTS_WIDTH*2, COMPONENTS_HEIGHT);
        return lblTitulo;
    }

    @ComponentMethod
    public JLabel getLblQuantityPaises() {
        lblQuantityPaises.setHorizontalAlignment(SwingConstants.CENTER);
        lblQuantityPaises.setBounds(50, 58, COMPONENTS_WIDTH*2, COMPONENTS_HEIGHT);
        return lblQuantityPaises;
    }

    @ComponentMethod
    public JLabel getLblListar() {
        lblListar.setText(ListarPaisesText.LBL_LISTAR.getString());
        lblListar.setHorizontalAlignment(SwingConstants.CENTER);
        lblListar.setBounds(50, 38, COMPONENTS_WIDTH / 3, COMPONENTS_HEIGHT);
        return lblListar;
    }

    @ComponentMethod
    public JButton getBtnListarNome() {
        btnListarNome.setText(ListarPaisesText.BTN_NOME.getString());
        btnListarNome.setBounds(120, 38, COMPONENTS_WIDTH / 2, COMPONENTS_HEIGHT);
        return btnListarNome;
    }

    @ComponentMethod
    public JButton getBtnListarNomeCapital(){
        btnListarNomeCapital.setText(ListarPaisesText.BTN_CAPITAL.getString());
        btnListarNomeCapital.setBounds(220, 38, COMPONENTS_WIDTH/2, COMPONENTS_HEIGHT);
        return btnListarNomeCapital;
    }

    @ComponentMethod
    public JButton getBtnListarDimensao() {
        btnListarDimensao.setText(ListarPaisesText.BTN_DIMENSION.getString());
        btnListarDimensao.setBounds(320, 38, COMPONENTS_WIDTH / 2, COMPONENTS_HEIGHT);
        return btnListarDimensao;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JTable getTable() {
        table.setEnabled(false);
        return table;
    }

    @ComponentMethod
    public JScrollPane getTxtAreaPaisesComScroll() {
        scrollPane.setBounds(25, 77, COMPONENTS_WIDTH*2+35, COMPONENTS_HEIGHT * 6);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        return scrollPane;
    }

    @ComponentMethod
    public JButton getBtnMenu() {
        btnMenu.setText(ListarPaisesText.BTN_MENU.getString());
        btnMenu.setBounds(200, 225, COMPONENTS_WIDTH / 2, COMPONENTS_HEIGHT);
        return btnMenu;
    }
}