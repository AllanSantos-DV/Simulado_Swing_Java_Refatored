package br.edu.fatec.paises.enums;

public enum CountryRegistrationText {
    LBL_TITLE_REGISTER("Cadastrar País"), LBL_NAME("Nome: "),
    LBL_CAPITAL("Capital: "), LBL_DIMENSION("Dimensao: "),
    LBL_SUCCESS_TEXT("País %s cadastrado com sucesso!"), LBL_ERROR_EXISTING("País já cadastrado!"),
    LBL_ERROR_EMPTY_FIELD("Preencha todos os campos!"), LBL_ERROR_INVALID_FIELD_SIZE("Campos deve conter entre 3 a 20 caracteres"),
    LBL_ERROR_INVALID_FIELD_NUMBER("Campos não podem conter números"), BTN_SAVE("Salvar"),
    BTN_MENU("Menu"), BTN_EDIT_COUNTRY("Editar País"), BTN_CANCEL_EDIT("Cancelar"),
    LBL_OPTION_PANE_TITLE_CONFIRM("Confirmar criação do Pais:"),
    BTN_OPTION_PANE_SAVE("Salvar"), BTN_OPTION_PANE_CANCEL("Cancelar");

    private final String string;

    CountryRegistrationText(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }
}