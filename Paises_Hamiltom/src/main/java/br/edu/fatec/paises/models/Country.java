package br.edu.fatec.paises.models;

import br.edu.fatec.paises.enums.CountryText;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Country {
    private final String name;
    private final String capital;
    private final double dimension;
    private final List<Country> frontier = new ArrayList<>();

    public Country(String name, String capital, double dimension) {
        this.name = name;
        this.capital = capital;
        this.dimension = dimension;
    }

    public String toString() {
        StringBuilder countryName = new StringBuilder(CountryText.PRINT_FRONTIER.getString());
        if (frontier.isEmpty())
            return String.format(CountryText.PRINT_COUNTRY.getString(), name, capital, dimension);
        for (Country country : frontier) countryName.append(country.getName()).append(", ");
        String countryAndFrontier = CountryText.PRINT_COUNTRY.getString() + "%s";
        return String.format(countryAndFrontier, name, capital, dimension, countryName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return Objects.equals(name.toLowerCase(), country.getName().toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, capital);
    }

    public String getName() {
        return name;
    }

    public String getCapital() {
        return capital;
    }

    public double getDimension() {
        return dimension;
    }

    public List<Country> getFrontier() {
        return frontier;
    }

    public void setFrontier(List<Country> frontier) {
        this.frontier.addAll(frontier);
    }
}