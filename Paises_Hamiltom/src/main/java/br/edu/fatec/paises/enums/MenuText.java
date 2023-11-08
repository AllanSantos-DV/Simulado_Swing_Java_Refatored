package br.edu.fatec.paises.enums;

import java.util.Arrays;

public enum MenuText {
    LBL_TITLE("Bem vindo ao Gerenciador de Países!"), BTN_NEW_COUNTRY("Adicionar País"),
    BTN_REGISTER_NEIGHBOR("Cadastrar Vizinho"), BTN_MANAGE_COUNTRY("Gerenciar País"),
    BTN_LIST_COUNTRIES("Listar Países"), LBL_NAME_GITHUB("<html>Projeto disponivel no <a href=\"\">GitHub</a></html>"),
    LBL_DEVELOPERS("Grupo de Desenvolvedores"), MSG_OPTION_PANE_COUNTRIES_EMPTY("Não há países cadastrados!"),
    MSG_OPTION_PANE_NEW_COUNTRY("Deseja Cadastrar um novo Pais?"), BTN_OPTION_PANE_MENU("Menu"),
     LINK_GITHUB("https://github.com/AllanSantos-DV/Simulado_Swing_Java_Refatored"),
    MSG_ERROR("Erro ao abrir o link do GitHub!");

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