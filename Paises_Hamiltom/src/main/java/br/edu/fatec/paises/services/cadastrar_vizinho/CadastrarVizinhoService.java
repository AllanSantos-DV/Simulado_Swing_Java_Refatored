package br.edu.fatec.paises.services.cadastrar_vizinho;

import br.edu.fatec.paises.enums.cadastrar_vizinho.CadastrarVizinhoText;
import br.edu.fatec.paises.interfaces.cadastrar_vizinho.CadastrarVizinho;
import br.edu.fatec.paises.models.Pais;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static br.edu.fatec.paises.Main.paisDAO;

public class CadastrarVizinhoService {
    private final List<Pais> vizinhos = new ArrayList<>();

    protected void updateCombs(CadastrarVizinho cadastrarVizinho) {
        cadastrarVizinho.getCmbPais().removeAllItems();
        paisDAO.getPaises().forEach(pais -> cadastrarVizinho.getCmbPais().addItem(pais.getNome()));
        updateCmbVizinho(cadastrarVizinho);
    }

    protected void updateCmbVizinho(CadastrarVizinho cadastrarVizinho) {
        cadastrarVizinho.getCmbVizinho().removeAllItems();
        Pais paisSelected = paisDAO.getPais(Objects.requireNonNull(cadastrarVizinho.getCmbPais().getSelectedItem()).toString());
        paisDAO.getPaises().stream()
                .filter(pais -> !pais.equals(paisSelected)
                        && !vizinhos.contains(pais)
                        && !paisSelected.getFronteira().contains(pais))
                .forEach(pais -> cadastrarVizinho.getCmbVizinho().addItem(pais.getNome()));
        checkEmptyCmbVizinho(cadastrarVizinho);
    }

    protected void selecionarVizinho(CadastrarVizinho cadastrarVizinho) {
        Pais vizinhoSelected = paisDAO.getPais(Objects.requireNonNull(cadastrarVizinho.getCmbVizinho().getSelectedItem()).toString());
        vizinhos.add(vizinhoSelected);
        lblPaisSelected(cadastrarVizinho, vizinhoSelected.getNome());
        updateCmbVizinho(cadastrarVizinho);
        checkEmptyCmbVizinho(cadastrarVizinho);
    }

    protected void lblPaisSelected(CadastrarVizinho cadastrarVizinho, String paisSelected) {
        cadastrarVizinho.getLblSuccess().setText(CadastrarVizinhoText.LBL_PAIS_SELECTED.getString() + paisSelected);
    }

    protected void checkEmptyCmbVizinho(CadastrarVizinho cadastrarVizinho) {
        if(cadastrarVizinho.getCmbVizinho().getItemCount() == 0) {
            cadastrarVizinho.getCmbVizinho().addItem(CadastrarVizinhoText.LBL_ERROR_EMPTY_FIELD.getString());
            cadastrarVizinho.getCmbVizinho().setEnabled(false);
            cadastrarVizinho.getBtnSelecionar().setEnabled(false);
        }
    }

    protected void updateCmbVizinhoPaisChange(CadastrarVizinho cadastrarVizinho) {
        vizinhos.clear();
        updateCmbVizinho(cadastrarVizinho);
        cadastrarVizinho.getBtnSelecionar().setEnabled(true);
        cadastrarVizinho.getCmbVizinho().setEnabled(true);
    }

    protected void addPaisesSelected(CadastrarVizinho cadastrarVizinho){
        List<Pais> vizinhosSelected = new ArrayList<>(vizinhos);
        Pais paisSelected = paisDAO.getPais(Objects.requireNonNull(cadastrarVizinho.getCmbPais().getSelectedItem()).toString());
        for (Pais paisVizinhos: vizinhosSelected) paisVizinhos.setFronteira(List.of(paisSelected));
        paisSelected.setFronteira(vizinhosSelected);
        cadastrarVizinho.getLblSuccess().setText(CadastrarVizinhoText.LBL_SUCCESS_TEXT.getString());
        vizinhos.clear();
    }
}