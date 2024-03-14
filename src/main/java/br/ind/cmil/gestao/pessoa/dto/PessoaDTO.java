package br.ind.cmil.gestao.pessoa.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author abraaocalelessocassi
 */
public record PessoaDTO(
        Long id,
        @NotNull
        String nome,
        @NotNull
        String sobrenome,
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate nascimento) {

}
