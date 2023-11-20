package br.edu.fatec.paises.models;

import br.edu.fatec.paises.enums.AppText;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Country {
    private final String name;
    private final String capital;
    private final double dimension;
    private final List<Country> frontier = new ArrayList<>();

    // Constructors

    public Country(String name, String capital, double dimension) {
        this.name = name;
        this.capital = capital;
        this.dimension = dimension;
    }

    // Equals and HashCode

    public boolean countryEquals(Country country) {
        return Objects.equals(name.toLowerCase(), country.getName().toLowerCase())
                && Objects.equals(capital.toLowerCase(), country.getCapital().toLowerCase());
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
        return Objects.hash(name.toLowerCase());
    }

    // Getters and Setters

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

    // toString

    public String toString() {
        StringBuilder countryName = new StringBuilder(AppText.PRINT_FRONTIER.getString());
        if (frontier.isEmpty())
            return String.format(AppText.PRINT_COUNTRY.getString(), name, capital, dimension);
        for (Country country : frontier) countryName.append(country.getName()).append(", ");
        String countryAndFrontier = AppText.PRINT_COUNTRY.getString() + "%s";
        return String.format(countryAndFrontier, name, capital, dimension, countryName);
    }

    // Methods

    public Country getFormattedCountry() {
        String nameCountry = this.name.substring(0, 1).toUpperCase() + this.name.substring(1);
        String capitalCountry = this.capital.substring(0, 1).toUpperCase() + this.capital.substring(1);
        return new Country(nameCountry, capitalCountry, dimension);
    }
}