package br.ind.cmil.gestao.infra.securitys.service.impl;

import br.ind.cmil.gestao.infra.securitys.LoginDTO;
import br.ind.cmil.gestao.exceptions.DisabledUserException;
import br.ind.cmil.gestao.exceptions.InvalidUserCredentialsException;
import br.ind.cmil.gestao.infra.securitys.token.service.imp.TokenServiceImpl;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.ind.cmil.gestao.infra.securitys.service.AuthenticationService;
import br.ind.cmil.gestao.infra.securitys.token.model.TokenDTO;

/**
 *
 * @author abraao
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

  
    private final TokenServiceImpl jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationServiceImpl(TokenServiceImpl jwtService, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    @Transactional(readOnly = true)
    public TokenDTO authenticate(LoginDTO request) {
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.login(), request.password()));

        } catch (DisabledException e) {
            throw new DisabledUserException("User Inactive");
        } catch (BadCredentialsException e) {
            throw new InvalidUserCredentialsException("Invalid Credentials");
        }

        String token = jwtService.generateToken(authentication);
        UserDetails user = (UserDetails) authentication.getPrincipal();
        Set<String> roles = user.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toSet());
     

        return new TokenDTO(token);

    }

}
