
package br.ind.cmil.gestao.empresa.domain;

import br.ind.cmil.gestao.funcionario.domain.Funcionario;
import jakarta.persistence.Entity;
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
public class Empresa {
    private String endereco;
    private String cidade;
    private String telefone;
    private String email;
    private boolean matriz;

    /**
     * Indica qual funcionário é o gerente da empresa.
    */
    private Funcionario gerente;
}
