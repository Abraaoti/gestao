package br.ind.cmil.gestao.appi;

import br.ind.cmil.gestao.infra.securitys.LoginDTO;
import br.ind.cmil.gestao.infra.securitys.service.impl.AuthenticationServiceImpl;
import br.ind.cmil.gestao.infra.securitys.token.model.TokenDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author abraao
 */

@RequiredArgsConstructor
@RestController
public class AuthController {

    private final AuthenticationServiceImpl service;

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> authenticate(@RequestBody LoginDTO request) {
        return new ResponseEntity<>(service.authenticate(request), HttpStatus.OK);
    }

}
