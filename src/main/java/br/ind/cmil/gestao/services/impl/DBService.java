package br.ind.cmil.gestao.services.impl;

import br.ind.cmil.gestao.cargo.domain.Cargo;
import br.ind.cmil.gestao.cargo.service.CargoService;
import br.ind.cmil.gestao.centro.model.CentroCustoDTO;
import br.ind.cmil.gestao.centro.service.CentroCustoService;
import br.ind.cmil.gestao.departamento.model.DepartamentoDTO;
import br.ind.cmil.gestao.departamento.service.DepartamentoService;
import br.ind.cmil.gestao.funcionario.model.FuncionarioDTO;
import br.ind.cmil.gestao.funcionario.services.FuncionarioService;
import br.ind.cmil.gestao.perfil.model.PerfilDTO;
import br.ind.cmil.gestao.perfil.service.PerfilService;
import jakarta.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.ind.cmil.gestao.usuario.model.UsuarioRequest;
import br.ind.cmil.gestao.usuario.service.UsuarioService;
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
    private CentroCustoService centroService;
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
        UsuarioRequest cmil = new UsuarioRequest(null, "cmil".toLowerCase(), "cmil@cmil.com.br", "123", LocalDateTime.now(), null, true, null, administrador);
        UsuarioRequest abraao = new UsuarioRequest(null, "Abraão".toLowerCase(), "dtimuila@gmail.com", "123", LocalDateTime.now(), null, true, null, administrador);

        UsuarioRequest beatriz = new UsuarioRequest(null, "beatriz".toLowerCase(), "contatos@timuila.com", "123", LocalDateTime.now(), null, true, null, assistente);
        UsuarioRequest angelino = new UsuarioRequest(null, "angelino".toLowerCase(), "elavokokassinda@gmail.com", "123", LocalDateTime.now(), null, true, null, auxiliar);
        usuarios.add(abraao);
        usuarios.add(angelino);
        usuarios.add(beatriz);
        usuarios.add(cmil);

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
        departamentos.add("pessoal");
        departamentos.add("ti");
        departamentos.add("técnico");
        departamentos.add("rh");
        for (int i = 0; i < departamentos.size(); i++) {
            ds.salvar(new DepartamentoDTO(null, departamentos.get(i)));
        }
    }

    public void instanciaBaseCentroCusto() {
        List<String> centroCustos = new ArrayList<>();
        centroCustos.add("amt");
        centroCustos.add("continuado");
        centroCustos.add("sede");
        centroCustos.add("unigal");

        for (int i = 0; i < centroCustos.size(); i++) {
            centroService.create(new CentroCustoDTO(null, centroCustos.get(i)));
        }

    }

    public void instanciaBaseCargos() {
        List<Cargo> cargos = new ArrayList<>();

        Cargo cargo = new Cargo();
        cargo.setNome("administrador");
        cargo.setSalario(BigDecimal.valueOf(8000, 0));
        Cargo cargo2 = new Cargo();
        cargo2.setNome("analista de pessoas");
        cargo2.setSalario(BigDecimal.valueOf(2300, 0));
        Cargo cargo3 = new Cargo();
        cargo3.setNome("analista de suporte");
        cargo3.setSalario(BigDecimal.valueOf(2000, 0));
        Cargo cargo4 = new Cargo();
        cargo4.setNome("comprador");
        cargo4.setSalario(BigDecimal.valueOf(3200, 0));
        Cargo cargo5 = new Cargo();
        cargo5.setNome("diretor");
        cargo5.setSalario(BigDecimal.valueOf(18000, 0));
        Cargo cargo6 = new Cargo();
        cargo6.setNome("encarregado de obras");
        cargo6.setSalario(BigDecimal.valueOf(1800, 0));
        Cargo cargo7 = new Cargo();
        cargo7.setNome("engenheiro");
        cargo7.setSalario(BigDecimal.valueOf(10000, 0));
        Cargo cargo8 = new Cargo();
        cargo8.setNome("pedreiro");
        cargo8.setSalario(BigDecimal.valueOf(1800, 0));
        Cargo cargo9 = new Cargo();
        cargo9.setNome("tst");
        cargo9.setSalario(BigDecimal.valueOf(1800, 0));
        cargos.add(cargo);
        cargos.add(cargo2);
        cargos.add(cargo3);
        cargos.add(cargo4);
        cargos.add(cargo5);
        cargos.add(cargo6);
        cargos.add(cargo7);
        cargos.add(cargo8);
        cargos.add(cargo9);

        for (Cargo c : cargos) {
            cs.salvar(c);
        }
    }

    public void instanciaBaseFuncionarios() {

        FuncionarioDTO abraao = new FuncionarioDTO(null, "Abraão Calelesso", "Cassinda", LocalDate.of(1920, Month.NOVEMBER, 27), "01250284902", "v565786876", "adriana chipondia", "agostinha cassinda", "09987878", "masculino", "solteiro(a)", "lubango", LocalDate.now(), null, 6L, 5L, 3L);
        FuncionarioDTO angelino = new FuncionarioDTO(null, "angelino", "manuel", LocalDate.of(1992, Month.JULY, 06), "01250284904", "998756547", "ana", "vicente", "76765765756", "masculino", "solteiro(a)", "vitória", LocalDate.now(), null, 4L, 5L, 3L);
        FuncionarioDTO beatriz = new FuncionarioDTO(null, "beatriz", "da silva campos", LocalDate.of(1995, Month.JULY, 06), "01250284903", "998756546", "marli bentos", "silva", "7678700", "feminino", "solteiro(a)", "gv", LocalDate.now(), null, 3L, 4L, 3L);
        FuncionarioDTO joao = new FuncionarioDTO(null, "joão", "victor", LocalDate.of(1997, Month.JULY, 06), "01250284905", "998756548", "victorina", "victor", "6567565675", "masculino", "solteiro(a)", "serra-es", LocalDate.now(), null, 7L, 6L, 3L);
        List<FuncionarioDTO> funcionarios = new ArrayList<>();
        funcionarios.add(abraao);
        funcionarios.add(beatriz);
        funcionarios.add(angelino);
        funcionarios.add(joao);
        for (FuncionarioDTO funcionario : funcionarios) {
            funcionarioService.salvar(funcionario);
        }

    }

}
