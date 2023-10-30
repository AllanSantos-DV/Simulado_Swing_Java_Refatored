package br.edu.fatec.paises.enums.deletar_pais;

public enum DeletarPaisText {
    LBL_TITLE("Deletar País"), LBL_SELECT_PAIS("Selecione o País:"),
    BTN_DELETE_PAIS("Deletar País"), BTN_MENU("Menu"),
    LBL_SUCCESS_TEXT("País %s deletado com sucesso!"), LBL_ERROR_TEXT("Erro ao deletar país!");

    private final String string;

    DeletarPaisText(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }
}
