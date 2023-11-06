package br.edu.fatec.paises.enums;

public enum CountryRegistrationText {
    LBL_TITLE_REGISTER("Cadastrar País"), LBL_TITLE_PANE_CONFIRM("Confirmar criação do Pais:"),
    LBL_SUCCESS_TEXT("País %s cadastrado com sucesso!"), LBL_ERROR_EMPTY_FIELD("Preencha todos os campos!"),
    LBL_ERROR_EXISTING("País já cadastrado!"), LBL_ERROR_INVALID_FIELD_SIZE("Campos deve conter entre 3 a 20 caracteres"),
    LBL_ERROR_INVALID_FIELD_NUMBER("Campos não podem conter números"), LBL_NAME("Nome: "),
    LBL_CAPITAL("Capital: "), LBL_DIMENSION("Dimensao: "),
    BTN_SAVE("Salvar"), BTN_CANCEL("Cancelar"), BTN_MENU("Menu");

    private final String string;

    CountryRegistrationText(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }
}