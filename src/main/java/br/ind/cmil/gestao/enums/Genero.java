package br.ind.cmil.gestao.enums;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author abraao
 */
public enum Genero {
    FEMININO("feminino"), MASCULINO("masculino"), OUTROS("outros");

    private final String value;

    private Genero(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Genero{" + "value=" + value + '}';
    }

    public static Genero convertGeneroValue(String value) {
        if (value == null) {
            return null;
        }
        return switch (value.toLowerCase()) {
            case "feminino" ->
                Genero.FEMININO;
            case "masculino" ->
                Genero.MASCULINO;
            case "outros" ->
                Genero.OUTROS;
            default ->
                throw new IllegalArgumentException(" Genero invalido " + value);
        };
    }

    public static Set<String> generos() {
        Set<String> generos = new HashSet<>();
        for (Genero genero : Genero.values()) {
            generos.add(genero.getValue());
        }
        return generos;
    }
}
