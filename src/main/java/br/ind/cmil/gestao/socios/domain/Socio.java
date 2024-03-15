
package br.ind.cmil.gestao.socios.domain;

import br.ind.cmil.gestao.base.Entidade;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 *
 * @author abraaocalelessocassi
 */
@Entity
@Table(name = "tbl_socios")
@SuppressWarnings("serial")
public class Socio extends Entidade{
    @Column(length = 80)
    private String nome;
    @Column(length = 120)
    private String sobrenome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }
    
}
