package br.ind.cmil.gestao.model.services.interfaces;

import br.ind.cmil.gestao.model.dto.Credentials;
import br.ind.cmil.gestao.model.dto.response.Response;
import java.io.IOException;

/**
 *
 * @author abraao
 */
public interface IAuthenticationService {

    //Usuario buscarPorEmail(String email);
    //MessageResponse register(RegistrarUsuario request); 
    Response authenticate(Credentials credencias) throws IOException;

   // void register(RegistrarUsuario request, String siteURL) throws MessagingException;

    //void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
