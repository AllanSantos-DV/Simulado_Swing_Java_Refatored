package br.edu.fatec.paises.enums;

public enum DevelopersText {

    LBL_TITLE("Desenvolvedores");

    private final String string;

    DevelopersText(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }
}