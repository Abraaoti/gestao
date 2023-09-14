
package br.ind.cmil.gestao.model.dto.mappers;

/**
 *
 * @author abraao
 */
@Component
public class PresencaMapper {

    public PresencaDTO toDTO(Presenca p) {
        if (p == null) {
            return null;
        }
        AuxiliarAdministrativoMapper dm = new AuxiliarAdministrativoMapper();
        AuxiliarAdministrativoDTO auxiliar_rh = dm.toDTO(p.getAuxiliar_rh());

        FuncionarioMapper fm = new FuncionarioMapper();
        FuncionarioDTO funcionario = fm.toDTO(p.getFuncionario());

        return new PresencaDTO(p.getId(), p.getDataPresenca(), p.getStatus().getValue(), p.getHorario(), auxiliar_rh, funcionario);
    }

    public Presenca toEntity(PresencaDTO dto) {
        if (dto == null) {
            return null;
        }
        Presenca p = new Presenca();
        p.setId(dto.id());
        p.setDataPresenca(dto.data_presenca());
        p.setStatus(convertPresencaValue(dto.status()));
        p.setHorario(dto.horario());
        AuxiliarAdministrativoMapper dm = new AuxiliarAdministrativoMapper();
        AuxiliarAdministrativo auxiliar_rh = dm.toEntity(dto.auxiliar_rh());
        p.setAuxiliar_rh(auxiliar_rh);
        FuncionarioMapper fm = new FuncionarioMapper();
        Funcionario funcionario = fm.toEntity(dto.funcionario());
        p.setFuncionario(funcionario);
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
                throw new IllegalArgumentException(" Presença invalido " + value);
        };
    }

}
