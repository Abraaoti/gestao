package br.ind.cmil.gestao.model.entidades;

import br.ind.cmil.gestao.model.base.Entidade;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
@Table(name = "tbl_departamentos")
public class Departamento extends Entidade {

    @Column(length = 80, unique = true)
    private String nome;
  
    public Departamento() {
    }

    public Departamento(String nome, String sobrenome) {
        this.nome = nome;
    }

}
