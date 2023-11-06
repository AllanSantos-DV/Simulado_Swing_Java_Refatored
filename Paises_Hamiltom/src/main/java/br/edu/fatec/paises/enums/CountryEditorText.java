package br.edu.fatec.paises.enums;

public enum CountryEditorText {

    LBL_TITLE("Editar País"), LBL_SELECT_COUNTRY("Selecione o País:"),
    LBL_SUCCESS_TEXT("País %s editado com sucesso!"), LBL_TITLE_PANE_CONFIRM("Confirmar Edição"),
    LBL_CONFIRM_DELETE("Deseja deletar o país %s?"), LBL_DELETE_NEIGHBOR("Vizinho (%s) deletado com sucesso!"),
    BTN_DELETE_NEIGHBOR("Deletar Vizinho"), BTN_EDIT_COUNTRY("Editar País"),
    BTN_DELETE("Deletar"), BTN_CANCEL("Cancelar"), BTN_MENU("Menu"),
    LBL_ERROR_EMPTY_NEIGHBORS("Lista de vizinhos vazia!");

    private final String string;

    CountryEditorText(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }
}