package br.ind.cmil.gestao.model.enums;

/**
 *
 * @author abraao
 */
public enum EstadoCivil {
    SOLTEIRO("solteiro(a)"), CASADO("casado(a)"), DIVORCIADO("divorciado(a)"), VIUVA("viúva(o)");

    private final String value;

    private EstadoCivil(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "EC{" + "value=" + value + '}';
    }
}
