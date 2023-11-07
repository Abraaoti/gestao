package br.ind.cmil.gestao.web;

import br.ind.cmil.gestao.model.dto.mappers.UsuarioMapper;
import br.ind.cmil.gestao.model.entidades.AuxiliarAdministrativo;
import br.ind.cmil.gestao.model.entidades.Usuario;
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
import br.ind.cmil.gestao.model.services.interfaces.AuxiliarAdministrativoService;
import br.ind.cmil.gestao.model.services.interfaces.UsuarioService;

/**
 *
 * @author abraao
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("auxiliar")
public class AuxiliarAdministrativoControlle {

    private final AuxiliarAdministrativoService as;
    private final UsuarioService usuarioService;

    @GetMapping("/lista")
    public String list() {
        return "auxiliar/auxliares";
    }

    @GetMapping("/dados")
    public String form(AuxiliarAdministrativo auxiliar, Model model, @AuthenticationPrincipal User user) {        
        model.addAttribute("auxiliar", as.form(auxiliar,user));
        return "auxiliar/formulario";
    }

    @PostMapping("/salvar")
    public ModelAndView save(@ModelAttribute AuxiliarAdministrativo auxiliar, @AuthenticationPrincipal User user, RedirectAttributes redir) {
        UsuarioMapper usuarioMapper = new UsuarioMapper();
        if (auxiliar.getId()==null && auxiliar.getUsuario().getId() == null) {
            Usuario usuario = usuarioMapper.toEntity(usuarioService.buscarPorEmail(user.getUsername()));
            auxiliar.setUsuario(usuario);
        }
        as.create(auxiliar);
         System.out.println("O que temos aqui: "+ auxiliar.getUsuario().getNome());
        redir.addFlashAttribute("sucesso", "Operação realizada com sucesso");
        return new ModelAndView("redirect:/auxiliar/dados");
    }

    @PutMapping("/editar")
    public ModelAndView update(@ModelAttribute AuxiliarAdministrativo auxiliar, RedirectAttributes redir) {
        as.create(auxiliar);
        redir.addFlashAttribute("sucesso", "Operação realizada com sucesso");
        return new ModelAndView("redirect:/auxiliar/dados");
    }

    @GetMapping("/editar/{id}")
    public String preEditar(Model model, @PathVariable("id") Long id, Pageable pageable) {

        model.addAttribute("auxiliar", as.buscarPorUsuarioId(id));
        return "auxiliar/formulario";
    }

    @GetMapping("/delete/{id}")
    public ModelAndView excluir(@PathVariable("id") Long id) {
        Map<String, Object> model = new HashMap<>();
        as.delete(id);
        model.put("sucesso", "Operação realizada com sucesso.");
        return new ModelAndView("auxiliar/auxiliares", model);
    }

    @GetMapping("/datatables/server")
    public ResponseEntity<?> auxiliares(HttpServletRequest request) {       
        return ResponseEntity.ok(as.aux(request));
    }
}
