
package br.ind.cmil.gestao.domain;

import br.ind.cmil.gestao.base.Entidade;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 *
 * @author abraao
 */
@SuppressWarnings("serial")

@Entity
@Table(name = "tbl_lotacoes")
public class Lotacao extends Entidade {

    @Column(length = 80, unique = true, nullable = false)
    private String nome;   

    public Lotacao() {
    }

    public Lotacao(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Lotacao{");
        sb.append("nome=").append(nome);
        sb.append('}');
        return sb.toString();
    }
   
    
    
}
