package br.ind.cmil.gestao.perfil.enums;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author ti
 */
public enum TipoPerfil {
    ROLE_ADMIN("admin"),
    ROLE_ADMINISTRATIVO("administrativo"),
    ROLE_ASSISTENTE("assistente"),
    ROLE_AUXILIAR("auxiliar"),
    ROLE_COMPRADOR("comprador"),
    ROLE_DIRETOR("diretor"),
    ROLE_ENGENHEIRO("engenheiro"),
    ROLE_GERENTE("gerente"),
    ROLE_RH("rh"),
    ROLE_TECNICO("técnico"),
    ROLE_USUARIO("usuário");
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
                TipoPerfil.ROLE_ADMIN;
            case "administrativo" ->
                TipoPerfil.ROLE_ADMINISTRATIVO;
            case "assistente" ->
                TipoPerfil.ROLE_ASSISTENTE;
            case "auxiliar" ->
                TipoPerfil.ROLE_AUXILIAR;
            case "comprador" ->
                TipoPerfil.ROLE_COMPRADOR;
            case "diretor" ->
                TipoPerfil.ROLE_DIRETOR;
            case "engenheiro" ->
                TipoPerfil.ROLE_ENGENHEIRO;
            case "gerente" ->
                TipoPerfil.ROLE_GERENTE;
            case "rh" ->
                TipoPerfil.ROLE_RH;
            case "técnico" ->
                TipoPerfil.ROLE_TECNICO;
            case "usuário" ->
                TipoPerfil.ROLE_USUARIO;
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
