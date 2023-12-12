package br.ind.cmil.gestao.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 *
 * @author abraao
 */
public record PresencaDTO(
        @JsonProperty
        Long id,
        String status,
        List<FrequenciaDTO> frequencias) {

    // public PresencaDTO{
    //        status = false;
    // }
}
