package br.ind.cmil.gestao.controller;

import br.ind.cmil.gestao.funcionario.services.FuncionarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author abraao
 */
@Controller
public class HomeController {

  @Autowired
  private FuncionarioService funcionarioService;

    private static final Random RANDOM = new Random(System.currentTimeMillis());

    @GetMapping("/")
    public String home(HttpServletResponse response, Model model) {
        model.addAttribute("chartData", getChartData());
        return "home/index";
    }

    @GetMapping({"/login"})
    public String login() {

        return "login";
    }
     @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(null);
        return "redirect:/login";
    }

  /**  @RequestMapping("/estatistica")
    public ResponseEntity<?> estatistica() {
        Map<String, Object> map = new HashMap();
        map.put("iFuncionarioCount", funcionarioService.countById());
        //map.put("iProcessoFinanceiroCount", iProcessoFinanceiroService.countId());
        //map.put("iContaPagarCount", iContaPagarService.countId());
        // map.put("iUsuarioCount", iUsuarioService.countId());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
*/
    @GetMapping("/login-error")
    public String loginError(ModelMap model, HttpServletRequest resp) {
        HttpSession session = resp.getSession();
        String lastException = String.valueOf(session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION"));
        if (lastException.contains(SessionAuthenticationException.class.getName())) {
            model.addAttribute("alerta", "erro");
            model.addAttribute("titulo", "Acesso recusado!");
            model.addAttribute("texto", "Você já está logado em outro dispositivo.");
            model.addAttribute("subtexto", "Faça o logout ou espere sua sessão expirar.");
            return "login";
        }
        model.addAttribute("alerta", "erro");
        model.addAttribute("titulo", "Crendenciais inválidas!");
        model.addAttribute("texto", "Login ou senha incorretos, tente novamente.");
        model.addAttribute("subtexto", "Acesso permitido apenas para cadastros já ativados.");
        return "login";
    }
/**
    @GetMapping("/multiplelinechart")
    public ResponseEntity<?> funcionarios() {
        Map<String, List<FuncionarioDTO>> mappedData = new HashMap<>();
        List<FuncionarioDTO> dataList = funcionarioService.list();

        for (FuncionarioDTO data : dataList) {

            if (mappedData.containsKey(data.nome())) {
              mappedData.get(data.nome()).add(data);
            } else {
                List<FuncionarioDTO> tempList = new ArrayList<>();
                tempList.add(data);
                mappedData.put(data.nome(), tempList);
            }

        }
        return new ResponseEntity<>(mappedData, HttpStatus.OK);
    }*/

    @GetMapping("/expired")
    public String sessaoExpirada(ModelMap model) {
        model.addAttribute("alerta", "erro");
        model.addAttribute("titulo", "Acesso recusado!");
        model.addAttribute("texto", "Sua sessão expirou.");
        model.addAttribute("subtexto", "Você logou em outro dispositivo");
        return "login";
    }

    @GetMapping("/negado")
    public String acessoNegado(ModelMap model, HttpServletResponse resp) {
        model.addAttribute("status", resp.getStatus());
        model.addAttribute("error", "Acesso Negado");
        model.addAttribute("message", "Você não tem permissão para acesso a esta área ou ação.");
        return "error";
    }

    /**
     * @GetMapping("/grafico") public String grafico(Model model) {
     * model.addAttribute("chartData", getChartData()); return "grafico"; }*
     */
    private List<List<Object>> getChartData() {
        return List.of(
                List.of("Janeiro", RANDOM.nextInt(5)),
                List.of("Fevereiro", RANDOM.nextInt(5)),
                List.of("Marco", RANDOM.nextInt(5)),
                List.of("Abril", RANDOM.nextInt(5)),
                List.of("Maio", RANDOM.nextInt(5)),
                List.of("Junho", RANDOM.nextInt(5)),
                List.of("Julho", RANDOM.nextInt(5)),
                List.of("Agosto", RANDOM.nextInt(5)),
                List.of("Setembro", RANDOM.nextInt(5)),
                List.of("Outubro", RANDOM.nextInt(1)),
                List.of("Novembro", RANDOM.nextInt(2)),
                List.of("Dezembro", RANDOM.nextInt(3))
        );
    }
}
