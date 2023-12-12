package br.ind.cmil.gestao.model.dto;

import java.util.List;

/**
 *
 * @author abraao
 */
public record PresencaRequestDTO(     
        String status,
        List<FrequenciaDTO> frequencias) {

    // public PresencaDTO{
    //        status = false;
    // }
}
