package br.ind.cmil.gestao.domain;

import br.ind.cmil.gestao.base.Entidade;
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
@Table(name = "tbl_cargos")
public class Cargo extends Entidade {

    @Column(length = 80, unique = true, nullable = false)
    private String nome;
    //@OneToMany(mappedBy = "cargo")
   // private List<Funcionario> funcionarios;

    public Cargo() {

    }

    public Cargo(Long id) {
        super.setId(id);
    }

    public Cargo(String nome) {
        this.nome = nome;
    }

}