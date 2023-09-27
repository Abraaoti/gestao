package br.ind.cmil.gestao.model.services.interfaces.impl;

import br.ind.cmil.gestao.model.dto.PerfilDTO;
import br.ind.cmil.gestao.model.dto.RegistrarUsuario;
import br.ind.cmil.gestao.model.services.interfaces.IPerfilService;
import br.ind.cmil.gestao.model.services.interfaces.IUsuarioService;
import jakarta.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author abraao
 */
@Service
public class DBService {

    @Autowired
    private IPerfilService ps;

    @Autowired
    private IUsuarioService us;

    public void instanciaBaseDePerfis() {
        List<String> perfis = new ArrayList<>();
        perfis.add("admin");
        perfis.add("administrador");
        perfis.add("assistente");
        perfis.add("auxiliar");
        perfis.add("comprador");
        perfis.add("diretor");
        perfis.add("engenheiro");
        perfis.add("funcionário");
        perfis.add("gerente");
        perfis.add("líder");
        perfis.add("técnico");
        perfis.add("usuário");
        for (int i = 0; i < perfis.size(); i++) {
            ps.create(new PerfilDTO(null, perfis.get(i)));
        }

    }

    public void instanciaBaseDeDados() throws MessagingException {

        Set<String> perfis = new HashSet<>();
        Set<String> perfis2 = new HashSet<>();
        perfis2.add("auxiliar");
        Set<String> perfis3 = new HashSet<>();
        perfis3.add("assistente");

        perfis.add("admin");
        perfis.add("administrador");
        perfis.addAll(perfis2);
        perfis.addAll(perfis3);

        Set<RegistrarUsuario> usuarios = new HashSet<>();
        RegistrarUsuario abraao = new RegistrarUsuario(null, "Abraão".toLowerCase(), "dtimuila@gmail.com", "123", LocalDateTime.now(), null, false, null, perfis);
        RegistrarUsuario beatriz = new RegistrarUsuario(null, "beatriz".toLowerCase(), "contatos@timuila.com", "123", LocalDateTime.now(), null, false, null, perfis3);
        RegistrarUsuario angelino = new RegistrarUsuario(null, "angelino".toLowerCase(), "elavokokassinda@gmail.com", "123", LocalDateTime.now(), null, false, null, perfis2);
        usuarios.add(abraao);
        usuarios.add(angelino);
        usuarios.add(beatriz);
        String url = "http://localhost:8080";

        for (RegistrarUsuario usuario : usuarios) {
            us.salvarUsuarioGeral(usuario, url);
        }

    }

}
