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
    
     public static TipoPerfil convertPerfilValue(String value) {
        if (value == null) {
            return null;
        }
        return switch (value) {
            case "admin" ->
                TipoPerfil.ADMIN;
            case "administrador" ->
                TipoPerfil.ADMINISTRADOR;
            case "assistente" ->
                TipoPerfil.ASSISTENTE;
            case "auxiliar" ->
                TipoPerfil.AUXILIAR;
            case "comprador" ->
                TipoPerfil.COMPRADOR;
            case "diretor" ->
                TipoPerfil.DIRETOR;
            case "engenheiro" ->
                TipoPerfil.ENGENHEIRO;
            case "gerente" ->
                TipoPerfil.GERENTE;
            case "funcionário" ->
                TipoPerfil.FUNCIONARIO;
            case "líder" ->
                TipoPerfil.LIDERFINANCEIRO;
          
            case "técnico" ->
                TipoPerfil.TECNICO;
            case "usuário" ->
                TipoPerfil.USUARIO;
            default ->
                throw new IllegalArgumentException(" Perfil invalido " + value);
        };
    }

    @Override
    public String toString() {
        return "TipoPerfil{" + "value=" + value + '}';
    }

}
