package br.ind.cmil.gestao.funcionario.domain;

import br.ind.cmil.gestao.cargo.domain.Cargo;
import br.ind.cmil.gestao.departamento.domain.Departamento;
import br.ind.cmil.gestao.pessoa.domain.PessoaFisica;
import br.ind.cmil.gestao.pessoa.domain.PessoaJuridica;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author ti
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
    @JoinColumn(name = "departamento_id")
    private Departamento departamento;
    @ManyToOne
    @JoinColumn(name = "cargo_id")
    private Cargo cargo;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate demissao;
    private Boolean status = false;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private PessoaJuridica empresa;

    public Funcionario() {
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public PessoaJuridica getEmpresa() {
        return empresa;
    }

    public void setEmpresa(PessoaJuridica empresa) {
        this.empresa = empresa;
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
        sb.append(", status=").append(status);
        sb.append(", empresa=").append(empresa);
        sb.append('}');
        return sb.toString();
    }

   

}
