package br.ind.cmil.gestao.model.dto.mappers;

import br.ind.cmil.gestao.model.dto.PerfilDTO;
import br.ind.cmil.gestao.model.entidades.Perfil;
import br.ind.cmil.gestao.model.enums.TipoPerfil;
import org.springframework.stereotype.Component;

/**
 *
 * @author abraao
 */
@Component
public class PerfilMapper {

    public PerfilDTO toDTO(Perfil p) {
        if (p == null) {
            return null;
        }
        return new PerfilDTO(p.getId(), p.getTp().getValue());
    }

    public Perfil toEntity(PerfilDTO dto) {
        if (dto == null) {
            return null;
        }
        Perfil p = new Perfil();
        p.setId(dto.id());
        p.setTp(convertPerfilValue(dto.p()));
        return p;
    }

    public TipoPerfil convertPerfilValue(String value) {
        if (value == null) {
            return null;
        }
        return switch (value) {
            case "administrador" ->
                TipoPerfil.ADMIN;
            case "administrativo" ->
                TipoPerfil.ADMINISTRATIVO;
            case "comprador" ->
                TipoPerfil.COMPRADOR;
            case "diretor" ->
                TipoPerfil.DIRETOR;
            case "engenheiro" ->
                TipoPerfil.ENGENHEIRO;
            case "financeiro" ->
                TipoPerfil.FINANCEIRO;
            case "funcionário" ->
                TipoPerfil.FUNCIONARIO;
            case "rh" ->
                TipoPerfil.RH;
            case "técnico" ->
                TipoPerfil.TECNICO;
            case "usuário" ->
                TipoPerfil.USUARIO;
            default ->
                throw new IllegalArgumentException(" Perfil invalido " + value);
        };
    }
}
