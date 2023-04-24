

package br.ind.cmil.gestao.model.entidades;

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
