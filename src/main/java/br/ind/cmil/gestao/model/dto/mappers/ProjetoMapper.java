
package br.ind.cmil.gestao.model.dto.mappers;

/**
 *
 * @author abraao
 */


import br.ind.cmil.gestao.dto.AdministradorDTO;
import br.ind.cmil.gestao.dto.ProjetoDTO;
import br.ind.cmil.gestao.model.entidades.Administrador;
import br.ind.cmil.gestao.model.entidades.Projeto;
import org.springframework.stereotype.Component;
@Component
public class ProjetoMapper {

    public ProjetoDTO toDTO(Projeto p) {
        if (p == null) {
            return null;
        }
       

        AdministradorMapper dm = new AdministradorMapper();
        AdministradorDTO administrador = dm.toDTO(p.getAdministrador());

        return new ProjetoDTO(p.getId(), p.getContrato(), p.getNumero(),p.getInicio(), p.getFim(),p.getUpdatedAt(), p.getResponsavel(), p.getSeguranca(),p.getGestor(),administrador);
       
    }

    public Projeto toEntity(ProjetoDTO dto) {
        if (dto == null) {
            return null;
        }
        Projeto p = new Projeto();
        p.setId(dto.id());
        p.setContrato(dto.contrato());
        p.setNumero(dto.numero());
        p.setInicio(dto.inicio());
        p.setFim(dto.fim());
        p.setUpdatedAt(dto.updatedAt());
        p.setResponsavel(dto.responsavel());
        p.setSeguranca(dto.seguranca());
        p.setGestor(dto.gestor());
        AdministradorMapper dm = new AdministradorMapper();
        Administrador administrador = dm.toEntity(dto.administrador());
        
        p.setAdministrador(administrador);

       
        return p;
    }
    
}
