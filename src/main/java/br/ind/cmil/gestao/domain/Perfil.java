package br.ind.cmil.gestao.domain;

import br.ind.cmil.gestao.base.Entidade;
import br.ind.cmil.gestao.enums.TipoPerfil;
import br.ind.cmil.gestao.convert.TipoPerfilConvert;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 *
 * @author abraao
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "tbl_perfis")
public class Perfil extends Entidade {



    @Column(name = "perfil", nullable = false, unique = true)
    @Convert(converter = TipoPerfilConvert.class)
    private TipoPerfil tp;

   // @ManyToMany(mappedBy = "perfis")
    //private Set<Usuario> usuarios;

    public Perfil() {
        super();

    }

    public Perfil(Long id) {
        super.setId(id);
    }

    public Perfil(TipoPerfil tp) {
        this.tp = tp;
    }

    public Perfil(String nome) {
        this.tp = TipoPerfil.valueOf(nome);
    }

    public TipoPerfil getTp() {
        return tp;
    }

    public void setTp(TipoPerfil tp) {
        this.tp = tp;
    }

   
    /**
     * @PreRemove private void removeUsuarioAssociations() { for (Usuario book :
     * this.usuarios) { book.getPerfis().remove(this); } }
     */
}
