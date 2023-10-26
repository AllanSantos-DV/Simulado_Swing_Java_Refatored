package br.edu.fatec.paises.interfaces.enums.adicionar_paises;

public enum AdicionarPaisText {
    LBL_TITLE_REGISTER("Cadastrar País"), LBL_TITLE_EDIT("Editar País"),
    LBL_SUCCESS_TEXT("País %s cadastrado com sucesso!"), LBL_ERROR_TEXT("Erro ao cadastrar país!"),
    LBL_ERROR_EMPTY_FIELD("Preencha todos os campos!"), LBL_ERROR_EXISTING("País já cadastrado!"),
    LBL_NOME("Nome:"), LBL_CAPITAL("Capital:"), LBL_DIMENSION("Dimensao:"),
    BTN_SAVE("Salvar"), BTN_MENU("Menu");

    private String string;

    AdicionarPaisText(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }
}
