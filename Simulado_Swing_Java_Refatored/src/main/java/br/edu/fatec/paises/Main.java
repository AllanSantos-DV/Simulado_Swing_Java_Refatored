package br.edu.fatec.paises;

import br.edu.fatec.paises.app_screens_and_controls.controller.Menu;
import br.edu.fatec.paises.enums.AppText;
import br.edu.fatec.paises.repository.CountryDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    public static final CountryDAO COUNTRY_DAO = new CountryDAO();
    public static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.appScreen(AppText.SCREEN_NAME_MENU.getString(), menu.mountScreen());
        logger.info(AppText.LOGGER_INIT_APP.getString());
    }
}