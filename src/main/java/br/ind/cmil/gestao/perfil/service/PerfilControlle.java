
package br.ind.cmil.gestao.perfil.service;

import br.ind.cmil.gestao.perfil.model.PerfilDTO;
import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author ti
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
@Controller
@RequestMapping("/perfis")
public class PerfilControlle {

    private final PerfilService ps;

    @GetMapping("/add")
    public String form(Model model, PerfilDTO perfilDTO) {
        model.addAttribute("perfil", perfilDTO);
        return "perfis/perfil";
    }

    @PostMapping("/salvar")
    public ModelAndView save(@ModelAttribute PerfilDTO perfilDTO, RedirectAttributes redir) {
        ps.create(perfilDTO);
        redir.addFlashAttribute("sucesso", "Operação realizada com sucesso");
        return new ModelAndView("redirect:/perfis/add");
    }
    
     @GetMapping("/perfis")
    public Set<PerfilDTO> getPerfis(Pageable pageable) {
        return ps.list(pageable);
    }

    @GetMapping
    public String perfis() {      
        return "perfis/lista";
    }

    @PostMapping("/editar")
    public String editarPerfil(@ModelAttribute PerfilDTO perfilDTO, RedirectAttributes redir) {
        ps.create(perfilDTO);
        redir.addFlashAttribute("sucesso", "Operação realizada com sucesso");
        return "redirect:/perfis";
    }

    @GetMapping("/editar/{id}")
    public String preEditar(Model model, @PathVariable("id") Long id, Pageable pageable) {
        model.addAttribute("perfis", ps.list(pageable));
        model.addAttribute("perfil", ps.findById(id));
        return "perfis/perfil";
    }

    @GetMapping("/excluir/{id}")
    public ModelAndView excluir(@PathVariable("id") Long id) {
        Map<String, Object> model = new HashMap<>();
        ps.delete(id);
        model.put("sucesso", "Operação realizada com sucesso.");
        return new ModelAndView("perfis/perfis", model);
    }

    @GetMapping("/datatables/server")
    public ResponseEntity<?> perfis(HttpServletRequest request) {
     
        return ResponseEntity.ok(ps.buscarTodos(request));
    }


}
