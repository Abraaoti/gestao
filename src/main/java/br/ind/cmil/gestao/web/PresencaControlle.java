package br.ind.cmil.gestao.web;

import br.ind.cmil.gestao.model.dto.PresencaDTO;
import br.ind.cmil.gestao.model.enums.TipoPresenca;
import br.ind.cmil.gestao.model.services.interfaces.IAuxiliarAdministrativoService;
import br.ind.cmil.gestao.model.services.interfaces.IFuncionarioService;
import br.ind.cmil.gestao.model.services.interfaces.IPresencaService;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author abraao
 */
@RequiredArgsConstructor
@Controller
@RequestMapping("/presenca")
public class PresencaControlle {

    private final IPresencaService ps;
    private final IAuxiliarAdministrativoService as;
    private final IFuncionarioService fs;

    @GetMapping("/add")
    public String form(Model model, PresencaDTO presenca, Pageable pageable) {
        model.addAttribute("presenca", presenca);
        model.addAttribute("status", getStatus());
        model.addAttribute("auxiliar", as.list(pageable));
        model.addAttribute("funcionario", fs.list(pageable));
        return "/presenca/presenca";
    }

    private Set<String> getStatus() {
        Set<String> generos = new HashSet<>();
        for (TipoPresenca value : TipoPresenca.values()) {
            TipoPresenca s = value;
            // fm.convertGeneroValue(g.getValue());
            generos.add(s.getValue().toLowerCase());
        }
        return generos;
    }
    // @PreAuthorize("hasAnyAuthority('PACIENTE', 'MEDICO')")

    @GetMapping("/horario/auxiliar/{id}/data/{data}")
    public ResponseEntity<?> getHorarios(@PathVariable("id") Long id, @PathVariable("data") @DateTimeFormat(iso = ISO.DATE) LocalDate data) {

        return ResponseEntity.ok(ps.buscarHorarios(id, data));
    }

    @PostMapping("/salvar")
    public ModelAndView save(@ModelAttribute PresencaDTO presenca, RedirectAttributes redir) {
        ps.create(presenca);
        redir.addFlashAttribute("sucesso", "Operação realizada com sucesso");
        return new ModelAndView("redirect:/presenca/add", "presenca", presenca);
    }

    @GetMapping("/lista")
    public String lista() {

        return "/presenca/presencas";

    }

    @PostMapping("/editar")
    public ModelAndView editarPerfil(@ModelAttribute PresencaDTO presenca, RedirectAttributes redir) {
        ps.create(presenca);
        redir.addFlashAttribute("sucesso", "Operação realizada com sucesso");
        return new ModelAndView("/presenca/presenca");
    }

    @GetMapping("/editar/{id}")
    public String preEditar(Model model, @PathVariable("id") Long id, Pageable pageable) {

        model.addAttribute("presenca", ps.findById(id));
        return "/presenca/presenca";
    }

    @GetMapping("/excluir/{id}")
    public ModelAndView excluir(@PathVariable("id") Long id) {
        Map<String, Object> model = new HashMap<>();
        ps.delete(id);
        model.put("sucesso", "Operação realizada com sucesso.");
        return new ModelAndView("/presenca/presencas", model);
    }

    @GetMapping("/datatables/server")
    public ResponseEntity<?> presencas(HttpServletRequest request) {
        //model.addAttribute("perfis", ps.list(pageable));
        // return "perfis/perfis";
        return ResponseEntity.ok(ps.presencas(request));
    }

}
