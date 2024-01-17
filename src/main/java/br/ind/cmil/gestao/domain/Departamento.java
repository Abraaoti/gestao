package br.ind.cmil.gestao.domain;

import br.ind.cmil.gestao.base.Entidade;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Set;
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
