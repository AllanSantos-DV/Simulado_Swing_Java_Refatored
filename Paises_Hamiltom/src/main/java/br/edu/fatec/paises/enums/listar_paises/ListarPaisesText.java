package br.edu.fatec.paises.enums.listar_paises;

import java.util.Arrays;

public enum ListarPaisesText {

    LBL_TITLE("Listar Países"), LBL_QTD_PAISES("Quantidade de Países: "),
    LBL_QTD_PAISES_VALUE("0"), LBL_LISTAR("Listar por: "), LBL_VIZINHOS("Vizinhos"),
    BTN_NOME("Nome"), BTN_DIMENSION("Dimensao"),
    BTN_CAPITAL("Capital"), BTN_MENU("Menu");

    private final String string;

    ListarPaisesText(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

    public static ListarPaisesText getEnum(String string) {
        return Arrays.stream(ListarPaisesText.values())
                .filter(listarPaisesText -> listarPaisesText.getString().equals(string))
                .findFirst().orElse(null);
    }
}
