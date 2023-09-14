package br.ind.cmil.gestao.model.entidades;

import br.ind.cmil.gestao.model.enums.EstadoCivil;
import br.ind.cmil.gestao.model.enums.Genero;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

/**
 *
 * @author abraao
 */
@SuppressWarnings("serial")

@Entity
@Table(name = "tbl_funcionarios")
@PrimaryKeyJoinColumn(name = "id")
public class Funcionario extends PessoaFisica {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate admissao;
    @ManyToOne
    @JoinColumn(name = "departmento_id", nullable = false)
    private Departamento departmento;
    @ManyToOne
    @JoinColumn(name = "cargo_id", nullable = false)
    private Cargo cargo;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate demissao;   
    @NumberFormat(pattern = "#,##0.00", style = NumberFormat.Style.CURRENCY)
    private BigDecimal salario;

    @ManyToOne
    @JoinColumn(name = "rh_id")
    private RH rh;
    @ManyToOne
    @JoinColumn(name = "projeto_id")
    private Projeto projeto;

    public Funcionario() {
    }

    public Funcionario(LocalDate admissao, Departamento departmento, Cargo cargo, LocalDate demissao, BigDecimal salario, RH rh, Projeto projeto) {
        this.admissao = admissao;
        this.departmento = departmento;
        this.cargo = cargo;
        this.demissao = demissao;
        this.salario = salario;
        this.rh = rh;
        this.projeto = projeto;
    }

    public Funcionario(LocalDate admissao, Departamento departmento, Cargo cargo, LocalDate demissao, BigDecimal salario, RH rh, Projeto projeto, String cpf, String rg, String mae, String pai, String passaporte, Genero genero, EstadoCivil estado_civil, String naturalidade) {
        super(cpf, rg, mae, pai, passaporte, genero, estado_civil, naturalidade);
        this.admissao = admissao;
        this.departmento = departmento;
        this.cargo = cargo;
        this.demissao = demissao;
        this.salario = salario;
        this.rh = rh;
        this.projeto = projeto;
    }

    public Funcionario(LocalDate admissao, Departamento departmento, Cargo cargo, LocalDate demissao, BigDecimal salario, RH rh, Projeto projeto, String cpf, String rg, String mae, String pai, String passaporte, Genero genero, EstadoCivil estado_civil, String naturalidade, String nome, String sobrenome, Date nascimento, Endereco endereco) {
        super(cpf, rg, mae, pai, passaporte, genero, estado_civil, naturalidade, nome, sobrenome, nascimento, endereco);
        this.admissao = admissao;
        this.departmento = departmento;
        this.cargo = cargo;
        this.demissao = demissao;
        this.salario = salario;
        this.rh = rh;
        this.projeto = projeto;
    }

    public LocalDate getAdmissao() {
        return admissao;
    }

    public void setAdmissao(LocalDate admissao) {
        this.admissao = admissao;
    }

    public Departamento getDepartmento() {
        return departmento;
    }

    public void setDepartmento(Departamento departmento) {
        this.departmento = departmento;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public LocalDate getDemissao() {
        return demissao;
    }

    public void setDemissao(LocalDate demissao) {
        this.demissao = demissao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public RH getRh() {
        return rh;
    }

    public void setRh(RH rh) {
        this.rh = rh;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Funcionario{");
        sb.append("admissao=").append(admissao);
        sb.append(", departmento=").append(departmento);
        sb.append(", cargo=").append(cargo);
        sb.append(", demissao=").append(demissao);
        sb.append(", salario=").append(salario);
        sb.append(", rh=").append(rh);
        sb.append(", projeto=").append(projeto);
        sb.append('}');
        return sb.toString();
    }

   

   

}
