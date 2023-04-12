package br.ind.cmil.gestao.model.dto;

import br.ind.cmil.gestao.model.enums.EstadoCivil;
import br.ind.cmil.gestao.model.enums.Genero;
import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

/**
 *
 * @author abraao
 */
@Data
public class Funcionario {
    protected String nome;
    protected String sobrenome;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    protected Date nascimento;
    protected String cpf;
    protected String rg;
    protected String mae;
    protected String pai;
    protected String passaporte;
    protected Genero genero;
    protected EstadoCivil estado_civil;
    @Column(length = 50, nullable = false)
    protected String naturalidade;
    protected LocalDate admissao;
    protected String matricula;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    protected LocalDate demissao;
    @NumberFormat(pattern = "#,##0.00", style = NumberFormat.Style.CURRENCY)
    protected BigDecimal salario;
}
