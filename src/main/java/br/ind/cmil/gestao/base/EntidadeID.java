package br.ind.cmil.gestao.base;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.util.UUID;
import org.hibernate.annotations.GenericGenerator;



/**
 *
 * @author cmilseg
 */
@SuppressWarnings("serial")
@MappedSuperclass
public abstract class EntidadeID implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    public UUID id;

    public EntidadeID() {
        super();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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
        final EntidadeID other = (EntidadeID) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Entidade{" + "id=" + id + '}';
    }

}
