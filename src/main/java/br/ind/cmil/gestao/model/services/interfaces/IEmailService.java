package br.ind.cmil.gestao.model.services.interfaces;

/**
 *
 * @author abraao
 */
public interface IEmailService {

    void enviarEmailConfirmacao(String destino, String codigo);
}
