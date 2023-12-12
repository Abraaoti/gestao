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
@Table(name = "tbl_peridos")
public class Periodo extends Entidade {
    
    @Column(length = 80, unique = true, nullable = false)
    private String nome;
    
    public Periodo() {
        
    }

    public Periodo(Long id) {
        super.setId(id);
    }
    
    public Periodo(String nome) {
        this.nome = nome;
    }
    
}
