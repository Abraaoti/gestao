
package br.ind.cmil.gestao.enums;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author abraao
 */
public enum TipoFrequencia {
    
    
    ATESTADO("atestado"),
    FALTA("falta"),
    INSS("inss"),
    PRESENTE("presente"),
    TREINAMENTO("treinamento");
    private final String value;

    private TipoFrequencia(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TipoFrequencia convertTipoTipoFrequencia(String value) {

        if (value == null) {
            return null;
        }
        return switch (value) {

            case "atestado" ->
                TipoFrequencia.ATESTADO;
            case "falta" ->
                TipoFrequencia.FALTA;
            case "inss" ->
                TipoFrequencia.INSS;
            case "presente" ->
                TipoFrequencia.PRESENTE;
            case "treinamento" ->
                TipoFrequencia.TREINAMENTO;
            default ->
                throw new IllegalArgumentException(" opção  inválido " + value);
        };

    }

    public static Set<String> tipoFrequencias() {
        Set<String> frequencias = new HashSet<>();
        for (TipoFrequencia ausencia : TipoFrequencia.values()) {
            frequencias.add(ausencia.getValue().toLowerCase());
        }
        return frequencias;
    }
    
}
