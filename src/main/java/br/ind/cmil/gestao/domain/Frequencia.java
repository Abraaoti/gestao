package br.ind.cmil.gestao.domain;

import br.ind.cmil.gestao.base.Entidade;
import br.ind.cmil.gestao.convert.TipoAusenciaConvert;
import br.ind.cmil.gestao.enums.TipoFrequencia;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author abraao
 */
@Entity
@Table(name = "tbl_frequencias")
public class Frequencia extends Entidade {

    @Column(name = "data")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate data;
    @Column(name = "status", nullable = false)
    @Convert(converter = TipoAusenciaConvert.class)
    protected TipoFrequencia status;
    @ManyToMany(mappedBy = "frequencias")
    @JsonIgnoreProperties("frequencias")
    private List<Funcionario> funcionarios = new ArrayList<>();

    public Frequencia() {
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

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

   
}
