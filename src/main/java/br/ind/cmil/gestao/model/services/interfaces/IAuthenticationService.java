package br.ind.cmil.gestao.model.services.interfaces;

import br.ind.cmil.gestao.model.dto.request.Credentials;
import br.ind.cmil.gestao.model.dto.response.AuthResponse;
import java.io.IOException;

/**
 *
 * @author abraao
 */
public interface IAuthenticationService {

    //Usuario buscarPorEmail(String email);

    //MessageResponse register(RegistrarUsuario request);

    AuthResponse authenticate(Credentials credencias)throws IOException;;

    //void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
