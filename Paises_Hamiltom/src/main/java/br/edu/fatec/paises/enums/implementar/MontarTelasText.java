package br.edu.fatec.paises.enums.implementar;

public enum MontarTelasText {

    MSG_ERROR("Erro ao montar tela: %s"), TELA_MENU("Menu");

    private final String string;

    MontarTelasText(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }
}
