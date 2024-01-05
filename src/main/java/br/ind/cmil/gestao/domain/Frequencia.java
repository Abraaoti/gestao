package br.ind.cmil.gestao.domain;

import br.ind.cmil.gestao.base.Entidade;
import br.ind.cmil.gestao.convert.TipoAusenciaConvert;
import br.ind.cmil.gestao.enums.TipoFrequencia;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author abraao
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "tbl_frequencias")
public class Frequencia extends Entidade {

    @Column(name = "data")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate data;
    @Column(name = "status", nullable = false)
    @Convert(converter = TipoAusenciaConvert.class)
    protected TipoFrequencia status;
     @JsonManagedReference
    //@JsonIgnoreProperties("frequencias")
    @ManyToMany( fetch = FetchType.LAZY)
    @JoinTable(name = "tbl_frequencias_funcionarios",
            joinColumns = {
                @JoinColumn(name = "frequencia_id")},
            inverseJoinColumns = {
                @JoinColumn(name = "funcionario_id")}
    )
    private Set<Funcionario> funcionarios = new HashSet<>();

    public Frequencia() {
    }
    
      public void addFuncionario(Funcionario funcionario) {
        this.funcionarios.add(funcionario);
        funcionario.getFrequencias().add(this);
    }

    public void removeFuncionario(Funcionario funcionario) {
        this.funcionarios.remove(funcionario);
        funcionario.getFrequencias().remove(this);
    }


    public Frequencia(Long id) {
        super.setId(id);
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public TipoFrequencia getStatus() {
        return status;
    }

    public void setStatus(TipoFrequencia status) {
        this.status = status;
    }

    public Set<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(Set<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

}
