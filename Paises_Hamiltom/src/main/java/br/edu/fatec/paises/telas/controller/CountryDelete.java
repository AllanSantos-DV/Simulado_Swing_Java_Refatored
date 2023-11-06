package br.edu.fatec.paises.telas.controller;

import br.edu.fatec.paises.enums.CountryDeleteText;
import br.edu.fatec.paises.models.Country;
import br.edu.fatec.paises.telas.CountryDeleteScreen;

import java.util.Objects;

import static br.edu.fatec.paises.Main.COUNTRY_DAO;

public class CountryDelete {

    protected void initCmb(CountryDeleteScreen countryDeleteScreen) {
        countryDeleteScreen.getCmbCountrySelected().removeAllItems();
        for (Country country : COUNTRY_DAO.getCountries()) countryDeleteScreen.getCmbCountrySelected().addItem(country.getName());
    }

    protected void deleteCountry(CountryDeleteScreen countryDeleteScreen) {
        String countryName = Objects.requireNonNull(countryDeleteScreen.getCmbCountrySelected().getSelectedItem()).toString();
        Country removedCountry = COUNTRY_DAO.getCountry(countryName);
        if (!removedCountry.getFrontier().isEmpty()) {
            for (Country country : removedCountry.getFrontier()) country.getFrontier().remove(removedCountry);
        }
        COUNTRY_DAO.removeCountry(countryName);
        initCmb(countryDeleteScreen);
        countryDeleteScreen.getLblDeleteCountry().setText(String.format(CountryDeleteText.LBL_SUCCESS_TEXT.getString(), countryName));
    }
}