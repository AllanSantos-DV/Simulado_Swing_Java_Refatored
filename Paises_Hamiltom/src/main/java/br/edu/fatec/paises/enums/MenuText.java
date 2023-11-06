package br.edu.fatec.paises.enums;

import java.util.Arrays;

public enum MenuText {
    LBL_TITLE("Bem vindo ao Gerenciador de Países!"), BTN_NEW_COUNTRY("Adicionar País"),
    BTN_NEW_NEIGHBOR("Cadastrar Vizinho"), BTN_EDIT_COUNTRY("Editar País"),
    BTN_LIST_COUNTRIES("Listar Países"), BTN_DELETE_COUNTRY("Deletar País"),
    MSG_COUNTRIES_EMPTY("Não há países cadastrados!"),
    MSG_NEW_COUNTRY("Deseja Cadastrar um novo Country?"), BTN_MENU("Menu");

    private final String string;

    MenuText(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

    public static MenuText getEnum(String string) {
        return Arrays.stream(MenuText.values())
                .filter(menuText -> menuText.getString().equals(string))
                .findFirst().orElse(null);
    }
}