package br.ind.cmil.gestao.web.controlles;

import br.ind.cmil.gestao.model.dto.Credentials;
import br.ind.cmil.gestao.model.dto.response.Response;
import br.ind.cmil.gestao.model.services.interfaces.impl.AuthenticationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author abraao
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
@Controller
public class AuthController {

    private final AuthenticationServiceImpl service;

    @PostMapping("/authenticate")
    public ResponseEntity<Response> authenticate(@ModelAttribute Credentials request) {
        return new ResponseEntity<>(service.authenticate(request), HttpStatus.OK);
    }

}
