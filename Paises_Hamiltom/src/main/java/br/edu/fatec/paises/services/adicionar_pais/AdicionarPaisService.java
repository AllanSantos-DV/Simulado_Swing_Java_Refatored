package br.edu.fatec.paises.services.adicionar_pais;

import br.edu.fatec.paises.interfaces.adicionar_pais.AdicionarPais;
import br.edu.fatec.paises.interfaces.enums.adicionar_paises.AdicionarPaisText;

import static br.edu.fatec.paises.Main.paisDAO;

public class AdicionarPaisService {

    public void limparCampos(AdicionarPais newPais) {
        newPais.getTxtNome().setText("");
        newPais.getTxtCapital().setText("");
        newPais.getTxtDimensao().setValue(0);
        newPais.getLblSucess().setText("");
    }

    public void adicionarPais(AdicionarPais adicionarPais) {
        String name = adicionarPais.getLblNome().getText();
        String capital = adicionarPais.getLblCapital().getText();
        int size = (int) adicionarPais.getTxtDimensao().getValue();
        if (camposCheck(adicionarPais, name, capital)||paisExistente(adicionarPais, name)) return;
        paisDAO.addPais(name, capital, size);
        limparCampos(adicionarPais);
        labelsalvarPais(adicionarPais, name);
    }

    public void labelsalvarPais(AdicionarPais newPais, String nome) {
        AdicionarPaisText.STRING_PAIS.setString(nome);
        newPais.getLblSucess().setText(AdicionarPaisText.LBL_SUCCESS_TEXT.getString());
    }


    public boolean camposCheck(AdicionarPais newPais, String nome, String capital) {
        if(nome.isEmpty() || capital.isEmpty()) {
            labelCamposVazios(newPais);
            return true;
        }
        return false;
    }

    public boolean paisExistente(AdicionarPais newPais, String nome) {
        if (paisDAO.getPaises().stream().anyMatch(pais -> pais.getNome().equalsIgnoreCase(nome))) {
            labelPaisExistente(newPais);
            return true;
        }
        return false;
    }

    public void labelPaisExistente(AdicionarPais newPais) {
        newPais.getLblSucess().setText("Pais já existe.");
    }

    public void labelCamposVazios(AdicionarPais newPais) {
        newPais.getLblSucess().setText("Campos 'Nome' e 'Capital' são obrigatórios.");
    }


}
