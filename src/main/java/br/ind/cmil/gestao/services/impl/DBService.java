package br.ind.cmil.gestao.services.impl;

import br.ind.cmil.gestao.model.dto.CentroCustoDTO;
import br.ind.cmil.gestao.model.dto.DepartamentoDTO;
import br.ind.cmil.gestao.model.dto.FuncionarioDTO;
import br.ind.cmil.gestao.model.dto.PerfilDTO;
import br.ind.cmil.gestao.model.dto.UsuarioRequest;
import br.ind.cmil.gestao.domain.Cargo;
import br.ind.cmil.gestao.domain.Cartao;
import br.ind.cmil.gestao.model.dto.FrequenciaDTO;
import jakarta.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.ind.cmil.gestao.services.DepartamentoService;
import br.ind.cmil.gestao.services.CargoService;
import br.ind.cmil.gestao.services.CartaoService;
import br.ind.cmil.gestao.services.CentroCustoService;
import br.ind.cmil.gestao.services.FrequenciaService;
import br.ind.cmil.gestao.services.FuncionarioService;
import br.ind.cmil.gestao.services.PerfilService;
import br.ind.cmil.gestao.services.UsuarioService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

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
    @Autowired
    private CartaoService cartaoService;

    @Autowired
    private FrequenciaService frequenciaService;

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
        List<String> cargos = new ArrayList<>();
        cargos.add("administrador");
        cargos.add("analista de pessoas");
        cargos.add("analista de suporte");
        cargos.add("comprador");
        cargos.add("diretor");
        cargos.add("encarregado de obras");
        cargos.add("engenheiro");
        cargos.add("pedreiro");
        cargos.add("tst");
        for (int i = 0; i < cargos.size(); i++) {
            cs.salvar(new Cargo(cargos.get(i)));
        }
    }

    public void instanciaBaseCartoes() {
        List<String> cartoes = new ArrayList<>();
        cartoes.add(String.valueOf(new Random().nextInt(1000)));
        cartoes.add(String.valueOf(new Random().nextInt(1000)));
        cartoes.add(String.valueOf(new Random().nextInt(1000)));
        cartoes.add(String.valueOf(new Random().nextInt(1000)));

        for (int i = 0; i < cartoes.size(); i++) {
            cartaoService.salvar(new Cartao(cartoes.get(i)));
        }

    }

    public void instanciaBaseFuncionarios() {

        FuncionarioDTO analista = new FuncionarioDTO(null, "Abraão Calelesso", "Cassinda", LocalDate.of(1920, Month.NOVEMBER, 27), "01250284902", "v565786876", "adriana chipondia", "agostinha cassinda", "09987878", "masculino", "solteiro(a)", "lubango", LocalDate.now(), null, BigDecimal.valueOf(1800, 0), 6L, 5L, 3L);
        FuncionarioDTO tecnico = new FuncionarioDTO(null, "angelino", "manuel", LocalDate.of(1992, Month.JULY, 06), "01250284904", "998756547", "ana", "vicente", "76765765756", "masculino", "solteiro(a)", "vitória", LocalDate.now(), null, BigDecimal.valueOf(8000, 0), 4L, 5L, 3L);
        FuncionarioDTO rh = new FuncionarioDTO(null, "beatriz", "da silva campos", LocalDate.of(1995, Month.JULY, 06), "01250284903", "998756546", "marli bentos", "silva", "7678700", "feminino", "solteiro(a)", "gv", LocalDate.now(), null, BigDecimal.valueOf(1400, 0), 3L, 4L, 3L);
        FuncionarioDTO engenheiro = new FuncionarioDTO(null, "joão", "victor", LocalDate.of(1997, Month.JULY, 06), "01250284905", "998756548", "victorina", "victor", "6567565675", "masculino", "solteiro(a)", "serra-es", LocalDate.now(), null, BigDecimal.valueOf(8000, 00), 7L, 6L, 3L);
        List<FuncionarioDTO> funcionarios = new ArrayList<>();
        funcionarios.add(analista);
        funcionarios.add(rh);
        funcionarios.add(tecnico);
        funcionarios.add(engenheiro);
        for (FuncionarioDTO funcionario : funcionarios) {
            funcionarioService.salvar(funcionario);
        }

    }

    public void instanciaBasePresenca() {

        Set<FrequenciaDTO> frequencias = new HashSet<>();
        Set<Long> funcionarios1 = new HashSet<>();
        funcionarios1.add(1L);
        funcionarios1.add(2L);

        Set<Long> funcionarios2 = new HashSet<>();
        funcionarios2.add(3L);
        funcionarios2.add(4L);

        Set<Long> funcionarios3 = new HashSet<>();
        funcionarios3.add(1L);
        funcionarios3.add(2L);
        funcionarios3.add(3L);
        funcionarios3.add(4L);

        Set<Long> funcionarios4 = new HashSet<>();
        funcionarios4.add(1L);
        funcionarios4.add(2L);
        funcionarios4.add(3L);
        funcionarios4.add(4L);

       

        FrequenciaDTO frequencia_1 = new FrequenciaDTO(null, LocalDate.now(), "presente", funcionarios1);
        FrequenciaDTO frequencia_falta = new FrequenciaDTO(null, LocalDate.now(), "falta", funcionarios2);
        FrequenciaDTO frequencia_treinamento = new FrequenciaDTO(null, LocalDate.now(), "treinamento", funcionarios3);

        FrequenciaDTO frequencia_2 = new FrequenciaDTO(null, LocalDate.of(2023, Month.DECEMBER, 17), "presente", funcionarios4);

        FrequenciaDTO frequencia_3 = new FrequenciaDTO(null, LocalDate.of(2023, Month.DECEMBER, 14), "presente", funcionarios4);

        FrequenciaDTO frequencia_4 = new FrequenciaDTO(null, LocalDate.of(2023, Month.DECEMBER, 15), "falta", funcionarios1);

        FrequenciaDTO frequencia_5 = new FrequenciaDTO(null, LocalDate.of(2023, Month.DECEMBER, 16), "presente", funcionarios3);

        frequencias.add(frequencia_1);
        frequencias.add(frequencia_falta);
        frequencias.add(frequencia_treinamento);
        frequencias.add(frequencia_2);
        frequencias.add(frequencia_3);
        frequencias.add(frequencia_4);
        frequencias.add(frequencia_5);

        for (FrequenciaDTO frequencia1 : frequencias) {
            frequenciaService.salvar(frequencia1);
        }

    }

}
