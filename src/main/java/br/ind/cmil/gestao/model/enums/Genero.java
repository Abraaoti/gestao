package br.ind.cmil.gestao.model.enums;

/**
 *
 * @author abraao
 */
public enum Genero {
    FEMININO("feminino"), MASCULINO("masculino"), OUTROS("outros");

    private final String value;

    private Genero(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "GeneroEnum{" + "value=" + value + '}';
    }
     public static Genero convertGeneroValue(String value) {
        if (value == null) {
            return null;
        }
        return switch (value) {
            case "feminino" ->
                Genero.FEMININO;
            case "masculino" ->
                Genero.MASCULINO;
            case "outros" ->
                Genero.OUTROS;
            default ->
                throw new IllegalArgumentException(" Genero invalido " + value);
        };
    }
}
