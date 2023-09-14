package br.ind.cmil.gestao.model.enums;

/**
 *
 * @author abraao
 */
public enum TipoPerfil {
    ADMIN("admin"),
    ADMINISTRADOR("administrador"),
    ADMINISTRATIVO("administrativo"),
    ASSISTENTEADMINISTRATIVO("assistente dministrativo"),
    AUXDMINISTRATIVO("auxiliar dministrativo"),
    BENEFICIOS("benefícios"),
    COMPRADOR("comprador"),
    DIRETOR("diretor"),
    ENGENHEIRO("engenheiro"),
    FINANCEIRO("financeiro"),
    FUNCIONARIO("funcionário"),
    PESSOAL("pessoal"),
    RECRUTAMENTO("recrutamento"),
    REMUNERACAO("remuneração"),
    RH("rh"),
    SEGURANCA("segurança"),
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
