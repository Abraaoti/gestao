
package br.ind.cmil.gestao.model.dto;

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
        LocalDate data_presenca,
        String status,
        Horario horario,
        AuxiliarAdministrativoDTO auxiliar_rh,
        FuncionarioDTO funcionario
        
        ) {

}
