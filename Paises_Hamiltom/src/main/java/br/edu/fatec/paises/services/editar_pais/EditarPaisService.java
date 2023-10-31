package br.edu.fatec.paises.services.editar_pais;

import br.edu.fatec.paises.interfaces.adicionar_pais.AdicionarPais;
import br.edu.fatec.paises.interfaces.editar_pais.EditarPais;
import br.edu.fatec.paises.enums.editar_pais.EditarPaisText;
import br.edu.fatec.paises.models.Pais;
import br.edu.fatec.paises.services.menu.MenuServices;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static br.edu.fatec.paises.Main.menuServices;
import static br.edu.fatec.paises.Main.paisDAO;

public class EditarPaisService {

    protected void initCmbPais(EditarPais editarPais) {
        editarPais.getCmbSelectPais().removeAllItems();
        for (Pais pais: paisDAO.getPaises()) editarPais.getCmbSelectPais().addItem(pais.getNome());
        initCmbVizinho(editarPais);
    }

    protected void initCmbVizinho(EditarPais editarPais) {
        if (editarPais.getCmbSelectPais().getSelectedItem() == null) return;
        editarPais.getCmbSelectVizinho().removeAllItems();
        Pais paisSelected = paisDAO.getPaises().get(editarPais.getCmbSelectPais().getSelectedIndex());
        for (String vizinho : getVizinhos(paisSelected, editarPais)) editarPais.getCmbSelectVizinho().addItem(vizinho);
    }

    protected List<String> getVizinhos(Pais paisSelected, EditarPais editarPais) {
        if (checkVizinhos(paisSelected,editarPais)){
            return List.of(EditarPaisText.LBL_ERROR_EMPTY_VIZINHOS.getString());
        }
        List<String> vizinhos = new ArrayList<>();
        for (Pais vizinho : paisSelected.getFronteira()) vizinhos.add(vizinho.getNome());
        return vizinhos;
    }

    protected boolean checkVizinhos(Pais paisSelected, EditarPais editarPais) {
        if (paisSelected.getFronteira().isEmpty()) {
            editarPais.getCmbSelectVizinho().setEnabled(false);
            editarPais.getBtnDeleteVizinho().setEnabled(false);
            return true;
        }
        editarPais.getCmbSelectVizinho().setEnabled(true);
        editarPais.getBtnDeleteVizinho().setEnabled(true);
        return false;
    }

    public void editarPais(EditarPais editarPais) {
        Pais pais = paisDAO.getPais(Objects.requireNonNull(editarPais.getCmbSelectPais().getSelectedItem()).toString());
        AdicionarPais adicionarPais = new AdicionarPais();
        MenuServices menuServices = new MenuServices();
        updateFrame(adicionarPais, menuServices, editarPais);
        editPaisPanel(adicionarPais, pais);
    }

    public void updateFrame(AdicionarPais adicionarPais,MenuServices menuServices, EditarPais editarPais){
        menuServices.telaClose(editarPais.getBtnEditPais());
        menuServices.telaApp(editarPais.getBtnEditPais().getText(), adicionarPais.montarTela());
    }

    public void editPaisPanel(AdicionarPais adicionarPais, Pais pais){
        JButton adicionarPaisBtnSalvar = adicionarPais.getBtnSalvar();
        ActionListener listenerSalvarPais = Arrays.stream(adicionarPaisBtnSalvar.getActionListeners()).iterator().next();
        adicionarPais.getLblTitulo().setHorizontalAlignment(SwingConstants.CENTER);
        adicionarPais.getLblTitulo().setText(EditarPaisText.LBL_TITLE.getString());
        adicionarPais.getTxtNome().setText(pais.getNome());
        adicionarPais.getTxtCapital().setText(pais.getCapital());
        adicionarPais.getTxtDimensao().setValue(pais.getDimensao());
        adicionarPaisBtnSalvar.setText(EditarPaisText.BTN_EDIT_PAIS.getString());
        adicionarPaisBtnSalvar.removeActionListener(listenerSalvarPais);
        ActionListener listenerEditarPais = e -> savePaisEdited(adicionarPaisBtnSalvar ,listenerSalvarPais,adicionarPais, pais);
        adicionarPaisBtnSalvar.addActionListener(listenerEditarPais);
    }

    public void savePaisEdited(JButton adicionarPaisBtnSalvar, ActionListener listener,AdicionarPais adicionarPais, Pais pais){
        String nome = adicionarPais.getTxtNome().getText();
        String capital = adicionarPais.getTxtCapital().getText();
        double dimensao = Double.parseDouble(adicionarPais.getTxtDimensao().getValue().toString());
        Pais newPais = new Pais(nome, capital, dimensao);
        if (camposCheck(adicionarPais, nome, capital)||paisExistente(adicionarPais, newPais, pais)) return;
        paisDAO.editPais(pais.getNome(), nome, capital, dimensao);
        adicionarPaisBtnSalvar.removeActionListener(adicionarPais.getBtnSalvar().getActionListeners()[0]);
        adicionarPaisBtnSalvar.addActionListener(listener);
        telaEditarPais(adicionarPaisBtnSalvar);
        JOptionPane.showMessageDialog(null, String.format(EditarPaisText.LBL_SUCCESS_TEXT.getString(), pais.getNome()));
    }

    public void telaEditarPais(JButton adicionarPaisBtnSalvar){
        EditarPais newInstance = new EditarPais();
        menuServices.telaClose(adicionarPaisBtnSalvar);
        menuServices.telaApp(adicionarPaisBtnSalvar.getText(), newInstance.montarTela());
    }

    public boolean camposCheck(AdicionarPais adicionarPais, String nomePais, String capitalPais) {
        if(nomePais.isEmpty() || capitalPais.isEmpty()) {
            adicionarPais.getLblSucess().setText(EditarPaisText.LBL_ERROR_EMPTY_FIELD.getString());
            return true;
        }
        return false;
    }

    public boolean paisExistente(AdicionarPais adicionarPais, Pais newPais, Pais paisOld) {
        List<Pais> paises = new ArrayList<>(paisDAO.getPaises());
        paises.remove(paisOld);
        if (paises.contains(newPais)) {
            adicionarPais.getLblSucess().setText(EditarPaisText.LBL_ERROR_EXISTING.getString());
            return true;
        }
        return false;
    }

    public void deleteVizinho(EditarPais editarPais) {
        Pais paisSelected = paisDAO.getPais(Objects.requireNonNull(editarPais.getCmbSelectPais().getSelectedItem()).toString());
        Pais vizinhoSelected = paisDAO.getPais(Objects.requireNonNull(editarPais.getCmbSelectVizinho().getSelectedItem()).toString());
        paisSelected.getFronteira().remove(vizinhoSelected);
        vizinhoSelected.getFronteira().remove(paisSelected);
        initCmbVizinho(editarPais);
        editarPais.getLblDeleteVizinho().setText(String.format(EditarPaisText.LBL_DELETE_VIZINHO.getString(), vizinhoSelected.getNome()));
    }
}