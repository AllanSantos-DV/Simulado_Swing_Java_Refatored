package br.edu.fatec.paises.enums.models;

public enum PaisText {

    PRINT_FRONTIER("Vizinhos: "), PRINT_PAIS("Nome: %s. Capital: %s. Dimensão: %,.2f");

    private final String string;

    PaisText(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }
}
