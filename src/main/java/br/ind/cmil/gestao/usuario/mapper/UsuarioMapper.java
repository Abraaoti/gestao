package br.ind.cmil.gestao.usuario.mapper;

import br.ind.cmil.gestao.perfil.domain.Perfil;
import br.ind.cmil.gestao.perfil.service.PerfilService;
import br.ind.cmil.gestao.usuario.domain.Usuario;
import br.ind.cmil.gestao.usuario.model.CriarUsuarioDTO;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 *
 * @author ti
 */
@Component
public class UsuarioMapper {

    @Autowired
    private PerfilService perfilService;
    @Autowired
    private PasswordEncoder encoder;

    public CriarUsuarioDTO toDTO(Usuario u) {
        List<String> roles = u.getPerfis().stream().map(p -> p.getTp().getValue()).collect(Collectors.toList());
        return new CriarUsuarioDTO(u.getNome(), u.getEmail(), u.getPassword(),u.isAtivo(), roles);
    }

    public Usuario toEntity(CriarUsuarioDTO dto) {

        Usuario u = new Usuario();
        u.setNome(dto.nome());
        u.setEmail(dto.email());
        u.setPassword(encoder.encode(dto.password()));
        u.setDataCadastro(LocalDateTime.now());      
        u.setAtivo(true);
        List<Perfil> perfis = perfilService.perfis(dto.perfis());
        u.setPerfis(perfis);
        
        return u;
    }

}
