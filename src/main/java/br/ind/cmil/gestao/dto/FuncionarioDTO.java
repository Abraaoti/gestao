package br.ind.cmil.gestao.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

/**
 *
 * @author abraao
 */
public record FuncionarioDTO(
       
        Long id,
        String nome,
        String sobrenome,
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate nascimento,
        // @Pattern(regexp = "(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2}){11,14}$", message = "cpf obrigatório")
        String cpf,
        String rg,
        String mae,
        String pai,
        String clt,
        String genero,
        String estado_civil,
        String naturalidade,
        LocalDate admissao,
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate demissao,
        @NumberFormat(pattern = "#,##0.00", style = NumberFormat.Style.CURRENCY)
        BigDecimal salario,       
        CargoDTO cargo,       
        DepartamentoDTO departamento,       
        LotacaoDTO lotacao) {

}
