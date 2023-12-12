package br.ind.cmil.gestao.enums;

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
     public static EPeriodo convertPeriodoValue(String value) {
        if (value == null) {
            return null;
        }
        return switch (value) {
            case "manhã" ->
                EPeriodo.MANHA;
            case "tarde" ->
                EPeriodo.TARDE;
            case "noite" ->
                EPeriodo.NOITE;
            default ->
                throw new IllegalArgumentException(" Telefone invalido " + value);
        };
    }

}
