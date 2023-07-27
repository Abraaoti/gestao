package br.ind.cmil.gestao.model.services.interfaces.impl;

import br.ind.cmil.gestao.model.dto.request.Credentials;
import br.ind.cmil.gestao.model.dto.response.AuthResponse;
import br.ind.cmil.gestao.model.services.interfaces.IAuthenticationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author abraao
 */
@Service
public class AuthenticationServiceImpl implements IAuthenticationService {

    private final JwtServiceImpl jwtService;
    private final AuthenticationManager authenticationManager;

    //@Autowired
    public AuthenticationServiceImpl(JwtServiceImpl jwtService, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    @Transactional(readOnly = true)
    public AuthResponse authenticate(Credentials request) {

        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));

            String token = jwtService.generateToken((UserDetails) authentication.getPrincipal());

            return new AuthResponse(token);
        } catch (AuthenticationException e) {
            return new AuthResponse("Credenciais incorretas! " + e.getMessage());
        }

    }

}
