package br.ind.cmil.gestao.model.services.interfaces.impl;

import br.ind.cmil.gestao.model.dto.mappers.PerfilMapper;
import br.ind.cmil.gestao.model.entidades.Perfil;
import br.ind.cmil.gestao.model.entidades.Usuario;
import br.ind.cmil.gestao.model.repositorys.IUsuarioRepository;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author abraao
 */
@Service
public class DBService {

   
    @Autowired
    private PerfilMapper pm;
    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private IUsuarioRepository ur;

    public void instanciaBaseDeDados() {

        Set<String> tipoPerfil = new HashSet<>();

        tipoPerfil.add("usuário");
        tipoPerfil.add("administrador");
        tipoPerfil.add("administrativo");
        tipoPerfil.add("comprador");
        tipoPerfil.add("diretor");
        tipoPerfil.add("engenheiro");
        tipoPerfil.add("financeiro");
        tipoPerfil.add("funcionário");
        tipoPerfil.add("técnico");
        Perfil pe = new Perfil();
        for (String perfil : tipoPerfil) {
            pe.setTp(pm.convertPerfilValue(perfil));

        }
        Set<Perfil> perfis = new HashSet<>();
        Usuario usuario = new Usuario();
       
        perfis.add(pe);

        usuario.setNome("abraao calelesso");
        usuario.setEmail("abraao@cmil.com.br");
        usuario.setPassword(encoder.encode("123"));
        usuario.setDataCadastro(LocalDateTime.now());
        usuario.setAtivo(false);
        usuario.setPerfis(perfis);
        ur.save(usuario);

    }

}
