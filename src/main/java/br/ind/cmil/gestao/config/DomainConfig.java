package br.ind.cmil.gestao.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("br.ind.cmil.gestao")
@EnableJpaRepositories("br.ind.cmil.gestao")
@EnableTransactionManagement
public class DomainConfig {
}
