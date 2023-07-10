package br.ind.cmil.gestao.controlles;


import br.ind.cmil.gestao.model.entidades.Endereco;
import br.ind.cmil.gestao.model.services.interfaces.IEnderecoService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author cmilseg
 */
@RestController
@RequestMapping("enderecos")
public class EnderecoController {

    private final IEnderecoService enderecoService;
    private final String ENDERECO = "adm/perfil/enderecos/";

    public EnderecoController(IEnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

   

    @GetMapping("/add")
    public String formEndereco(ModelMap model) {      
      
        model.addAttribute("endereco", new Endereco());
        return ENDERECO + "addEndereco";
    }

    @GetMapping("/lista")
    public ModelAndView getEndereco() {
        Map<String, Object> model = new HashMap<>();
        // model.put("centros", enderecoService.listAllCentroCusto());
        return new ModelAndView(ENDERECO + "enderecos", model);
    }

    @PostMapping("/salvar")
    public String salvar(Endereco endereco, RedirectAttributes attr, @AuthenticationPrincipal User user) {

        
        enderecoService.save(endereco);
        attr.addFlashAttribute("sucesso", "Operação realizada com sucesso");

        return "redirect:/enderecos/add/"+ endereco.getPessoa().getId();
    }

    @PostMapping("/editar")
    public ModelAndView editar(Endereco endereco, RedirectAttributes redir) {
        // Map<String, Object> model = new HashMap<>();
        enderecoService.update(endereco);

        redir.addFlashAttribute("sucesso", "Operação realizada com sucesso");
        // model.clear();
        return new ModelAndView("redirect:/enderecos");
    }

    @GetMapping("/editar/{id}")
    public ModelAndView preEditar(@PathVariable("id") Long id) {
        Map<String, Object> model = new HashMap<>();
        model.put("endereco", enderecoService.buscarEnderecoPorId(id));
        return new ModelAndView(ENDERECO + "endereco", model);
    }

    @GetMapping("/excluir/{id}")
    public ModelAndView excluir(@PathVariable("id") Long id) {
        Map<String, Object> model = new HashMap<>();
        enderecoService.delete(id);
        model.put("sucesso", "Operação realizada com sucesso.");
        return new ModelAndView("redirect:/enderecos", model);
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deletar(@PathVariable("id") Long id) {
        Map<String, Object> model = new HashMap<>();
        enderecoService.delete(id);
        model.put("sucesso", "Operação realizada com sucesso.");
        return new ModelAndView("redirect:/enderecos", model);
    }

  

    @GetMapping("/titulo")
    public ResponseEntity<?> getEspecialidadesPorTermo(@RequestParam("termo") String termo) {
        List<String> endereco = (List<String>) enderecoService.buscarPorCep(termo);
        return ResponseEntity.ok(endereco);
    }

}
