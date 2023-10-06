package br.ind.cmil.gestao.model.enums;

/**
 *
 * @author abraao
 */
public enum EPeriodo {
    MANHA("manhã"),
    TARDE("tarde"),
    NOITE("noite");
    private final String value;

    private EPeriodo(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "EPeriodo{" + "value=" + value + '}';
    }

}
