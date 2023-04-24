
package br.ind.cmil.gestao.model.entidades;

import br.ind.cmil.gestao.model.base.Entidade;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author abraao
 */
@SuppressWarnings("serial")
@Getter
@Setter
@Entity
@Table(name = "tbl_categorias")
public class Categoria extends Entidade {

    @Column(length = 40)
    private String nome;

    public Categoria() {
    }

    public Categoria(String nome) {
        this.nome = nome;
    }

}
