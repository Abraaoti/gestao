
package br.ind.cmil.gestao.email.service;

import br.ind.cmil.gestao.usuario.domain.Usuario;
import jakarta.mail.MessagingException;

/**
 *
 * @author ti
 */
public interface EmailService {

    void placeOrder(Usuario usuario);

    void enviarEmailConfirmacao(String destino, String codigo);

    void emailDeConfirmacaoDeCadastro(String email) throws MessagingException;
}