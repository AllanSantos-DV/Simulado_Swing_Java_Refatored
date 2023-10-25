package br.edu.fatec.paises.interfaces.enums.listar_paises;

public enum ListarPaisesText {

    LBL_TITLE("Listar Países"), LBL_QTD_PAISES("Quantidade de Países: "),
    LBL_QTD_PAISES_VALUE("0"),
    LBL_LISTAR("Listar por: "), BTN_NOME("Nome"),
    BTN_DIMENSION("Dimensao"), BTN_MENU("Menu");

    private final String string;

    ListarPaisesText(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }
}
