package br.edu.fatec.paises.enums.menu;

import java.util.Arrays;

public enum MenuText {
    LBL_TITLE("Bem vindo ao Gerenciador de Países!"), BTN_NEW_PAIS("Adicionar País"),
    BTN_NEW_VIZINHO("Cadastrar Vizinho"), BTN_EDIT_PAIS("Editar País"),
    BTN_LIST_PAISES("Listar Países"), BTN_DELETE_PAIS("Deletar País"),
    MSG_PAISES_EMPTY("Não há países cadastrados!"), MSG_NOVO_PAIS("Deseja Cadastrar um novo Pais?"),
    BTN_MENU("Menu");

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
