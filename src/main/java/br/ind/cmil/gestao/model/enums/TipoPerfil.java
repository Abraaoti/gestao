package br.ind.cmil.gestao.model.enums;

/**
 *
 * @author abraao
 */
public enum TipoPerfil {
    ADMIN("administrador"),
    ADMINISTRATIVO("administrativo"),
    COMPRADOR("comprador"),
    DIRETOR("diretor"),
    ENGENHEIRO("engenheiro"),
    FINANCEIRO("financeiro"),
    FUNCIONARIO("funcionário"),
    RH("rh"),
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
