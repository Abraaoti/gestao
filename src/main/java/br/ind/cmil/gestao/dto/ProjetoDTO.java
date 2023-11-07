package br.ind.cmil.gestao.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author abraao
 */
public record ProjetoDTO(
        @JsonProperty
        Long id,
        String contrato,
        String numero,
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDateTime inicio,
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDateTime fim,
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDateTime updatedAt,
        String responsavel,
        String seguranca,
        String gestor,
        AdministradorDTO administrador) {

}
