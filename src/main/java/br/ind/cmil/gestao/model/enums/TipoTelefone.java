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
    
    
    public static TipoTelefone convertTelefoneValue(String value) {
        if (value == null) {
            return null;
        }
        return switch (value) {
            case "pessoal" ->
                TipoTelefone.PESSOAL;
            case "comercial" ->
                TipoTelefone.COMERCIAL;
            case "residencial" ->
                TipoTelefone.RESIDENCIAL;
            default ->
                throw new IllegalArgumentException(" Telefone invalido " + value);
        };
    }

    

}
