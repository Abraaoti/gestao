package br.ind.cmil.gestao.model.dto.mappers;

/**
 *
 * @author abraao
 */
import br.ind.cmil.gestao.model.dto.AuxiliarAdministrativoDTO;
import br.ind.cmil.gestao.model.dto.FuncionarioDTO;
import br.ind.cmil.gestao.model.dto.PresencaDTO;
import br.ind.cmil.gestao.model.entidades.AuxiliarAdministrativo;
import br.ind.cmil.gestao.model.entidades.Funcionario;
import br.ind.cmil.gestao.model.entidades.Presenca;
import br.ind.cmil.gestao.model.enums.TipoPresenca;
import org.springframework.stereotype.Component;

@Component
public class PresencaMapper {

    public PresencaDTO toDTO(Presenca p) {
        if (p == null) {
            return null;
        }

        AuxiliarAdministrativoMapper am = new AuxiliarAdministrativoMapper();
        AuxiliarAdministrativoDTO administrador = am.toDTO(p.getAuxiliar_rh());
        FuncionarioMapper fm = new FuncionarioMapper();
        FuncionarioDTO funcionario = fm.toDTO(p.getFuncionario());

        return new PresencaDTO(p.getId(), p.getDataPresenca(), p.getHorario(), administrador, funcionario, p.getStatus().getValue());

    }

    public Presenca toEntity(PresencaDTO dto) {
        if (dto == null) {
            return null;
        }
        Presenca p = new Presenca();
        p.setId(dto.id());
        p.setDataPresenca(dto.dataPresenca());

        AuxiliarAdministrativoMapper am = new AuxiliarAdministrativoMapper();
        AuxiliarAdministrativo auxiliar = am.toEntity(dto.auxiliar_rh());
        p.setAuxiliar_rh(auxiliar);
        FuncionarioMapper fm = new FuncionarioMapper();
        Funcionario funcionario = fm.toEntity(dto.funcionario());

        p.setFuncionario(funcionario);
        p.setStatus(convertPresencaValue(dto.status()));

        return p;
    }

    public TipoPresenca convertPresencaValue(String value) {

        if (value == null) {
            return null;
        }
        return switch (value) {
            case "afastado" ->
                TipoPresenca.AFASTADO;
            case "atestado" ->
                TipoPresenca.ATESTADO;
            case "ausente" ->
                TipoPresenca.AUSENTE;
            case "presente" ->
                TipoPresenca.PRESENTE;
            case "treinamento" ->
                TipoPresenca.TREINAMENTO;
            default ->
                throw new IllegalArgumentException(" Presença inválido " + value);
        };
    }

}
