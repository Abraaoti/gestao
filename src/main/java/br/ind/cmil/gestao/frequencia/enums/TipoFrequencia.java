package br.ind.cmil.gestao.frequencia.enums;

/**
 *
 * @author abraaocalelessocassi
 */
public enum TipoFrequencia {
    ATENDENTE {
        public double calculaComissao(double valor) {
            return valor * 0.1;
        }
    },
    VENDE
}
