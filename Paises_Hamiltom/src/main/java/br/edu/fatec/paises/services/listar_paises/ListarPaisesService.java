package br.edu.fatec.paises.services.listar_paises;

import br.edu.fatec.paises.enums.listar_paises.ListarPaisesText;
import br.edu.fatec.paises.interfaces.listar_paises.ListarPaises;
import br.edu.fatec.paises.models.Pais;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static br.edu.fatec.paises.Main.paisDAO;

public class ListarPaisesService {

    public void listarPaises(ListarPaises listarPaises) {
        int sizeListPaises = paisDAO.getPaises().size();
        listarPaises.getLblQuantityPaises().setText(ListarPaisesText.LBL_QTD_PAISES.getString() + sizeListPaises);
        setTableModel(listarPaises, paisDAO.getPaises());
    }

    public void listarPaisesOrdenado(ListarPaises listarPaises, String buttonName) {
        List<Pais> paises = new ArrayList<>(paisDAO.getPaises());
        switch (ListarPaisesText.getEnum(buttonName)) {
            case BTN_DIMENSION -> paises.sort(Comparator.comparing(Pais::getDimensao));
            case BTN_CAPITAL -> paises.sort(Comparator.comparing(Pais::getCapital));
            default -> paises.sort(Comparator.comparing(Pais::getNome));
        }
        setTableModel(listarPaises, paises);
    }

    public void setTableModel(ListarPaises listarPaises, List<Pais> paises) {
        int sizeColumn = 0;
        getEditedTableModel(listarPaises);
        for (Pais pais : paises){
            StringBuilder vizinhos = new StringBuilder();
            if (pais.getFronteira().isEmpty()) listarPaises.getTableModel().addRow(new Object[]{pais.getNome(), pais.getCapital(), pais.getDimensao(), "Sem Vizinhos"});
            else {
                for (Pais paisVizinho : pais.getFronteira()) vizinhos.append(paisVizinho.getNome()).append(", ");
                listarPaises.getTableModel().addRow(new Object[]{pais.getNome(), pais.getCapital(), pais.getDimensao(), vizinhos});
            }
            if(vizinhos.length()>sizeColumn) sizeColumn = vizinhos.length();
        }
        getEditedTable(listarPaises, sizeColumn);
    }

    public void getEditedTable(ListarPaises listarPaises, int sizecolumn){
        int preferredWidth = 140;
        JTable table = listarPaises.getTable();
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (int i = 0; i < table.getColumnCount()-1; i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(preferredWidth);
        }
        table.getColumnModel().getColumn(table.getColumnCount()-1).setPreferredWidth(sizecolumn*6);
        table.updateUI();
    }

    public void getEditedTableModel(ListarPaises listarPaises) {
        DefaultTableModel tableModel = listarPaises.getTableModel();
        tableModel.setRowCount(0);
        tableModel.setColumnCount(0);
        tableModel.addColumn(ListarPaisesText.BTN_NOME.getString());
        tableModel.addColumn(ListarPaisesText.BTN_CAPITAL.getString());
        tableModel.addColumn(ListarPaisesText.BTN_DIMENSION.getString());
        tableModel.addColumn(ListarPaisesText.LBL_VIZINHOS.getString());
    }
}