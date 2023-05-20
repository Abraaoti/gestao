package br.ind.cmil.gestao.model.enums;

/**
 *
 * @author abraao
 */
public enum TipoPrivilegio {
    LER("ler"),
    GRAVAR("gravar"),
    ATUALIZAR("atualizar"),
    DELETAR("excluir");
    private final String value;

    private TipoPrivilegio(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "TipoPrivilege{" + "value=" + value + '}';
    }

}
