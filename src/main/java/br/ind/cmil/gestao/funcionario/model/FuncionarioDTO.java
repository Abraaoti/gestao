
package br.ind.cmil.gestao.funcionario.model;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author ti
 */
public record FuncionarioDTO(
        Long id,
        @NotNull
        String nome,
        @NotNull
        String sobrenome,
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate nascimento,
        // @Pattern(regexp = "(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2}){11,14}$", message = "cpf obrigatório")
        @NotNull
        String cpf,
        @NotNull
        String rg,
        String mae,
        String pai,
        String clt,
        @NotNull
        String genero,
        @NotNull
        String estado_civil,
        String naturalidade,
        LocalDate admissao,
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate demissao,       
        @NotNull
        Long cargo,
        @NotNull
        Long departamento,
        @NotNull
        Long centro
        ) {

}
