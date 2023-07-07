package br.ind.cmil.gestao.core.email.services.interfaces;

import br.ind.cmil.gestao.model.entidades.Usuario;
import jakarta.mail.MessagingException;

/**
 *
 * @author abraao
 */
public interface IEmailService {

    void confirmarCadastro(Usuario user, String siteUR) throws MessagingException;

    //void linkSite(String siteURL);

    void redefinirSenha(String destino, String verificador) throws MessagingException;

    //void sendMail(final AbstractEmailContext email) throws MessagingException;
}
