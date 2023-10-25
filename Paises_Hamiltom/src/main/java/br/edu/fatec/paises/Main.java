package br.edu.fatec.paises;

import br.edu.fatec.paises.interfaces.listar_paises.ListarPaises;
import br.edu.fatec.paises.services.menu.MenuServices;

public class Main {
    public static void main(String[] args) {
        new MenuServices().telaApp("Cadastrar Vizinho", new ListarPaises().montarTela());
    }
}
