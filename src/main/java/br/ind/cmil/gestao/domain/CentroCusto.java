package br.ind.cmil.gestao.domain;

import br.ind.cmil.gestao.base.Entidade;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author abraao
 */
@SuppressWarnings("serial")
@Getter
@Setter
@ToString
@Entity
@Table(name = "tbl_centro_custos")
public class CentroCusto extends Entidade {

    @Column(length = 80)
    private String nome;
   // @JsonIgnore
    //@OneToMany(mappedBy = "centro")
    //private List<Funcionario> funcionarios;

    public CentroCusto() {

    }

    public CentroCusto(Long id) {
        super.setId(id);

    }

    public CentroCusto(String nome) {
        this.nome = nome;

    }

}
