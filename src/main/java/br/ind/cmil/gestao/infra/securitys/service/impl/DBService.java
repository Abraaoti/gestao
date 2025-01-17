package br.ind.cmil.gestao.infra.securitys.service.impl;

import br.ind.cmil.gestao.cargo.domain.Cargo;
import br.ind.cmil.gestao.cargo.service.CargoService;
import br.ind.cmil.gestao.centro.model.CentroCustoDTO;
import br.ind.cmil.gestao.centro.service.CentroCustoService;
import br.ind.cmil.gestao.departamento.model.DepartamentoDTO;
import br.ind.cmil.gestao.departamento.service.DepartamentoService;
import br.ind.cmil.gestao.funcionario.model.CriarFuncionarioDTO;
import br.ind.cmil.gestao.funcionario.services.FuncionarioService;
import br.ind.cmil.gestao.perfil.model.PerfilDTO;
import br.ind.cmil.gestao.perfil.service.PerfilService;
import br.ind.cmil.gestao.pessoa.domain.PessoaJuridica;
import br.ind.cmil.gestao.pessoa.service.PessoaJuridicaService;
import jakarta.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.ind.cmil.gestao.usuario.model.CriarUsuarioDTO;
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
    private PessoaJuridicaService pessoaJuridicaService;
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
    
    public void instanciaBaseDeEmpresa() {        
        PessoaJuridica empresa = new PessoaJuridica();
        empresa.setNome("cmil - construção e manutenção industrial ltda");
        empresa.setSobrenome("cmil - construção e manutenção industrial ltda");
        empresa.setNascimento(LocalDate.of(1995, Month.APRIL, 05));
        empresa.setCnpj("39811898000113");
        empresa.setIe("896565656");
        empresa.setIm("665455465");
        empresa.setCapital(BigDecimal.valueOf(300000, 0));
        pessoaJuridicaService.save(empresa);
        
    }
    
    public void instanciaBaseDePerfis() {
        List<String> perfis = new ArrayList<>();
        perfis.add("admin");
        perfis.add("administrativo");
        perfis.add("assistente");
        perfis.add("auxiliar");
        perfis.add("comprador");
        perfis.add("diretor");
        perfis.add("engenheiro");
        perfis.add("gerente");
        perfis.add("rh");
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
        
        administrador.add("administrativo");
        administrador.add("admin");
        administrador.addAll(auxiliar);
        administrador.addAll(assistente);
        administrador.addAll(diretor);
        
        List<CriarUsuarioDTO> usuarios = new ArrayList<>();
        CriarUsuarioDTO cmil = new CriarUsuarioDTO( "cmil".toLowerCase(), "cmil@cmil.com.br", "123",Boolean.TRUE, administrador);
        CriarUsuarioDTO abraao = new CriarUsuarioDTO( "Abraão".toLowerCase(), "dtimuila@gmail.com", "123",Boolean.TRUE,  administrador);
        
        CriarUsuarioDTO beatriz = new CriarUsuarioDTO( "beatriz".toLowerCase(), "contatos@timuila.com", "123",Boolean.TRUE,  assistente);
        CriarUsuarioDTO angelino = new CriarUsuarioDTO( "angelino".toLowerCase(), "elavokokassinda@gmail.com", "123",Boolean.TRUE, auxiliar);
        usuarios.add(abraao);
        usuarios.add(angelino);
        usuarios.add(beatriz);
        usuarios.add(cmil);
        
        for (CriarUsuarioDTO usuario : usuarios) {
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
        
        CriarFuncionarioDTO abraao = new CriarFuncionarioDTO(null, "Abraão Calelesso", "Cassinda", LocalDate.of(1920, Month.NOVEMBER, 27), "01250284902", "v565786876", "adriana chipondia", "agostinha cassinda", "masculino", "solteiro(a)", "lubango", "09987878", 6L, 5L, 1L);
        CriarFuncionarioDTO angelino = new CriarFuncionarioDTO(null, "angelino lismo", "cassinda", LocalDate.of(1992, Month.JANUARY, 26), "01250284904", "998756547", "ana", "vicente", "masculino", "solteiro(a)", "vitória", "76765765756",  4L, 5L, 1L);
        CriarFuncionarioDTO beatriz = new CriarFuncionarioDTO(null, "beatriz", "da silva campos", LocalDate.of(1995, Month.JULY, 06), "01250284903", "998756546", "marli bentos", "silva", "feminino", "solteiro(a)", "gv", "7678700",  3L, 4L, 1L);
        CriarFuncionarioDTO joao = new CriarFuncionarioDTO(null, "joão baptista", "pongolola pinto", LocalDate.of(1997, Month.JUNE, 12), "01250284905", "998756548", "victorina", "victor", "masculino", "solteiro(a)", "serra-es", "6567565675",  7L, 6L, 1L);
        List<CriarFuncionarioDTO> funcionarios = new ArrayList<>();
        funcionarios.add(abraao);
        funcionarios.add(beatriz);
        funcionarios.add(angelino);
        funcionarios.add(joao);
        for (CriarFuncionarioDTO funcionario : funcionarios) {
            funcionarioService.save(funcionario);
        }
        
    }
    
}
