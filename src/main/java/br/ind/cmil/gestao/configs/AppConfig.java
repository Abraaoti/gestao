package br.ind.cmil.gestao.configs;

import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *
 * @author abraao
 */
@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "app")
public class AppConfig {
    @NotNull
    private String appUrl;

    public String getAppUrl() {
        return appUrl;
    }
}
