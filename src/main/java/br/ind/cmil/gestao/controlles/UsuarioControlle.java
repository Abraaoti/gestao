package br.ind.cmil.gestao.controlles;

import br.ind.cmil.gestao.model.dto.RegistrarUsuario;
import br.ind.cmil.gestao.model.services.interfaces.IUsuarioService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author abraao
 */
@RestController
@RequestMapping("/api/u")
@CrossOrigin(origins = "http://localhost:4200/")
public class UsuarioControlle {

    @Autowired
    private IUsuarioService service;

    @PostMapping("/registrar")
    public ResponseEntity registrarUsuario(@RequestBody @Valid RegistrarUsuario usuario, HttpServletRequest request) throws MessagingException {
       
        service.register(usuario, getSiteURL(request));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok().body(service.buscarPorId(id));
    }

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }

    @GetMapping("/usuarios")
    public Set<RegistrarUsuario> list(Pageable pageable) {
        //List<PessoaDTO> lis = service.usuarios(pageable);
        return service.getUsuarios(pageable);
    }

    @GetMapping("/confirmacao/cadastro")
    public String confirmarCadastro(@RequestParam("codigo") String codigo) {
         service.ativarCadastro(codigo);
       return "redirect:/login";
    }
    

}
