package br.edu.fatec.paises.services.cadastrar_vizinho;

import javax.swing.*;

import static br.edu.fatec.paises.Main.paisDAO;

public class CadastrarVizinhoService {

    private void updateCmbPais(JComboBox<String> cmbPais) {
        paisDAO.getPaises().forEach(pais -> cmbPais.addItem(pais.getNome()));
    }


}
