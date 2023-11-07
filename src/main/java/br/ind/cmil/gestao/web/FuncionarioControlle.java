package br.ind.cmil.gestao.web;

import br.ind.cmil.gestao.model.entidades.Funcionario;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import br.ind.cmil.gestao.model.services.interfaces.LotacaoService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import br.ind.cmil.gestao.model.services.interfaces.DepartamentoService;
import br.ind.cmil.gestao.model.services.interfaces.CargoService;
import br.ind.cmil.gestao.model.services.interfaces.FuncionarioService;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 *
 * @author abraao
 */
@RequiredArgsConstructor
@Controller
@RequestMapping("/funcionarios")
public class FuncionarioControlle {

    private final FuncionarioService funcionarioService;
    private final DepartamentoService departamentoService;
    private final CargoService cargoService;
    private final LotacaoService lotacaoService;

    @GetMapping("/add")
    public String form(Model model, Funcionario funcionario) {
        model.addAttribute("funcionario", funcionario);
        model.addAttribute("departamentos", departamentoService.lista());
        model.addAttribute("cargos", cargoService.lista());      
        model.addAttribute("lotacoes", lotacaoService.lista());        
       
        return "funcionarios/funcionario";
    }

    @PostMapping("/salvar")
    public ModelAndView save(@ModelAttribute Funcionario funcionario, RedirectAttributes redir) {
     
        funcionarioService.salvar(funcionario);
        redir.addFlashAttribute("sucesso", "Operação realizada com sucesso");
        return new ModelAndView("redirect:/funcionarios/add");
    }

    @PostMapping("/editar")
    public ModelAndView update(@ModelAttribute Funcionario funcionario, RedirectAttributes redir) {
      
         funcionarioService.salvar(funcionario);
        redir.addFlashAttribute("sucesso", "Operação realizada com sucesso");
        return new ModelAndView("redirect:/funcionarios/add");
    }

    @GetMapping("/lista")
    public String lista() {
        return "funcionarios/funcionarios";

    }

   
    @GetMapping("/editar/{id}")
    public String editar(Model model, @PathVariable("id") Long id) {
        Funcionario funcionario = funcionarioService.findById(id);      
        
        model.addAttribute("funcionario", funcionario);
        model.addAttribute("departamentos", departamentoService.lista());
        model.addAttribute("cargos", cargoService.lista());       
        model.addAttribute("lotacoes", lotacaoService.lista());
        return "funcionarios/funcionario";
    }

    @GetMapping("/delete/{id}")
    public ModelAndView excluir(@PathVariable("id") Long id) {
        Map<String, Object> model = new HashMap<>();
        funcionarioService.delete(id);
        model.put("sucesso", "Operação realizada com sucesso.");

        return new ModelAndView("funcinarios/funcionarios", model);
    }

    @GetMapping("/datatables/server")
    public ResponseEntity<?> funcionarios(HttpServletRequest request) {
        //model.addAttribute("perfis", ps.lista(pageable));
        // return "perfis/perfis";
        return ResponseEntity.ok(funcionarioService.buscarTodos(request));
    }

}
