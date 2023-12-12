package br.ind.cmil.gestao.enums;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author abraao
 */
public enum EstadoCivil {
    SOLTEIRO("solteiro(a)"), CASADO("casado(a)"), DIVORCIADO("divorciado(a)"), VIUVA("viúva(o)");
    private final String value;

    private EstadoCivil(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "EC{" + "value=" + value + '}';
    }

    public static EstadoCivil findTipo(String value) {
        if (value == null) {
            return null;
        }
        return switch (value.toLowerCase()) {
            case "solteiro(a)" ->
                EstadoCivil.SOLTEIRO;
            case "casado(a)" ->
                EstadoCivil.CASADO;
            case "divorciado(a)" ->
                EstadoCivil.DIVORCIADO;
            case "viúva(o)" ->
                EstadoCivil.VIUVA;
            default ->
                throw new IllegalArgumentException(" Estado civil invalido " + value);
        };
    }

    public static Set<String> getEstadoCivil() {
        Set<String> estados = new HashSet<>();
        for (EstadoCivil estadoCivil : EstadoCivil.values()) {
            estados.add(estadoCivil.getValue());
        }
        return estados;
    }
}
