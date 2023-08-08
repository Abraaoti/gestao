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
public class FuncionarioDTO extends PessoaFisicaDTO {

    private LocalDate admissao;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate demissao;
    @NumberFormat(pattern = "#,##0.00", style = NumberFormat.Style.CURRENCY)
    private BigDecimal salario;
    private DepartamentoDTO departamento;
    private CargoDTO cargo;

    public FuncionarioDTO(LocalDate admissao, LocalDate demissao, BigDecimal salario, DepartamentoDTO departamento, CargoDTO cargo, String cpf, String rg, String mae, String pai, String passaporte, String genero, String estado_civil, String naturalidade, Long id, String nome, String sobrenome, Date nascimento) {
        super(cpf, rg, mae, pai, passaporte, genero, estado_civil, naturalidade, id, nome, sobrenome, nascimento);
        this.admissao = admissao;
        this.demissao = demissao;
        this.salario = salario;
        this.departamento = departamento;
        this.cargo = cargo;
    }

   

    public LocalDate getAdmissao() {
        return admissao;
    }

    public void setAdmissao(LocalDate admissao) {
        this.admissao = admissao;
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

    public CargoDTO getCargo() {
        return cargo;
    }

    public void setCargo(CargoDTO cargo) {
        this.cargo = cargo;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FuncionarioDTO{");
        sb.append("admissao=").append(admissao);
        sb.append(", demissao=").append(demissao);
        sb.append(", departamento=").append(departamento);
        sb.append(", cargo=").append(cargo);
        sb.append(", salario=").append(salario);
        sb.append('}');
        return sb.toString();
    }

}
