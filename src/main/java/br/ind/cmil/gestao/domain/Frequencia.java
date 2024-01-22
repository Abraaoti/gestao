package br.ind.cmil.gestao.domain;

import br.ind.cmil.gestao.base.Entidade;
import br.ind.cmil.gestao.convert.TipoAusenciaConvert;
import br.ind.cmil.gestao.enums.TipoFrequencia;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 *
 * @author abraao
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "tbl_frequencias")
public class Frequencia extends Entidade {

    //@Column(name = "data")
    //@DateTimeFormat(pattern = "yyyy-MM-dd")
   // private LocalDate data;
    @Column(name = "status", nullable = false)
    @Convert(converter = TipoAusenciaConvert.class)
    private TipoFrequencia status;
    // @JsonManagedReference
    //@JsonIgnoreProperties("frequencias")
    // @JsonIgnoreProperties(value = "frequencias", allowSetters = true)
   /**
     * @param id *  @JsonBackReference
    @ManyToMany
    @JoinTable(name = "tbl_frequencias_funcionarios",
            joinColumns = {
                @JoinColumn(name = "frequencia_id")},
            inverseJoinColumns = {
                @JoinColumn(name = "funcionario_id")}
    )
    private Set<Funcionario> funcionarios;

    public Frequencia() {
        data =  LocalDate.now();
    }

    public void addFuncionario(Funcionario funcionario) {
        this.funcionarios.add(funcionario);
        funcionario.getFrequencias().add(this);
    }

    public void removeFuncionario(Funcionario funcionario) {
        this.funcionarios.remove(funcionario);
        funcionario.getFrequencias().remove(this);
    }
   public Set<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(Set<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }
      public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }**/
    
    public Frequencia() {
    }

    public Frequencia(Long id) {
        super.setId(id);
    }

    public Frequencia(TipoFrequencia tipo) {
          this.status = TipoFrequencia.valueOf(tipo.getValue());
    }

  

    public TipoFrequencia getStatus() {
        return status;
    }

    public void setStatus(TipoFrequencia status) {
        this.status = status;
    }

 

}
