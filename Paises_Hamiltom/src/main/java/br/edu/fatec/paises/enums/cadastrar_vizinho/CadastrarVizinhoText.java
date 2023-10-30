package br.edu.fatec.paises.enums.cadastrar_vizinho;

public enum CadastrarVizinhoText {

    LBL_TITLE_REGISTER("Cadastrar Vizinho"), LBL_SUCCESS_TEXT("Vizinhos selecioandos cadastrado com sucesso!"),
    LBL_SUCCESS_TEXT_ERROR("Erro ao cadastrar vizinho!"), LBL_PAIS_SELECTED("Pais Selecionado: "),
    LBL_ERROR_EMPTY_FIELD("Lista de vizinhos vazia!"),
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
