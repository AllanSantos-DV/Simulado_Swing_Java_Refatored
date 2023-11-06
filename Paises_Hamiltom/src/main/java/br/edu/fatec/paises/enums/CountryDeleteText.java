package br.edu.fatec.paises.enums;

public enum CountryDeleteText {
    LBL_TITLE("Deletar País"), LBL_SELECT_COUNTRY("Selecione o País:"),
    BTN_DELETE_COUNTRY("Deletar País"), BTN_MENU("Menu"),
    LBL_SUCCESS_TEXT("País %s deletado com sucesso!");

    private final String string;

    CountryDeleteText(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }
}