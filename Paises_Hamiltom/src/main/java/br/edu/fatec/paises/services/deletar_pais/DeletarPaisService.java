package br.edu.fatec.paises.services.deletar_pais;

import br.edu.fatec.paises.enums.deletar_pais.DeletarPaisText;
import br.edu.fatec.paises.interfaces.deletar_pais.DeletarPais;
import br.edu.fatec.paises.models.Pais;

import java.util.Objects;

import static br.edu.fatec.paises.Main.paisDAO;

public class DeletarPaisService {

    protected void initCmb(DeletarPais deletarPais) {
        deletarPais.getCmbSelectPais().removeAllItems();
        for (Pais pais : paisDAO.getPaises()) deletarPais.getCmbSelectPais().addItem(pais.getNome());
    }

    protected void deletePais(DeletarPais deletarPais) {
        String paisName = Objects.requireNonNull(deletarPais.getCmbSelectPais().getSelectedItem()).toString();
        Pais removedPais = paisDAO.getPais(paisName);
        if (!removedPais.getFronteira().isEmpty()) {
            for (Pais pais : removedPais.getFronteira()) pais.getFronteira().remove(removedPais);
        }
        paisDAO.removePais(paisName);
        initCmb(deletarPais);
        deletarPais.getLblDeletePais().setText(String.format(DeletarPaisText.LBL_SUCCESS_TEXT.getString(), paisName));
    }
}