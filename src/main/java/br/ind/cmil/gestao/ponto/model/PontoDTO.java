package br.ind.cmil.gestao.ponto.model;

import java.time.LocalDate;
import java.time.LocalTime;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author ti
 */
public record PontoDTO(
        Long id,
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate data,
        LocalTime entradaManha,
        LocalTime saidaManha,
        LocalTime entradaTarde,
        LocalTime saidaTarde,
        LocalTime entradaExtra,
        LocalTime saidaExtra,
        LocalTime entradaNoite,
        LocalTime saidaNoite,
        Long funcionario) {

}
