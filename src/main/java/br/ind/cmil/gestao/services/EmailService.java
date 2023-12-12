package br.ind.cmil.gestao.services;

import br.ind.cmil.gestao.domain.Usuario;
import jakarta.mail.MessagingException;

/**
 *
 * @author abraao
 */
public interface EmailService {

    void placeOrder(Usuario usuario);

    void enviarEmailConfirmacao(String destino, String codigo);

    void emailDeConfirmacaoDeCadastro(String email) throws MessagingException;
}
