package br.edu.fatec.paises;

import br.edu.fatec.paises.app_screens_and_controls.screens.MenuScreen;
import br.edu.fatec.paises.repository.CountryDAO;
import br.edu.fatec.paises.app_screens_and_controls.controller.Menu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    public static final CountryDAO COUNTRY_DAO = new CountryDAO();
    public static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static final Menu MENU = new Menu();

    public static void main(String[] args) {
        MENU.appScreen("Menu", new MenuScreen().mountScreen());
    }
}
