package br.edu.fatec.paises.services.deletar_pais;

import br.edu.fatec.paises.enums.deletar_pais.DeletarPaisText;
import br.edu.fatec.paises.models.Pais;

import javax.swing.*;

import java.util.Objects;

import static br.edu.fatec.paises.Main.paisDAO;

public class DeletarPaisService {

    protected void initCmb(JComboBox<String> cmbPais) {
        cmbPais.removeAllItems();
        for (Pais pais : paisDAO.getPaises()) cmbPais.addItem(pais.getNome());
    }

    protected void deletePais(JComboBox<String> cmbPais, JLabel lblDeletePais) {
        String paisName = Objects.requireNonNull(cmbPais.getSelectedItem()).toString();
        Pais removedPais = paisDAO.getPais(paisName);
        if (!removedPais.getFronteira().isEmpty()) {
            for (Pais pais : removedPais.getFronteira()) pais.getFronteira().remove(removedPais);
        }
        paisDAO.removePais(paisName);
        initCmb(cmbPais);
        lblDeletePais.setText(String.format(DeletarPaisText.LBL_SUCCESS_TEXT.getString(), paisName));
    }
}
