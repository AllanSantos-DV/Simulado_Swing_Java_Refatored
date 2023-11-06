package br.edu.fatec.paises.enums;

public enum NeighborRegistrationText {

    LBL_TITLE_REGISTER("Cadastrar Vizinho"), LBL_SUCCESS_TEXT("Vizinhos selecionados cadastrado com sucesso!"),
    LBL_TITLE_CHANGE("Alterar Country"), MSG_CHANGE_COMBOBOX("Deseja Alterar o Country selecionado ?"),
    MSG_WARNING("Lista de Vizinhos Selecionado será limpa!"), LBL_COUNTRY_SELECTED("Country Selecionado: "),
    LBL_ERROR_EMPTY_FIELD("Lista de vizinhos vazia!"), LBL_CLEAR_LIST_CHANGE("Lista de vizinhos limpa!"),
    LBL_COUNTRY("Selecione o Pais:"), LBL_NEIGHBOR("Selecione o Vizinho:"),
    BTN_CHANGE("Alterar"), BTN_CANCEL("Cancelar"),
    BTN_SELECT("Selecionar"), BTN_REGISTER("Cadastrar"), BTN_MENU("Menu");

    private final String string;

    NeighborRegistrationText(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }
}