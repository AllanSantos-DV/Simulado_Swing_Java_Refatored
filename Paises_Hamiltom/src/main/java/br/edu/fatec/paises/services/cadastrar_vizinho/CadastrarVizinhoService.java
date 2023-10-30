package br.edu.fatec.paises.services.cadastrar_vizinho;

import br.edu.fatec.paises.enums.cadastrar_vizinho.CadastrarVizinhoText;
import br.edu.fatec.paises.models.Pais;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static br.edu.fatec.paises.Main.paisDAO;

public class CadastrarVizinhoService {
    private final List<Pais> vizinhos = new ArrayList<>();

    protected void updateCombs(@NotNull JComboBox<String> cmbPais, JComboBox<String> cmbVizinho, JButton button) {
        cmbPais.removeAllItems();
        paisDAO.getPaises().forEach(pais -> cmbPais.addItem(pais.getNome()));
        updateCmbVizinho(cmbPais, cmbVizinho, button);
    }

    protected void updateCmbVizinho(@NotNull JComboBox<String> cmbPais, @NotNull JComboBox<String> cmbVizinho, JButton button) {
        cmbVizinho.removeAllItems();
        Pais paisSelected = paisDAO.getPais(Objects.requireNonNull(cmbPais.getSelectedItem()).toString());
        paisDAO.getPaises().stream()
                .filter(pais -> !pais.equals(paisSelected)
                        && !vizinhos.contains(pais)
                        && !paisSelected.getFronteira().contains(pais))
                .forEach(pais -> cmbVizinho.addItem(pais.getNome()));
        checkEmptyCmbVizinho(cmbVizinho, button);
    }

    protected void selecionarVizinho(JLabel lblPaisSelected, JComboBox<String> cmbPais, @NotNull JComboBox<String> cmbVizinho, JButton button) {
        Pais vizinhoSelected = paisDAO.getPais(Objects.requireNonNull(cmbVizinho.getSelectedItem()).toString());
        vizinhos.add(vizinhoSelected);
        lblPaisSelected(lblPaisSelected, vizinhoSelected.getNome());
        updateCmbVizinho(cmbPais, cmbVizinho,button);
        checkEmptyCmbVizinho(cmbVizinho, button);
    }

    protected void lblPaisSelected(@NotNull JLabel lblPaisSelected, String paisSelected) {
        lblPaisSelected.setText(CadastrarVizinhoText.LBL_PAIS_SELECTED.getString() + paisSelected);
    }

    protected void checkEmptyCmbVizinho(@NotNull JComboBox<String> cmbVizinho, JButton button) {
        if(cmbVizinho.getItemCount() == 0) {
            cmbVizinho.addItem(CadastrarVizinhoText.LBL_ERROR_EMPTY_FIELD.getString());
            cmbVizinho.setEnabled(false);
            button.setEnabled(false);
        }
    }

    protected void updateCmbVizinhoPaisChange(JButton button, JComboBox<String> cmbPais, JComboBox<String> cmbVizinho) {
        vizinhos.clear();
        updateCmbVizinho(cmbPais, cmbVizinho, button);
        button.setEnabled(true);
        cmbVizinho.setEnabled(true);
    }

    protected void addPaisesSelected(JLabel lblAddPaisesSelected, @NotNull JComboBox<String> cmbPais){
        List<Pais> vizinhosSelected = new ArrayList<>(vizinhos);
        Pais paisSelected = paisDAO.getPais(Objects.requireNonNull(cmbPais.getSelectedItem()).toString());
        for (Pais paisVizinhos: vizinhosSelected) paisVizinhos.setFronteira(List.of(paisSelected));
        paisSelected.setFronteira(vizinhosSelected);
        lblAddPaisesSelected.setText(CadastrarVizinhoText.LBL_SUCCESS_TEXT.getString());
        vizinhos.clear();
    }
}
