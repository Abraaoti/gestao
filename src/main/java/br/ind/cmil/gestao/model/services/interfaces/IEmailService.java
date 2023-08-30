package br.ind.cmil.gestao.model.services.interfaces;

import br.ind.cmil.gestao.model.entidades.Usuario;
import jakarta.mail.MessagingException;

/**
 *
 * @author abraao
 */
public interface IEmailService {

    void placeOrder(Usuario usuario);

    void enviarEmailConfirmacao(String destino, String codigo);

    void emailDeConfirmacaoDeCadastro(String email) throws MessagingException;
}
