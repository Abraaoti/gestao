package br.ind.cmil.gestao.model.services.interfaces.impl;

import br.ind.cmil.gestao.dto.CargoDTO;
import br.ind.cmil.gestao.dto.DepartamentoDTO;
import br.ind.cmil.gestao.dto.LotacaoDTO;
import br.ind.cmil.gestao.dto.PerfilDTO;
import br.ind.cmil.gestao.dto.UsuarioRequest;
import br.ind.cmil.gestao.model.entidades.Cargo;
import br.ind.cmil.gestao.model.entidades.Departamento;
import br.ind.cmil.gestao.model.entidades.Funcionario;
import br.ind.cmil.gestao.model.entidades.Lotacao;
import br.ind.cmil.gestao.model.enums.EstadoCivil;
import br.ind.cmil.gestao.model.enums.Genero;
import br.ind.cmil.gestao.model.services.interfaces.LotacaoService;
import jakarta.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.ind.cmil.gestao.model.services.interfaces.DepartamentoService;
import br.ind.cmil.gestao.model.services.interfaces.CargoService;
import br.ind.cmil.gestao.model.services.interfaces.FuncionarioService;
import br.ind.cmil.gestao.model.services.interfaces.PerfilService;
import br.ind.cmil.gestao.model.services.interfaces.UsuarioService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

/**
 *
 * @author abraao
 */
@Service
public class DBService {

    @Autowired
    private LotacaoService ls;
    @Autowired
    private CargoService cs;
    @Autowired
    private DepartamentoService ds;
    @Autowired
    private FuncionarioService funcionarioService;
    @Autowired
    private PerfilService ps;
    @Autowired
    private UsuarioService us;

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
        departamentos.add("ti");
        departamentos.add("rh");
        for (int i = 0; i < departamentos.size(); i++) {
            ds.salvar(new Departamento(departamentos.get(i)));
        }
    }

    public void instanciaBaseLotacao() {
        List<String> lotacoes = new ArrayList<>();
        lotacoes.add("amt");
        lotacoes.add("continuado");
        lotacoes.add("sede");
        lotacoes.add("unigal");
        for (int i = 0; i < lotacoes.size(); i++) {
            ls.salvar(new Lotacao(lotacoes.get(i)));
        }

    }

    public void instanciaBaseCargos() {
        List<String> cargos = new ArrayList<>();
        cargos.add("administrador");
        cargos.add("analista de pessoas");
        cargos.add("analista de suporte");
        cargos.add("comprador");
        cargos.add("diretor");
        cargos.add("engenheiro");
        cargos.add("tst");
        for (int i = 0; i < cargos.size(); i++) {
            cs.salvar(new Cargo(cargos.get(i)));
        }

    }

    public void instanciaBaseFuncionarios() {

        Funcionario funcionario = new Funcionario();
        funcionario.setId(null);
        funcionario.setNome("Abraão Calelesso");
        funcionario.setSobrenome("Cassinda");
        funcionario.setNascimento(LocalDate.of(1920, Month.NOVEMBER, 27));
        funcionario.setCpf("01250284902");
        funcionario.setRg("v565656");
        funcionario.setMae("adriana chipondia");
        funcionario.setPai("agostinha cassinda");
        funcionario.setClt("n76787878");
        funcionario.setGenero(Genero.convertGeneroValue("masculino"));
        funcionario.setEstado_civil(EstadoCivil.findTipo("solteiro(a)"));
        funcionario.setNaturalidade("lubango");
        funcionario.setAdmissao(LocalDate.now());
        funcionario.setDemissao(null);
        funcionario.setSalario(BigDecimal.valueOf(1800, 0));
        funcionario.setDepartamento(ds.findByNome("ti"));
        funcionario.setCargo(cs.findByNome("analista de suporte"));
        funcionario.setLotacao(ls.findByNome("unigal"));
         
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(funcionario);
        for (Funcionario funcionario1 : funcionarios) {
             funcionarioService.salvar(funcionario1);
        }
        

    }

}
