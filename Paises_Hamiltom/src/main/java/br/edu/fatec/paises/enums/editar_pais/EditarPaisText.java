package br.edu.fatec.paises.enums.editar_pais;

public enum EditarPaisText {

    LBL_TITLE("Editar País"), LBL_SELECT_PAIS("Selecione o País:"),
    LBL_SUCCESS_TEXT("País %s editado com sucesso!"), LBL_ERROR_TEXT("Erro ao editar país!"),
    LBL_ERROR_EMPTY_FIELD("Preencha todos os campos!"), LBL_ERROR_EXISTING("País já cadastrado!"),
    LBL_NOME("Nome:"), LBL_CAPITAL("Capital:"), LBL_DIMENSION("Dimensao:"),
    LBL_DELETE_VIZINHO("Vizinho (%s) deletado com sucesso!"), LBL_DELETE_VIZINHO_ERROR("Erro ao deletar vizinho!"),
    BTN_DELETE_VIZINHO("Deletar Vizinho"), BTN_EDIT_PAIS("Editar País"),
    BTN_MENU("Menu"), LBL_ERROR_EMPTY_VIZINHOS("Lista de vizinhos vazia!");

    private final String string;

    EditarPaisText(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }
}
