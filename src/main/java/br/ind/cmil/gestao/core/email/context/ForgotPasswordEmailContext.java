
package br.ind.cmil.gestao.core.email.context;

import br.ind.cmil.gestao.model.entidades.Usuario;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author abraao
 */
public class ForgotPasswordEmailContext   extends AbstractEmailContext {
    private String token;


    @Override
    public <T> void init(T context){
        //we can do any common configuration setup here
        // like setting up some base URL and context
        Usuario customer = (Usuario) context; // we pass the customer informati
        put("firstName", customer.getNome());
        setTemplateLocation("emails/forgot-password");
        setSubject("Forgotten Password");
        setFrom("no-reply@timuila.com");
        setTo(customer.getEmail());
    }

    public void setToken(String token) {
        this.token = token;
        put("token", token);
    }

    public void buildVerificationUrl(final String baseURL, final String token){
        final String url= UriComponentsBuilder.fromHttpUrl(baseURL)
                .path("/password/change").queryParam("token", token).toUriString();
        put("verificationURL", url);
    }
}
