
package br.ind.cmil.gestao.enums;

/**
 *
 * @author abraao
 */
public enum DiaSemana {
     SEGUNDA("segunda-feira"),
    TERCA("terça-feira"),
    QUARTA("quarta-feira"),
    QUINTA("quinta-feira"),
    SEXTA("sexta-feira"),
    SABADO("sábado"),
    DOMINGO("domingo");
    private final String value;

    private DiaSemana(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "DiaSemana{" + "value=" + value + '}';
    }
    
}
