package br.ind.cmil.gestao.funcionario.model;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author ti
 */
public record CriarFuncionarioDTO(
        Long id,
        String nome,
        @NotNull
        String sobrenome,
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate nascimento,
        @NotNull
        String cpf,
        @NotNull
        String rg,
        String mae,
        String pai,
        @NotNull
        String genero,
        @NotNull
        String estado_civil,
        String naturalidade,
        @NotNull
        String clt,      
        @NotNull
        Long cargo,
        @NotNull
        Long departamento,
        @NotNull
        Long empresa) {

}
