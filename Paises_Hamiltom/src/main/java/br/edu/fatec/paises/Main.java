package br.edu.fatec.paises;

import br.edu.fatec.paises.interfaces.menu.Menu;
import br.edu.fatec.paises.repository.PaisDAO;
import br.edu.fatec.paises.services.menu.MenuServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    public static final PaisDAO paisDAO = new PaisDAO();
    public static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static final MenuServices menuServices = new MenuServices();

    public static void main(String[] args) {
        menuServices.telaApp("Menu", new Menu().montarTela());
    }
}
