package br.edu.fatec.paises.services.listar_paises;

import br.edu.fatec.paises.enums.listar_paises.ListarPaisesText;
import br.edu.fatec.paises.models.Pais;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static br.edu.fatec.paises.Main.paisDAO;

public class ListarPaisesService {

    public void listarPaises(JTable table, JLabel lblQtdPaises , DefaultTableModel tableModel) {
        int sizeListPaises = paisDAO.getPaises().size();
        lblQtdPaises.setText(ListarPaisesText.LBL_QTD_PAISES.getString() + sizeListPaises);
        setTableModel(table, tableModel, paisDAO.getPaises());
    }

    public void listarPaisesOrdenado(JTable table,DefaultTableModel tableModel, String buttonName) {
        List<Pais> paises = new ArrayList<>(paisDAO.getPaises());
        switch (ListarPaisesText.getEnum(buttonName)) {
            case BTN_DIMENSION -> paises.sort(Comparator.comparing(Pais::getDimensao));
            case BTN_CAPITAL -> paises.sort(Comparator.comparing(Pais::getCapital));
            default -> paises.sort(Comparator.comparing(Pais::getNome));
        }
        setTableModel(table, tableModel, paises);
    }

    public void setTableModel(JTable table,DefaultTableModel tableModel, List<Pais> paises) {
        int sizeColumn = 0;
        getEditedTableModel(tableModel);
        for (Pais pais : paises){
            StringBuilder vizinhos = new StringBuilder();
            if (pais.getFronteira().isEmpty()) tableModel.addRow(new Object[]{pais.getNome(), pais.getCapital(), pais.getDimensao(), "Sem Vizinhos"});
            else {
                for (Pais paisVizinho : pais.getFronteira()) vizinhos.append(paisVizinho.getNome()).append(", ");
                tableModel.addRow(new Object[]{pais.getNome(), pais.getCapital(), pais.getDimensao(), vizinhos});
            }
            if(vizinhos.length()>sizeColumn) sizeColumn = vizinhos.length();
        }
        getEditedTable(table, sizeColumn);
    }

    public void getEditedTable(JTable table, int sizecolumn){
        int preferredWidth = 140;
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (int i = 0; i < table.getColumnCount()-1; i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(preferredWidth);
        }
        table.getColumnModel().getColumn(table.getColumnCount()-1).setPreferredWidth(sizecolumn*6);
        table.updateUI();
    }

    public void getEditedTableModel(DefaultTableModel tableModel) {
        tableModel.setRowCount(0);
        tableModel.setColumnCount(0);
        tableModel.addColumn(ListarPaisesText.BTN_NOME.getString());
        tableModel.addColumn(ListarPaisesText.BTN_CAPITAL.getString());
        tableModel.addColumn(ListarPaisesText.BTN_DIMENSION.getString());
        tableModel.addColumn(ListarPaisesText.LBL_VIZINHOS.getString());
    }
}