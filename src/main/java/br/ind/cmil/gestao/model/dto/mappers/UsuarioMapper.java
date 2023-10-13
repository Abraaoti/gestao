package br.ind.cmil.gestao.model.dto.mappers;

import br.ind.cmil.gestao.model.dto.UsuarioRequest;
import br.ind.cmil.gestao.model.entidades.Usuario;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

/**
 *
 * @author abraao
 */
@Component
public class UsuarioMapper {

    public UsuarioRequest toDTO(Usuario u) {
        List<String> roles = u.getPerfis().stream().map(p -> p.getTp().getValue()).collect(Collectors.toList());         
        return new UsuarioRequest(u.getId(), u.getNome(), u.getEmail(), u.getPassword(), u.getDataCadastro(), u.getUpdatedAt(), u.isAtivo(), u.getVerificador(), roles);
    }

    public Usuario toEntity(UsuarioRequest dto) {

        Usuario u = new Usuario();
        u.setId(dto.id());        
        u.setNome(dto.nome());
        u.setEmail(dto.email());
        u.setPassword(dto.password());
        u.setVerificador(dto.verificador());
        u.setDataCadastro(LocalDateTime.now());
        u.setAtivo(dto.ativo());
       // u.setPerfis(perfis(dto.perfis()));
        return u;
    }

}
