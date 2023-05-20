package br.ind.cmil.gestao.configs;

import jakarta.validation.constraints.NotNull;

/**
 *
 * @author abraao
 */
//@Component
//@EnableConfigurationProperties
//@ConfigurationProperties(prefix = "app")
public class AppConfig {
    @NotNull
    private String appUrl;

    public String getAppUrl() {
        return appUrl;
    }
}
