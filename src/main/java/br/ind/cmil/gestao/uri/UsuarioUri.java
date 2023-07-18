
package br.ind.cmil.gestao.uri;

import org.springframework.hateoas.server.EntityLinks;
import org.springframework.stereotype.Component;

/**
 *
 * @author ti
 */
@Component
public class UsuarioUri {
    private final EntityLinks entityLinks;
    public static final String USUARIOS = "/u/lista";
    public static final String ADD = "/u/add";
    public static final String SALVAR = "/u/salvar";
    public static final String EDITAR = "/u/editar/{id}";
    public static final String DETALHES = "/u/detalhes/{id}";
    public static final String CREDENCIAIS = "/u/editar/credenciais/{id}";
    public static final String EDITAR_PERFIL = "/u/editar/usuario/{id}/perfis/{perfis}";
    public static final String EDITAR_SENHA = "/u/editar/senha";
    public static final String CONFIRMASENHA = "/u/confirmar/senha";
    public static final String NOVO = "/u/novo";
    public static final String MENSAGEM = "/u/mensagem";
    public static final String CONFIRMACADASTRO = "/u/confirmacao";
    public static final String REDEFINIRSENHA = "/u/p/redefinir/senha";
    public static final String RECUPERARSENHA = "/u/p/recuperar/senha";
    public static final String  NOVASENHA = "/u/p/nova/senha";

    public UsuarioUri(EntityLinks entityLinks) {
        this.entityLinks = entityLinks;
    }
    
}
