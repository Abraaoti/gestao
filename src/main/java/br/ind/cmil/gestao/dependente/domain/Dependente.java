

package br.ind.cmil.gestao.dependente.domain;

import br.ind.cmil.gestao.funcionario.domain.Funcionario;
import br.ind.cmil.gestao.pessoa.domain.PessoaFisica;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * @author abraao
 */
@Setter
@Getter
@Entity
@Table(name = "tbl_dependentes")
public class Dependente extends PessoaFisica{
	@ManyToOne
	private Funcionario funcionario;

}
