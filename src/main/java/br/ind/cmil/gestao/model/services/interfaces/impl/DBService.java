package br.ind.cmil.gestao.model.services.interfaces.impl;

import br.ind.cmil.gestao.model.dto.CargoDTO;
import br.ind.cmil.gestao.model.dto.DepartamentoDTO;
import br.ind.cmil.gestao.model.dto.LotacaoDTO;
import br.ind.cmil.gestao.model.dto.PerfilDTO;
import br.ind.cmil.gestao.model.dto.UsuarioRequest;
import br.ind.cmil.gestao.model.services.interfaces.ICargoService;
import br.ind.cmil.gestao.model.services.interfaces.IDepartamentoService;
import br.ind.cmil.gestao.model.services.interfaces.IPerfilService;
import br.ind.cmil.gestao.model.services.interfaces.IUsuarioService;
import br.ind.cmil.gestao.model.services.interfaces.LotacaoService;
import jakarta.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author abraao
 */
@Service
public class DBService {

    @Autowired
    private LotacaoService ls;
    @Autowired
    private ICargoService cs;
    @Autowired
    private IDepartamentoService ds;
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

    public void instanciaBaseDeUsuarios() throws MessagingException {

        List<String> administrador = new ArrayList<>();

        List<String> assistente = new ArrayList<>();
        assistente.add("assistente");
        List<String> diretor = new ArrayList<>();
        diretor.add("diretor");

        List<String> auxiliar = new ArrayList<>();
        auxiliar.add("auxiliar");

        administrador.add("administrador");
        administrador.add("admin");
        administrador.addAll(auxiliar);
        administrador.addAll(assistente);
        administrador.addAll(diretor);

        List<UsuarioRequest> usuarios = new ArrayList<>();
        UsuarioRequest abraao = new UsuarioRequest(null, "Abraão".toLowerCase(), "dtimuila@gmail.com", "123", LocalDateTime.now(), null, true, null, administrador);
        UsuarioRequest beatriz = new UsuarioRequest(null, "beatriz".toLowerCase(), "contatos@timuila.com", "123", LocalDateTime.now(), null, true, null, assistente);
        UsuarioRequest angelino = new UsuarioRequest(null, "angelino".toLowerCase(), "elavokokassinda@gmail.com", "123", LocalDateTime.now(), null, true, null, auxiliar);
        usuarios.add(abraao);
        usuarios.add(angelino);
        usuarios.add(beatriz);

        for (UsuarioRequest usuario : usuarios) {
            us.register(usuario);
        }

    }

    public void instanciaBaseDepartamento() {
        List<String> departamentos = new ArrayList<>();
        departamentos.add("administrativo");
        departamentos.add("compras");
        departamentos.add("diretoria");
        departamentos.add("financeiro");
        departamentos.add("engenharia");
        departamentos.add("rh");
        for (int i = 0; i < departamentos.size(); i++) {
            ds.create(new DepartamentoDTO(null, departamentos.get(i)));
        }
    }

    public void instanciaBaseLotacao() {
        List<String> lotacoes = new ArrayList<>();
        lotacoes.add("amt");
        lotacoes.add("continuado");
        lotacoes.add("sede");
        lotacoes.add("unigal");
        for (int i = 0; i < lotacoes.size(); i++) {
            ls.save(new LotacaoDTO(null, lotacoes.get(i)));
        }

    }

    public void instanciaBaseCargos() {
        List<String> cargos = new ArrayList<>();
        cargos.add("administrador");
        cargos.add("analista de pessoas");
        cargos.add("comprador");
        cargos.add("diretor");
        cargos.add("engenheiro");
        cargos.add("tst");
        for (int i = 0; i < cargos.size(); i++) {
            cs.create(new CargoDTO(null, cargos.get(i)));
        }

    }

}
