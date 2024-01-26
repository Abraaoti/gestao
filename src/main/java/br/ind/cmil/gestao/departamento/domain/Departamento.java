package br.ind.cmil.gestao.departamento.domain;

import br.ind.cmil.gestao.base.Entidade;
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

    @Column(length = 80, unique = true, nullable = false)
    private String nome;  

    public Departamento() {
    }

    public Departamento(Long id) {
        super.setId(id);
    }

    public Departamento(String nome) {
        this.nome = nome;
    }

}
