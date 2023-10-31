package br.edu.fatec.paises.services.adicionar_pais;

import br.edu.fatec.paises.interfaces.adicionar_pais.AdicionarPais;
import br.edu.fatec.paises.enums.adicionar_paises.AdicionarPaisText;
import br.edu.fatec.paises.models.Pais;

import static br.edu.fatec.paises.Main.paisDAO;

public class AdicionarPaisService {

    public void limparCampos(AdicionarPais newPais) {
        newPais.getTxtNome().setText("");
        newPais.getTxtCapital().setText("");
        newPais.getTxtDimensao().setValue(0);
        newPais.getLblSucess().setText("");
    }

    public void adicionarPais(AdicionarPais adicionarPais) {
        String name = adicionarPais.getTxtNome().getText();
        String capital = adicionarPais.getTxtCapital().getText();
        double size = Double.parseDouble(adicionarPais.getTxtDimensao().getValue().toString());
        Pais newPais = new Pais(name, capital, size);
        if (camposCheck(adicionarPais, name, capital)||paisExistente(adicionarPais, newPais)) return;
        paisDAO.addPais(newPais);
        limparCampos(adicionarPais);
        labelsalvarPais(adicionarPais, name);
    }

    public void labelsalvarPais(AdicionarPais adicionarPais, String nome) {
        adicionarPais.getLblSucess().setText(String.format(AdicionarPaisText.LBL_SUCCESS_TEXT.getString(), nome));
    }


    public boolean camposCheck(AdicionarPais adicionarPais, String nomePais, String capitalPais) {
        if(nomePais.isEmpty() || capitalPais.isEmpty()) {
            labelCamposVazios(adicionarPais);
            return true;
        }
        return false;
    }

    public boolean paisExistente(AdicionarPais adicionarPais, Pais newPais) {
        if (paisDAO.getPaises().contains(newPais)) {
            labelPaisExistente(adicionarPais);
            return true;
        }
        return false;
    }

    public void labelPaisExistente(AdicionarPais adicionarPais) {
        adicionarPais.getLblSucess().setText(AdicionarPaisText.LBL_ERROR_EXISTING.getString());
    }

    public void labelCamposVazios(AdicionarPais adicionarPais) {
        adicionarPais.getLblSucess().setText(AdicionarPaisText.LBL_ERROR_EMPTY_FIELD.getString());
    }
}