package br.ind.cmil.gestao.model.services.interfaces;

import jakarta.mail.MessagingException;

/**
 *
 * @author abraao
 */
public interface IEmailService {

    void enviarEmailConfirmacao(String destino, String codigo);

    void emailDeConfirmacaoDeCadastro(String email) throws MessagingException;
}
