package br.ind.cmil.gestao.infra.securitys.service;

import br.ind.cmil.gestao.infra.securitys.LoginDTO;
import br.ind.cmil.gestao.infra.securitys.token.model.TokenDTO;

/**
 *
 * @author abraao
 */
public interface AuthenticationService {

   
    TokenDTO authenticate(LoginDTO credencias);
}
