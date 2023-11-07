package br.ind.cmil.gestao.dto;

import br.ind.cmil.gestao.model.entidades.Horario;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author abraao
 */
public record PresencaDTO(
        @JsonProperty
        Long id,
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate dataPresenca,
        Horario horario,
        AuxiliarAdministrativoDTO auxiliar,
        FuncionarioDTO funcionario,
        String status) {

}
