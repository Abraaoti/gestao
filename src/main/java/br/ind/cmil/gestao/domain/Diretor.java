package br.ind.cmil.gestao.domain;

import br.ind.cmil.gestao.base.Entidade;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
@Table(name = "tbl_Diretores")
public class Diretor extends Entidade {

    @Column(length = 80, unique = true)
    private String nome;
    //@JsonIgnore
    //@OneToMany(mappedBy = "auxiliar_rh")
    //private List<String> presencas;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Diretor() {
    }

    public Diretor(Long id) {
        super.setId(id);
    }

    public Diretor(Usuario usuario) {
        this.usuario = usuario;
    }

}
