package br.ind.cmil.gestao.model.services.interfaces.impl;

import br.ind.cmil.gestao.model.dto.mappers.PerfilMapper;
import br.ind.cmil.gestao.model.entidades.Perfil;
import br.ind.cmil.gestao.model.entidades.Usuario;
import br.ind.cmil.gestao.model.repositorys.IUsuarioRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
        List<Usuario> usuarios = new ArrayList<>();
        Usuario usuario = new Usuario();

        perfis.add(pe);

        usuario.setNome("abraao calelesso");
        usuario.setEmail("abraao@cmil.com.br");
        usuario.setPassword(encoder.encode("123"));
        usuario.setDataCadastro(LocalDateTime.now());
        usuario.setAtivo(false);
        usuario.setPerfis(perfis);
        //ur.save(usuario);

        Usuario usuario2 = new Usuario();

        perfis.add(pe);

        usuario2.setNome("cmil");
        usuario2.setEmail("cmil@cmil.com.br");
        usuario2.setPassword(encoder.encode("123"));
        usuario2.setDataCadastro(LocalDateTime.now());
        usuario2.setAtivo(false);
        usuario2.setPerfis(perfis);
        usuarios.add(usuario);
        usuarios.add(usuario2);
        ur.saveAll(usuarios);

    }

}
