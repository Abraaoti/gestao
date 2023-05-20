
package br.ind.cmil.gestao.controlles;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author abraao
 */
@Validated
@RestController
@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:4200/")
public class HomeController {
    
    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/admin")
    public String getAdminPage() {
        return "adminPage";
    }

    @GetMapping("/analista")
    public String getAnalistaPage() {
        return "analistaPage";
    }
    @GetMapping("/comprador")
    public String getCompradorPage() {
        return "compradorPage";
    }

    @GetMapping("/diretor")
    public String getDiretorPage() {
        return "diretorPage";
    }

    @GetMapping("/finaceiro")
    public String getFinanceiroPage() {
        return "finaceiroPage";
    }

    @GetMapping("/gerente")
    public String getManagerPage() {
        return "gerentePage";
    }

    @GetMapping("/empregado")
    public String getEmployeePage() {
        return "empregadoPage";
    }

    @GetMapping("/hr")
    public String getHrPage() {
        return "hrPage";
    }

    @GetMapping("/common")
    public String getCommonPage() {
        return "commonPage";
    }

    @GetMapping("/tecnico")
    public String getTecnicoPage() {
        return "tecnicoPage";
    }

  
     @GetMapping("/acesso-negado")
    public String acessoNegado(Model model, HttpServletResponse response) {
        
        model.addAttribute("status", response.getStatus());
        model.addAttribute("error", "Acesso Negado");
        model.addAttribute("message", "Não tem permissão para acessar esta página");
        model.addAttribute("subtexto", "Acesso permitido apenas para cadastros já ativados");
        return "error/403";
    }

}
