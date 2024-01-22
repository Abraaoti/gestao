package br.ind.cmil.gestao.domain;

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
@Table(name = "tbl_estados")
public class Estado extends Entidade {

    @Column(length = 80, unique = true)
    private String nome;
    @Column(length = 80)
    private String uf;
   
    public Estado() {
    }

    public Estado(Long id) {
        super.setId(id);
    }

  

}
