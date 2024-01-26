package br.ind.cmil.gestao.administrador.domain;

import br.ind.cmil.gestao.base.Entidade;
import br.ind.cmil.gestao.usuario.domain.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "tbl_administradores")
public class Administrador extends Entidade {

    @Column(length = 80, unique = true)
    private String nome;
    @JsonIgnore
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Administrador() {
    }

    public Administrador(Long id) {
        super.setId(id);
    }

    public Administrador(Usuario usuario) {
        this.usuario = usuario;
    }

}
