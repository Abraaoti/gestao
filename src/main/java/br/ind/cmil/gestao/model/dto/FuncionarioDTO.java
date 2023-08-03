package br.ind.cmil.gestao.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

/**
 *
 * @author abraao
 */
public class FuncionarioDTO extends PessoaFisicaDTO{
    protected LocalDate admissao;
    protected String matricula;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    protected LocalDate demissao;
    protected DepartamentoDTO departamento;
    @NumberFormat(pattern = "#,##0.00", style = NumberFormat.Style.CURRENCY)
    protected BigDecimal salario;

    public FuncionarioDTO(LocalDate admissao, String matricula, LocalDate demissao, DepartamentoDTO departamento, BigDecimal salario, String cpf, String rg, String mae, String pai, String passaporte, String genero, String estado_civil, String naturalidade, Long id, String nome, String sobrenome, Date nascimento) {
        super(cpf, rg, mae, pai, passaporte, genero, estado_civil, naturalidade, id, nome, sobrenome, nascimento);
        this.admissao = admissao;
        this.matricula = matricula;
        this.demissao = demissao;
        this.departamento = departamento;
        this.salario = salario;
    }


    public LocalDate getAdmissao() {
        return admissao;
    }

    public void setAdmissao(LocalDate admissao) {
        this.admissao = admissao;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public LocalDate getDemissao() {
        return demissao;
    }

    public void setDemissao(LocalDate demissao) {
        this.demissao = demissao;
    }

    public DepartamentoDTO getDepartamento() {
        return departamento;
    }

    public void setDepartamento(DepartamentoDTO departamento) {
        this.departamento = departamento;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "FuncionarioDTO{" + "admissao=" + admissao + ", matricula=" + matricula + ", demissao=" + demissao + ", departamento=" + departamento + ", salario=" + salario + '}';
    }
    
    

}
