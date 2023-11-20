package br.edu.fatec.paises.repository;

import br.edu.fatec.paises.models.Country;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CountryDAO {
    private static final List<Country> COUNTRY_LIST_ORDERED = new LinkedList<>();
    private static final Map<String, Country> COUNTRIES = new LinkedHashMap<>();

    public CountryDAO() {
        initCountries();
    }

    private void initCountries() {
        save(new Country("Brasil", "Brasília", 8516000));
        save(new Country("Argentina", "Buenos Aires", 2780000));
        save(new Country("Uruguai", "Montevidéu", 176000));
        save(new Country("Paraguai", "Assunção", 406752));
        save(new Country("Bolívia", "Sucre", 1098581));
        save(new Country("Peru", "Lima", 1285216));
        save(new Country("Colômbia", "Bogotá", 1141748));
        save(new Country("Venezuela", "Caracas", 916445));
        save(new Country("Guiana", "Georgetown", 214969));
        save(new Country("Suriname", "Paramaribo", 163820));
        save(new Country("Guiana Francesa", "Caiena", 83534));
        save(new Country("Equador", "Quito", 283561));
        save(new Country("Chile", "Santiago", 756102));
        save(new Country("Ilhas Malvinas", "Stanley", 12173));
    }

    public void save(Country country) {
        COUNTRIES.put(country.getName(), country);
        COUNTRY_LIST_ORDERED.add(country);
    }

    public void editCountry(String oldCountryName, Country country) {
        Country oldCountry = COUNTRIES.remove(oldCountryName);
        Country newCountry = new Country(country.getName(), country.getCapital(), country.getDimension());
        if (!oldCountry.getFrontier().isEmpty()) {
            newCountry.setFrontier(oldCountry.getFrontier());
            for (Country neighbor : newCountry.getFrontier())
                neighbor.getFrontier().set(neighbor.getFrontier().indexOf(oldCountry), newCountry);
        }
        COUNTRIES.put(country.getName(), newCountry);
        COUNTRY_LIST_ORDERED.set(COUNTRY_LIST_ORDERED.indexOf(oldCountry), newCountry);
    }

    public void deleteByName(String countryName) {
        Country oldCountry = COUNTRIES.remove(countryName);
        COUNTRY_LIST_ORDERED.remove(oldCountry);
    }

    public Country findByName(String countryName) {
        return COUNTRIES.get(countryName);
    }

    public List<Country> findAll() {
        return COUNTRY_LIST_ORDERED;
    }
}