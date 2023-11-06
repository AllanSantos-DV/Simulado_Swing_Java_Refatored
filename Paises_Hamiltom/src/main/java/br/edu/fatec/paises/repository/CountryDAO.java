package br.edu.fatec.paises.repository;

import br.edu.fatec.paises.models.Country;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CountryDAO {
    private static final List<Country> COUNTRY_LIST_ORDERED = new LinkedList<>();
    private static final Map<String, Country> COUNTRIES = new LinkedHashMap<>();
    public CountryDAO(){
        addCountry("Brasil", "Brasilia", 8516000);
        addCountry("Argentina", "Buenos Aires", 2780000);
        addCountry("Uruguai", "Montevidéu", 176000);
        addCountry("Paraguai", "Assunção", 406752);
        addCountry("Bolívia", "Sucre", 1098581);
        addCountry("Peru", "Lima", 1285216);
        addCountry("Colômbia", "Bogotá", 1141748);
        addCountry("Venezuela", "Caracas", 916445);
        addCountry("Guiana", "Georgetown", 214969);
        addCountry("Suriname", "Paramaribo", 163820);
        addCountry("Guiana Francesa", "Caiena", 83534);
        addCountry("Equador", "Quito", 283561);
        addCountry("Chile", "Santiago", 756102);
        addCountry("Ilhas Malvinas", "Stanley", 12173);
    }

    private void addCountry(String name, String capital, double dimension) {
        addCountry(new Country(name, capital, dimension));
    }

    public void addCountry(Country country) {
        COUNTRIES.put(country.getName(), country);
        COUNTRY_LIST_ORDERED.add(country);
    }

    public void editCountry(String nameOldCountry, Country country) {
        Country oldCountry = COUNTRIES.remove(nameOldCountry);
        Country newCountry = new Country(country.getName(), country.getCapital(), country.getDimension());
        if (!oldCountry.getFrontier().isEmpty()) {
            newCountry.setFrontier(oldCountry.getFrontier());
            for(Country neighbor : newCountry.getFrontier()) neighbor.getFrontier().set(neighbor.getFrontier().indexOf(oldCountry), newCountry);
        }
        COUNTRIES.put(country.getName(), newCountry);
        COUNTRY_LIST_ORDERED.set(COUNTRY_LIST_ORDERED.indexOf(oldCountry), newCountry);
    }

    public void removeCountry(String country) {
        Country oldCountry = COUNTRIES.remove(country);
        COUNTRY_LIST_ORDERED.remove(oldCountry);
    }

    public Country getCountry(String country) {
        return COUNTRIES.get(country);
    }

    public List<Country> getCountries() {
        return COUNTRY_LIST_ORDERED;
    }
}