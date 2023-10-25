package br.edu.fatec.paises.interfaces.enums.cadastrar_vizinho;

public enum CadastrarVizinhoText {

    LBL_TITLE_REGISTER("Cadastrar Vizinho"), LBL_SUCCESS_TEXT("Vizinho cadastrado com sucesso!"),
    LBL_PAIS("Selecione o Pais:"), LBL_VIZINHO("Selecione o Vizinho:"),
    BTN_SELECT("Selecionar"), BTN_REGISTER("Cadastrar"), BTN_MENU("Menu");

    private final String string;

    CadastrarVizinhoText(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }
}
