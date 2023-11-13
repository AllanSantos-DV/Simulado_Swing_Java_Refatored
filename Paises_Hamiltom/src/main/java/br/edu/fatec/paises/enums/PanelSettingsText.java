package br.edu.fatec.paises.enums;

public enum PanelSettingsText {
    MSG_CONFIRM("Deseja Continuar?"), MSG_WARNING("Dados não salvos serão perdidos!"),
    BTN_CONFIRM("Sim"), BTN_CANCEL("Não"), MSG_ERROR("Erro ao montar tela: %s");

    private final String string;

    PanelSettingsText(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }
}