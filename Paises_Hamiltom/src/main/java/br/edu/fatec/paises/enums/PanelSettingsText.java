package br.edu.fatec.paises.enums;

public enum PanelSettingsText {
    MSG_CONFIRM("Deseja voltar ao menu?"), MSG_WARNING("Dados não salvos serão perdidos!"),
    MSG_TITLE_CONFIRM("Voltar ao menu"), BTN_CONFIRM("Sim"), BTN_CANCEL("Não"),
    MSG_ERROR("Erro ao montar tela: %s"), TELA_MENU("Menu");

    private final String string;

    PanelSettingsText(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }
}