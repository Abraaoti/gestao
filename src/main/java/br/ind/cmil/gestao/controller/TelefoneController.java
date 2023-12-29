package br.ind.cmil.gestao.controller;

import br.ind.cmil.gestao.enums.TipoTelefone;
import br.ind.cmil.gestao.model.dto.TelefoneDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import br.ind.cmil.gestao.services.TelefoneService;
import br.ind.cmil.gestao.util.WebUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 *
 * @author cmilseg
 */
@Controller
@RequestMapping("telefone")
public class TelefoneController {

    private final TelefoneService telService;

    public TelefoneController(TelefoneService telefoneService) {
        this.telService = telefoneService;
    }
 @ModelAttribute
    public void prepareContext(Model model) {
        model.addAttribute("tipos", TipoTelefone.tipoTelefones());
    }
    @GetMapping("/pessoa/{pessoa_id}")
    public String formTelefone(@PathVariable("pessoa_id") Long pessoa_id, @ModelAttribute("telefone") TelefoneDTO telefone, Model model) {
        model.addAttribute("telefone", telService.criar(pessoa_id, telefone));
        return "telefone/telefone";
    }
  

    @GetMapping
    public String list(Pageable pageable) {
        return "telefone/telefones";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute("telefone") TelefoneDTO telefone, RedirectAttributes redir) {
        
        telService.salvar(telefone);
          redir.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("telefone.create.success"));
       return "redirect:/telefone/pessoa/" + telefone.pessoa();
    }
    @PostMapping("/editar")
    public String atualizar(@ModelAttribute("telefone") TelefoneDTO telefone, RedirectAttributes redir) {
     
        telService.salvar(telefone);
          redir.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("telefone.create.success"));
       return "redirect:/telefone/pessoa/" + telefone.pessoa();
    }

   

    @GetMapping("/excluir/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        telService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
     @GetMapping("/editar/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("telefone", telService.buscarTelefonePorId(id));
        return "telefone/telefone";
    }
    
     @GetMapping("/datatables/server")
    public ResponseEntity<?> telefones(HttpServletRequest request) {
        //model.addAttribute("perfis", ps.lista(pageable));
        // return "perfis/perfis";
        return ResponseEntity.ok(telService.buscarTodos(request));
    }

}
