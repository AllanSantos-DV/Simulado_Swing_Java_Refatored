package br.edu.fatec.paises.enums;

public enum CountryText {
    PRINT_FRONTIER("Vizinhos: "), PRINT_COUNTRY("Nome: %s. Capital: %s. Dimensão: %,.2f");

    private final String string;

    CountryText(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }
}