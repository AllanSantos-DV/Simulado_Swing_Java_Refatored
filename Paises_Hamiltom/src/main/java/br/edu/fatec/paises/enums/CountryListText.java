package br.edu.fatec.paises.enums;

import java.util.Arrays;

public enum CountryListText {

    LBL_TITLE("Listar Países"), LBL_QTD_COUNTRIES("Quantidade de Países: "),
    LBL_QTD_COUNTRIES_VALUE("0"), LBL_ORDERING_TITLE("Listar por: "), LBL_NEIGHBORS("Vizinhos"),
    BTN_NAME("Nome"), BTN_DIMENSION("Dimensao"),
    BTN_CAPITAL("Capital"), BTN_MENU("Menu");

    private final String string;

    CountryListText(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

    public static CountryListText getEnum(String string) {
        return Arrays.stream(CountryListText.values())
                .filter(countryListText -> countryListText.getString().equals(string))
                .findFirst().orElse(null);
    }
}