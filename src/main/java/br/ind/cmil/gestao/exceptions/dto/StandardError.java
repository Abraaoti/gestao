package br.ind.cmil.gestao.exceptions.dto;

import java.time.Instant;

/**
 *
 * @author abraao
 */
public record StandardError(
        Instant timestamp,
        Integer status,
        String error,
        String message,
        String path
        
        ) {

}
