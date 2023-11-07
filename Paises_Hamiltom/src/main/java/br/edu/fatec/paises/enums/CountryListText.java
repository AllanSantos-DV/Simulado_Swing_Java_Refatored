package br.edu.fatec.paises.enums;

import java.util.Arrays;

public enum CountryListText {
    LBL_TITLE("Listar Países"), LBL_ORDERING_TITLE("Listar por: "),
    BTN_ORDERING_NAME("Nome"), BTN_ORDERING_CAPITAL("Capital"),
    BTN_ORDERING_DIMENSION("Dimensao"), LBL_QTD_COUNTRIES("Quantidade de Países: "),
    LBL_NEIGHBORS("Vizinhos"), MSG_LIST_NEIGHBOR_EMPTY("Lista vazia"),
    BTN_MENU("Menu");

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