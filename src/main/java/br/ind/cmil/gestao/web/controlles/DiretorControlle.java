package br.ind.cmil.gestao.web.controlles;

import br.ind.cmil.gestao.model.dto.DiretorDTO;
import br.ind.cmil.gestao.model.services.interfaces.IDiretorService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author abraao
 */

@RequiredArgsConstructor
@Controller
@RequestMapping("diretor")
public class DiretorControlle {

    private final IDiretorService ds;

    @GetMapping("/lista")
    public String list() {
        return "diretor/diretores";
    }

    @GetMapping("/dados")
    public String form(DiretorDTO diretor, Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("diretor", ds.form(diretor, user));
        return "diretor/cadastro";
    }

    @PostMapping("/create")
    public ModelAndView save(@ModelAttribute DiretorDTO diretor, RedirectAttributes redir) {
        ds.create(diretor);
        redir.addFlashAttribute("sucesso", "Operação realizada com sucesso");
        return new ModelAndView("redirect:/diretor/dados");
    }

    @PutMapping("/update")
    public ModelAndView update(@ModelAttribute DiretorDTO diretor, RedirectAttributes redir) {
        ds.create(diretor);
        redir.addFlashAttribute("sucesso", "Operação realizada com sucesso");
        return new ModelAndView("redirect:/diretor/dados");
    }

    @GetMapping("/editar/{id}")
    public String preEditar(Model model, @PathVariable("id") Long id, Pageable pageable) {

        model.addAttribute("diretor", ds.findById(id));
        return "diretor/cadastro";
    }

    @GetMapping("/delete/{id}")
    public ModelAndView excluir(@PathVariable("id") Long id) {
        Map<String, Object> model = new HashMap<>();
        ds.delete(id);
        model.put("sucesso", "Operação realizada com sucesso.");
        return new ModelAndView("diretor/diretores", model);
    }

    @GetMapping("/datatables/server")
    public ResponseEntity<?> administradores(HttpServletRequest request) {       
        return ResponseEntity.ok(ds.diretores(request));
    }
}
