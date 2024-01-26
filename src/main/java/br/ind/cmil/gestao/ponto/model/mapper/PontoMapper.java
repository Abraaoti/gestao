
package br.ind.cmil.gestao.ponto.model.mapper;

import br.ind.cmil.gestao.funcionario.domain.Funcionario;
import br.ind.cmil.gestao.ponto.domain.Ponto;
import br.ind.cmil.gestao.ponto.model.PontoDTO;
import java.time.LocalDate;
import java.time.LocalTime;
import org.springframework.stereotype.Component;

/**
 *
 * @author ti
 */
@Component
public class PontoMapper {
     public PontoDTO toDTO(Ponto ponto) {
     Long funcionarioId = ponto.getFuncionario() == null ? null : ponto.getFuncionario().getId();
     return new PontoDTO(ponto.getId(), ponto.getData(), ponto.getEntradaManha(), ponto.getSaidaManha(), ponto.getEntradaTarde(), ponto.getSaidaTarde(), ponto.getEntradaExtra(), ponto.getSaidaExtra(), ponto.getEntradaNoite(),ponto.getSaidaNoite(), funcionarioId);
     }
     public Ponto toEntity(PontoDTO dto) {
        if (dto == null) {
            return null;
        }
         Ponto ponto = new Ponto();
        ponto.setId(dto.id());
        ponto.setData(LocalDate.now());
        ponto.setEntradaManha(LocalTime.now());
        ponto.setSaidaManha(LocalTime.now());
        ponto.setEntradaTarde(LocalTime.now());
        ponto.setSaidaManha(LocalTime.now());
        ponto.setEntradaExtra(LocalTime.now());
        ponto.setSaidaExtra(LocalTime.now());
        ponto.setEntradaNoite(LocalTime.now());
        ponto.setSaidaNoite(LocalTime.now());
        ponto.setFuncionario(new Funcionario(dto.funcionario()));
        return ponto;
    }
}
