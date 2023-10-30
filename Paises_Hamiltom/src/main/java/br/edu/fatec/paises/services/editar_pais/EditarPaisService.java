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

    protected void initCmbPais(JComboBox<String> cmbPais, JComboBox<String> cmbVizinhos, JButton button) {
        cmbPais.removeAllItems();
        for (Pais pais: paisDAO.getPaises()) cmbPais.addItem(pais.getNome());
        initCmbVizinho(cmbVizinhos, cmbPais, button);
    }

    protected void initCmbVizinho(JComboBox<String> cmbVizinho, JComboBox<String> cmbPais, JButton button) {
        if (cmbPais.getSelectedItem() == null) return;
        cmbVizinho.removeAllItems();
        Pais paisSelected = paisDAO.getPaises().get(cmbPais.getSelectedIndex());
        for (String vizinho : getVizinhos(paisSelected, cmbVizinho, button)) cmbVizinho.addItem(vizinho);
    }

    protected List<String> getVizinhos(Pais paisSelected, JComboBox<String> cmbVizinhos, JButton button) {
        if (checkVizinhos(paisSelected,cmbVizinhos, button)){
            return List.of(EditarPaisText.LBL_ERROR_EMPTY_VIZINHOS.getString());
        }
        List<String> vizinhos = new ArrayList<>();
        for (Pais vizinho : paisSelected.getFronteira()) vizinhos.add(vizinho.getNome());
        return vizinhos;
    }

    protected boolean checkVizinhos(Pais paisSelected, JComboBox<String> cmbVizinho, JButton button) {
        if (paisSelected.getFronteira().isEmpty()) {
            cmbVizinho.setEnabled(false);
            button.setEnabled(false);
            return true;
        }
        cmbVizinho.setEnabled(true);
        button.setEnabled(true);
        return false;
    }

    public void editarPais(JComboBox<String> cmbPais, JButton button) {
        Pais pais = paisDAO.getPaises().get(cmbPais.getSelectedIndex());
        AdicionarPais adicionarPais = new AdicionarPais();
        MenuServices menuServices = new MenuServices();
        updateFrame(adicionarPais, menuServices, button);
        editPaisPanel(adicionarPais, pais);
    }

    public void updateFrame(AdicionarPais adicionarPais,MenuServices menuServices, JButton button){
        menuServices.telaClose(button);
        menuServices.telaApp(button.getText(), adicionarPais.montarTela());
    }

    public void editPaisPanel(AdicionarPais adicionarPais, Pais pais){
        JButton button = adicionarPais.getBtnSalvar();
        ActionListener listenerSalvarPais = Arrays.stream(button.getActionListeners()).iterator().next();
        adicionarPais.getLblTitulo().setHorizontalAlignment(SwingConstants.CENTER);
        adicionarPais.getLblTitulo().setText(EditarPaisText.LBL_TITLE.getString());
        adicionarPais.getTxtNome().setText(pais.getNome());
        adicionarPais.getTxtCapital().setText(pais.getCapital());
        adicionarPais.getTxtDimensao().setValue(pais.getDimensao());
        button.setText(EditarPaisText.BTN_EDIT_PAIS.getString());
        button.removeActionListener(listenerSalvarPais);
        ActionListener listenerEditarPais = e -> savePaisEdited(button ,listenerSalvarPais,adicionarPais, pais);
        button.addActionListener(listenerEditarPais);
    }

    public void savePaisEdited(JButton button, ActionListener listener,AdicionarPais adicionarPais, Pais pais){
        String nome = adicionarPais.getTxtNome().getText();
        String capital = adicionarPais.getTxtCapital().getText();
        double dimensao = Double.parseDouble(adicionarPais.getTxtDimensao().getValue().toString());
        Pais newPais = new Pais(nome, capital, dimensao);
        if (camposCheck(adicionarPais, nome, capital)||paisExistente(adicionarPais, newPais, pais)) return;
        paisDAO.editPais(pais.getNome(), nome, capital, dimensao);
        button.removeActionListener(adicionarPais.getBtnSalvar().getActionListeners()[0]);
        button.addActionListener(listener);
        telaEditarPais(button);
        JOptionPane.showMessageDialog(null, String.format(EditarPaisText.LBL_SUCCESS_TEXT.getString(), pais.getNome()));
    }

    public void telaEditarPais(JButton button){
        EditarPais newInstance = new EditarPais();
        menuServices.telaClose(button);
        menuServices.telaApp(button.getText(), newInstance.montarTela());
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

    public void deleteVizinho(JLabel lbJLabel, JComboBox<String> cmbPais, JComboBox<String> cmbVizinho, JButton button) {
        Pais paisSelected = paisDAO.getPais(Objects.requireNonNull(cmbPais.getSelectedItem()).toString());
        Pais vizinhoSelected = paisDAO.getPais(Objects.requireNonNull(cmbVizinho.getSelectedItem()).toString());
        paisSelected.getFronteira().remove(vizinhoSelected);
        vizinhoSelected.getFronteira().remove(paisSelected);
        initCmbVizinho(cmbVizinho, cmbPais, button);
        lbJLabel.setText(String.format(EditarPaisText.LBL_DELETE_VIZINHO.getString(), vizinhoSelected.getNome()));
    }
}
