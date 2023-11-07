package br.edu.fatec.paises.enums;

public enum NeighborRegistrationText {
    LBL_TITLE_REGISTER("Cadastrar Vizinho"), LBL_COUNTRY("Selecione o Pais:"),
    LBL_NEIGHBOR("Selecione o Vizinho:"), BTN_SELECT("Selecionar"),
    LBL_SUCCESS_TEXT("Vizinhos selecionados cadastrado com sucesso!"), LBL_CLEAR_LIST_CHANGE("Lista de vizinhos limpa!"),
    LBL_COUNTRY_SELECTED("Pais Selecionado: "), LBL_ERROR_EMPTY_FIELD("Lista de vizinhos vazia!"),
    BTN_REGISTER("Cadastrar"), BTN_MENU("Menu"),
    LBL_OPTION_PANE_TITLE_CHANGE("Alterar Pais"), MSG_OPTION_PANE_CHANGE_COMBOBOX("Deseja Alterar o Pais selecionado ?"),
    MSG_OPTION_PANE_WARNING("Lista de Vizinhos Selecionado será limpa!"), BTN_OPTION_PANE_CHANGE("Alterar"),
    BTN_OPTION_PANE_CANCEL("Cancelar");

    private final String string;

    NeighborRegistrationText(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }
}