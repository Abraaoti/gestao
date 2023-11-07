package br.ind.cmil.gestao.model.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
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

    private String clt;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate admissao;
    @ManyToOne
    @JoinColumn(name = "departamento_id", referencedColumnName = "id")
    private Departamento departamento;
    @ManyToOne
    @JoinColumn(name = "cargo_id", referencedColumnName = "id")
    private Cargo cargo;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate demissao;
    @NumberFormat(pattern = "#,##0.00", style = NumberFormat.Style.CURRENCY)
    private BigDecimal salario;
    @ManyToOne
    @JoinColumn(name = "lotacao_id", referencedColumnName = "id")
    private Lotacao lotacao;

    public Funcionario() {
    }

    public Funcionario(String clt, LocalDate admissao, Departamento departamento, Cargo cargo, LocalDate demissao, BigDecimal salario, Lotacao lotacao) {
        this.clt = clt;
        this.admissao = admissao;
        this.departamento = departamento;
        this.cargo = cargo;
        this.demissao = demissao;
        this.salario = salario;
        this.lotacao = lotacao;
    }

    public String getClt() {
        return clt;
    }

    public void setClt(String clt) {
        this.clt = clt;
    }

    public LocalDate getAdmissao() {
        return admissao;
    }

    public void setAdmissao(LocalDate admissao) {
        this.admissao = admissao;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
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

    public Lotacao getLotacao() {
        return lotacao;
    }

    public void setLotacao(Lotacao lotacao) {
        this.lotacao = lotacao;
    }

    @Override
    public String toString() {
        return "Funcionario{" + "clt=" + clt + ", admissao=" + admissao + ", departamento=" + departamento + ", cargo=" + cargo + ", demissao=" + demissao + ", salario=" + salario + ", lotacao=" + lotacao + '}';
    }

   

}
