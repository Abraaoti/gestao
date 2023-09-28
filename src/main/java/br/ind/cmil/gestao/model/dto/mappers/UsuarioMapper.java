package br.ind.cmil.gestao.model.dto.mappers;

import br.ind.cmil.gestao.exceptions.ObjectNotFoundException;
import br.ind.cmil.gestao.model.dto.RegistrarUsuario;
import br.ind.cmil.gestao.model.entidades.Usuario;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

/**
 *
 * @author abraao
 */
@Component
public class UsuarioMapper {

    public RegistrarUsuario toDTO(Usuario u) {
        Set<String> roles = u.getPerfis().stream().map(p -> p.getTp().getValue()).collect(Collectors.toSet());         
        return new RegistrarUsuario(u.getId(), u.getNome(), u.getEmail(), u.getPassword(), u.getDataCadastro(), u.getUpdatedAt(), u.isAtivo(), u.getVerificador(), roles);
    }

    public Usuario toEntity(RegistrarUsuario dto) {

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
