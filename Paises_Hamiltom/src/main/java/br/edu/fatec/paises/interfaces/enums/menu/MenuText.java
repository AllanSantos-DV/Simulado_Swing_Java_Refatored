package br.edu.fatec.paises.interfaces.enums.menu;

public enum MenuText {

    LBL_TITLE("Bem vindo ao Gerenciador de Países!"), BTN_NEW_PAIS("Adicionar País"),
    BTN_NEW_VIZINHO("Cadastrar Vizinho"), BTN_EDIT_PAIS("Editar País"),
    BTN_LIST_PAISES("Listar Países"), BTN_DELETE_PAIS("Deletar País");

    private final String string;

    MenuText(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }
}
