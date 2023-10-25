package br.edu.fatec.paises.interfaces.enums.editar_pais;

public enum EditarPaisText {

    LBL_TITLE("Editar País"), LBL_SELECT_PAIS("Selecione o País:"),
    LBL_SUCCESS_TEXT("País editado com sucesso!"), LBL_ERROR_TEXT("Erro ao editar país!"),
    LBL_ERROR_EMPTY_FIELD("Preencha todos os campos!"), LBL_ERROR_EXISTING("País já cadastrado!"),
    LBL_NOME("Nome:"), LBL_CAPITAL("Capital:"), LBL_DIMENSION("Dimensao:"),
    LBL_DELETE_VIZINHO("Vizinho deletado com sucesso!"), LBL_DELETE_VIZINHO_ERROR("Erro ao deletar vizinho!"),
    BTN_DELETE_VIZINHO("Deletar Vizinho"), BTN_EDIT_PAIS("Editar País"),
    BTN_MENU("Menu");

    private final String string;

    EditarPaisText(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }
}
