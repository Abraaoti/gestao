
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
    public static final String USUARIOS = "/lista";
    public static final String ADD = "/add";
    public static final String SALVAR = "/salvar";
    public static final String EDITAR = "/editar/{id}";
    public static final String DETALHES = "/detalhes/{id}";
    public static final String CREDENCIAIS = "/editar/credenciais/{id}";
    public static final String EDITAR_PERFIL = "/editar/usuario/{id}/perfis/{perfis}";
    public static final String EDITAR_SENHA = "/editar/senha";
    public static final String CONFIRMASENHA = "/confirmar/senha";
    public static final String NOVO = "/novo";
    public static final String MENSAGEM = "/mensagem";
    public static final String CONFIRMACADASTRO = "/confirmacao";
    public static final String REDEFINIRSENHA = "/p/redefinir/senha";
    public static final String RECUPERARSENHA = "/p/recuperar/senha";
    public static final String  NOVASENHA = "/p/nova/senha";

    public UsuarioUri(EntityLinks entityLinks) {
        this.entityLinks = entityLinks;
    }
    
}
