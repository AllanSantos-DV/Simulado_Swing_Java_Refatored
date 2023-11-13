package br.edu.fatec.paises.enums;

public enum CountryManagerText {

    LBL_TITLE("Gerenciar País"), LBL_SELECT_COUNTRY("Selecione o País:"),
    BTN_DELETE_COUNTRY("Deletar País"), LBL_ERROR_EMPTY_NEIGHBORS("Lista de vizinhos vazia!"),
    BTN_DELETE_NEIGHBOR("Deletar Vizinho"), LBL_DELETE_COUNTRY("País %s deletado com sucesso!"),
    LBL_SUCCESS_TEXT("País %s editado com sucesso!"), BTN_EDIT_COUNTRY("Editar País"),
    BTN_MENU("Menu"), LBL_OPTION_PANE_TITLE_PANE_CONFIRM("Deletar Pais?"),
    MSG_OPTION_PANE_CONFIRM_DELETE("Deseja deletar o país %s?"), BTN_OPTION_PANE_DELETE("Deletar"),
    BTN_OPTION_PANE_CANCEL("Cancelar"), LBL_OPTION_PANE_TITLE("Aviso!"),
    MSG_OPTION_PANE_LIST_COUNTRY_EMPTY("Lista de países vazia!"), MSG_OPTION_PANE_BACK_MENU("Voltando ao Menu...");

    private final String string;

    CountryManagerText(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }
}