
package br.ind.cmil.gestao.core.email.context;

import br.ind.cmil.gestao.model.entidades.Usuario;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author abraao
 */
public class AccountVerificationEmailContext extends AbstractEmailContext {
     private String token;


    @Override
    public <T> void init(T context){
        
        
        
        
        //we can do any common configuration setup here
        // like setting up some base URL and context
        Usuario customer = (Usuario) context; // we pass the customer informati
        put("nome", customer.getNome());
        //put("titulo", "Bem vindo a Plataforma CMIL");
        //put("texto", "Precisamos que confirme seu cadastro, clicando no link abaixo");
        //put("linkConfirmacao", "https://app-cmil.herokuapp.com/u/confirmacao/cadastro?codigo=" + codigo);

        setTemplateLocation("emails/email-verification");
        setSubject("Confirma o cadastro por gentileza");
        setFrom("no-reply@timuila.com");
        setTo(customer.getEmail());
    }

    public void setToken(String token) {
        this.token = token;
        put("token", token);
    }

    public void buildVerificationUrl(final String baseURL, final String token){
        final String url= UriComponentsBuilder.fromHttpUrl(baseURL)
                .path("/register/verify").queryParam("token", token).toUriString();
        put("verificationURL", url);
    }
}
