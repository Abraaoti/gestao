package br.ind.cmil.gestao.enums;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author abraao
 */
public enum TipoControle {
    ATESTADO("atestado"),
    FALTA("falta"),
    INSS("inss"),
    PRESENTE("presente"),
    TREINAMENTO("treinamento");
    private final String value;

    private TipoControle(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TipoControle convertTipoControle(String value) {

        if (value == null) {
            return null;
        }
        return switch (value) {

            case "atestado" ->
                TipoControle.ATESTADO;
            case "falta" ->
                TipoControle.FALTA;
            case "inss" ->
                TipoControle.INSS;
            case "presente" ->
                TipoControle.PRESENTE;
            case "treinamento" ->
                TipoControle.TREINAMENTO;
            default ->
                throw new IllegalArgumentException(" opção  inválido " + value);
        };

    }

    public static Set<String> tipoAusencia() {
        Set<String> ausencias = new HashSet<>();
        for (TipoControle ausencia : TipoControle.values()) {
            ausencias.add(ausencia.getValue());
        }
        return ausencias;
    }

}
