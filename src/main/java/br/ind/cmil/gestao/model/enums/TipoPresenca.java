package br.ind.cmil.gestao.model.enums;

/**
 *
 * @author abraao
 */
public enum TipoPresenca {
    AFASTADO("afastado"),
    ATESTADO("atestado"),
    AUSENTE("ausente"),
    PRESENTE("presente"),
    TREINAMENTO("treinamento");
    private final String value;

    private TipoPresenca(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "TipoPresenca{" + "value=" + value + '}';
    }

}
