package br.ind.cmil.gestao.model.enums;

/**
 *
 * @author abraao
 */
public enum TipoTelefone {
    PESSOAL("pessoal"),
    COMERCIAL("comercial"),
    RESIDENCIAL("residencial");
    private final String value;

    private TipoTelefone(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "TipoTelefone{" + "value=" + value + '}';
    }

    

}
