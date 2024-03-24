package br.ind.cmil.gestao.pessoa.controlle;

import br.ind.cmil.gestao.pessoa.domain.PessoaJuridica;
import br.ind.cmil.gestao.pessoa.service.PessoaJuridicaService;
import br.ind.cmil.gestao.util.WebUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author abraaocalelessocassi
 */
@Controller
@RequestMapping("pessoa_juridica")
public class PessoaJuridicaControle {

    private final PessoaJuridicaService pessoaJuridicaService;

    public PessoaJuridicaControle(PessoaJuridicaService pessoaJuridicaService) {
        this.pessoaJuridicaService = pessoaJuridicaService;
    }

    @GetMapping
    public String list() {
        return "empresas/empresas";
    }

    @GetMapping("/add")
    public String formPessoaJuridica(@ModelAttribute("empresa") PessoaJuridica empresa, Model model) {

        return "empresas/add";
    }

    @PostMapping("/save")
    public String salvar(@ModelAttribute("empresa") PessoaJuridica empresa, RedirectAttributes redir) {

        pessoaJuridicaService.save(empresa);

        redir.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("telefone.create.success"));
        return "redirect:/pessoa_juridica/add";
    }

    @PostMapping("/update")
    public String atualizar(@ModelAttribute("empresa") PessoaJuridica empresa, RedirectAttributes redir) {

        pessoaJuridicaService.save(empresa);

        redir.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("telefone.create.success"));
        return "redirect:/pessoa_juridica/add";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") final Long id,
            final RedirectAttributes redirectAttributes) {
        pessoaJuridicaService.delete(id);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("telefone.delete.success"));
        return "redirect:/pessoa_juridica";
    }

    @GetMapping("/editar/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("empresa", pessoaJuridicaService.buscarEmpresaPorId(id));
        return "empresas/add";
    }

 
     @GetMapping("/datatables/server")
    public ResponseEntity<?> pesssoasJuridicas(HttpServletRequest request) {
        return ResponseEntity.ok(pessoaJuridicaService.buscarTodos(request));
    }

}
