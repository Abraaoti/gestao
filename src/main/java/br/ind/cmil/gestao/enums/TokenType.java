package br.ind.cmil.gestao.enums;

/**
 *
 * @author abraao
 */
public enum TokenType {
    BEARER(" Bearer ");
    private final String value;

    private TokenType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "TipoToken{" + "value=" + value + '}';
    }

}
