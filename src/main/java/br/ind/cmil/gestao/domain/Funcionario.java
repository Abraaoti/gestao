package br.ind.cmil.gestao.domain;

import br.ind.cmil.gestao.enums.EstadoCivil;
import br.ind.cmil.gestao.enums.Genero;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
    @JoinColumn(name = "centro_custo_id", referencedColumnName = "id")
    private CentroCusto centroCusto;
    @JsonIgnore
    @OneToMany(mappedBy = "funcionario")
    private List<Frequencia> frequencias = new ArrayList<>();

    public Funcionario() {
    }

    public Funcionario(Long id) {
        super.setId(id);
    }

    public Funcionario(String nome) {
        super.setNome(nome);
    }

    public Funcionario(String clt, LocalDate admissao, Departamento departamento, Cargo cargo, LocalDate demissao, BigDecimal salario, CentroCusto centroCusto) {
        this.clt = clt;
        this.admissao = admissao;
        this.departamento = departamento;
        this.cargo = cargo;
        this.demissao = demissao;
        this.salario = salario;
        this.centroCusto = centroCusto;
    }

    public Funcionario(String clt, LocalDate admissao, Departamento departamento, Cargo cargo, LocalDate demissao, BigDecimal salario, CentroCusto centroCusto, String cpf, String rg, String mae, String pai, Genero genero, EstadoCivil estado_civil, String naturalidade, Long id, String nome, String sobrenome, LocalDate nascimento) {
        super(cpf, rg, mae, pai, genero, estado_civil, naturalidade, id, nome, sobrenome, nascimento);
        this.clt = clt;
        this.admissao = admissao;
        this.departamento = departamento;
        this.cargo = cargo;
        this.demissao = demissao;
        this.salario = salario;
        this.centroCusto = centroCusto;
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

    public CentroCusto getCentroCusto() {
        return centroCusto;
    }

    public void setCentroCusto(CentroCusto centroCusto) {
        this.centroCusto = centroCusto;
    }

    public List<Frequencia> getFrequencias() {
        return frequencias;
    }

    public void setFrequencias(List<Frequencia> frequencias) {
        this.frequencias = frequencias;
    }
    
    

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Funcionario{");
        sb.append("clt=").append(clt);
        sb.append(", admissao=").append(admissao);
        sb.append(", departamento=").append(departamento);
        sb.append(", cargo=").append(cargo);
        sb.append(", demissao=").append(demissao);
        sb.append(", salario=").append(salario);
        sb.append(", centro_custo=").append(centroCusto);
        sb.append('}');
        return sb.toString();
    }

}
