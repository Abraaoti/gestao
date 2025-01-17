package br.ind.cmil.gestao.funcionario.controlle;

import br.ind.cmil.gestao.cargo.service.CargoService;
import br.ind.cmil.gestao.departamento.service.DepartamentoService;
import br.ind.cmil.gestao.enums.EstadoCivil;
import br.ind.cmil.gestao.enums.Genero;
import br.ind.cmil.gestao.funcionario.model.CriarFuncionarioDTO;
import br.ind.cmil.gestao.funcionario.services.FuncionarioService;
import br.ind.cmil.gestao.util.WebUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import br.ind.cmil.gestao.pessoa.service.PessoaJuridicaService;

/**
 *
 * @author ti
 */
@RequiredArgsConstructor
@Controller
@RequestMapping("/funcionarios")
public class FuncionarioControlle {

    private final FuncionarioService funcionarioService;
    private final DepartamentoService departamentoService;
    private final CargoService cargoService;
    private final PessoaJuridicaService empresaService;

    @GetMapping
    public String lista() {
        return "funcionario/funcionarios";

    }

    @ModelAttribute
    public void prepareContext(Model model) {
        model.addAttribute("departamentos", departamentoService.lista());
        model.addAttribute("cargos", cargoService.lista());
        model.addAttribute("empresas", empresaService.getEmpresas());
        model.addAttribute("estadoCivil", EstadoCivil.getEstadoCivil());
        model.addAttribute("generos", Genero.generos());
    }

    @GetMapping("/add")
    public String form(@ModelAttribute("funcionario") CriarFuncionarioDTO funcionario) {

        return "funcionario/funcionario";
    }

    @PostMapping("/salvar")
    public String save(@ModelAttribute("funcionario") CriarFuncionarioDTO funcionario, RedirectAttributes redirectAttributes) {
        funcionarioService.save(funcionario);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("funcionario.create.success"));
        return "redirect:/funcionarios";
    }

    @PostMapping("/editar")
    public String update(@ModelAttribute CriarFuncionarioDTO funcionario, RedirectAttributes redirectAttributes) {
        funcionarioService.save(funcionario);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("funcionario.update.success"));
        return "redirect:/funcionarios";
    }

    @GetMapping("/info/{id}")
    public String detalhes(@PathVariable("id") Long id, Model model) {
        model.addAttribute("pessoa", funcionarioService.buscarFuncionarioPorId(id));
        return "funcionarios/detalhepessoa";
    }

    @GetMapping("/editar/{id}")
    public String editar(Model model, @PathVariable("id") Long id) {
        CriarFuncionarioDTO funcionario = funcionarioService.buscarFuncionarioPorId(id);
        model.addAttribute("funcionario", funcionario);

        return "funcionario/funcionario";
    }

    @GetMapping("/delete/{id}")
    public String excluir(@PathVariable("id") Long id, Model model) {
        funcionarioService.delete(id);
        model.addAttribute("sucesso", "Operação realizada com sucesso.");

        return "funcionario/funcionarios";
    }

    @GetMapping("/datatables/server")
    public ResponseEntity<?> funcionarios(HttpServletRequest request) {
        return ResponseEntity.ok(funcionarioService.buscarTodos(request));
    }

}
