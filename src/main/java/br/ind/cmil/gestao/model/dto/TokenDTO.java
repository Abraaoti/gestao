package br.ind.cmil.gestao.model.dto;

import java.security.Timestamp;
import java.time.LocalDateTime;

/**
 *
 * @author abraao
 */
public record TokenDTO(
        String token,
        Timestamp timeStamp,
        LocalDateTime expireAt,
        String tokenType) {

}
