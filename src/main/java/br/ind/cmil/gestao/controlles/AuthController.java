package br.ind.cmil.gestao.controlles;

import br.ind.cmil.gestao.model.dto.request.Credentials;
import br.ind.cmil.gestao.model.services.interfaces.IAuthenticationService;
import br.ind.cmil.gestao.model.services.interfaces.impl.AuthenticationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author abraao
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationServiceImpl service;

    

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody Credentials request) {
        return ResponseEntity.ok(service.authenticate(request));
    }

}
