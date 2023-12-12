
package br.ind.cmil.gestao.domain;

import br.ind.cmil.gestao.base.Entidade;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

/**
 *
 * @author abraao
 */
@SuppressWarnings("serial")
@Getter
@Setter
@ToString
@Entity
@Table(name = "tbl_agendamentos")
public class Agendamento extends Entidade{
   
	
	@ManyToOne
	@JoinColumn(name="assistente_id")
	private AssistenteAdministrativo assistente;
	
	@ManyToOne
	@JoinColumn(name="funcionario_id")
	private Funcionario funcionario;
	
	@ManyToOne
	@JoinColumn(name="id_horario")
	private Horario horario; 

	@Column(name="data_exame")
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate dataExame;
    
}
