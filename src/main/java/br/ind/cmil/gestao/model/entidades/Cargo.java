package br.ind.cmil.gestao.model.entidades;

import br.ind.cmil.gestao.model.base.Entidade;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
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
@Table(name = "tbl_cargos")
@Inheritance(strategy = InheritanceType.JOINED)
public class Cargo extends Entidade {

    @Column(length = 80)
    private String nome;

    public Cargo() {

    }

    public Cargo(String nome, String sobrenome) {
        this.nome = nome;

    }

}