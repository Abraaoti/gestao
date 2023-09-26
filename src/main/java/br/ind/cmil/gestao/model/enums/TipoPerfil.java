package br.ind.cmil.gestao.model.enums;

/**
 *
 * @author abraao
 */
public enum TipoPerfil {
    ADMIN("admin"),
    ADMINISTRADOR("administrador"),
    ASSISTENTE("assistente"),
    AUXILIAR("auxiliar"),
    COMPRADOR("comprador"),
    DIRETOR("diretor"),
    ENGENHEIRO("engenheiro"),
    FUNCIONARIO("funcionário"),
    GERENTE("gerente"),    
    LIDERFINANCEIRO("líder"),
    TECNICO("técnico"),
    USUARIO("usuário");
    private final String value;

    private TipoPerfil(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "TipoPerfil{" + "value=" + value + '}';
    }

}
