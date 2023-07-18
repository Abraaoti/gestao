package br.ind.cmil.gestao.controlles;

import br.ind.cmil.gestao.model.dto.request.RegistrarUsuario;
import br.ind.cmil.gestao.model.dto.response.UsuarioResponse;
import br.ind.cmil.gestao.model.services.interfaces.IUsuarioService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
    public ResponseEntity  registrarUsuario(@RequestBody @Valid RegistrarUsuario usuario, HttpServletRequest request) throws MessagingException{
        
        service.register(usuario, getSiteURL(request));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<UsuarioResponse> buscarPorId(@PathVariable @NotNull @Positive Long id) {
        return ResponseEntity.ok().body(service.buscarPorId(id));
    }

   

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }

    @GetMapping("/confirmacao/cadastro")
    public String verifyUser(@RequestParam("codigo") String codigo) {
        if (service.verify(codigo)) {
            return "verify_success";
        } else {
            return "verify_fail";
        }
    }
    
 

}
