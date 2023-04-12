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
}
