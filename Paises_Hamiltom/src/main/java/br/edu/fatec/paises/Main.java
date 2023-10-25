package br.edu.fatec.paises;

import br.edu.fatec.paises.interfaces.menu.Menu;
import br.edu.fatec.paises.services.menu.MenuServices;

public class Main {
    public static void main(String[] args) {
        new MenuServices().telaApp("Menu", new Menu().montarTela());
    }
}
