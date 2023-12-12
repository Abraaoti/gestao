package br.ind.cmil.gestao.enums;

import br.ind.cmil.gestao.interfaces.Perfis;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author abraao
 */
public enum TipoPerfil  {
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

    public static Set<String> perfis() {
        Set<String> perfis = new HashSet<>();
        for (TipoPerfil perfil : TipoPerfil.values()) {
            perfis.add(perfil.getValue());
        }
        return perfis;
    }
   

}
