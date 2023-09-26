package br.ind.cmil.gestao.configs;

import br.ind.cmil.gestao.model.services.interfaces.impl.DBService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 *
 * @author ti
 */
@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DBService dbService;

    @Bean
    public void instaciaBaseDePerfis() {
        this.dbService.instanciaBaseDePerfis();

    }

    @Bean
    public void instaciaBaseDeDados() throws MessagingException {
        this.dbService.instanciaBaseDeDados();

    }
}
