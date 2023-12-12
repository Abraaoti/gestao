package br.ind.cmil.gestao.base;

import java.io.Serializable;
import java.util.Objects;



/**
 *
 * @author cmilseg
 */

public abstract class IdBase implements Serializable {

    private static final long serialVersionUID = 1L;
    public Long id;

    public IdBase() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final IdBase other = (IdBase) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Entidade{" + "id=" + id + '}';
    }

}
