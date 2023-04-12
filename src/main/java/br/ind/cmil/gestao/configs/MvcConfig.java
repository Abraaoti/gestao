package br.ind.cmil.gestao.configs;

import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author cmilseg
 */
//@Configuration
public class MvcConfig {//implements WebMvcConfigurer {
/**
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        exposeDirectory("user-photos", registry);
    }

    private void exposeDirectory(String dirName, ResourceHandlerRegistry registry) {
        Path uploadDir = Paths.get(dirName);
        String uploadPath = uploadDir.toFile().getAbsolutePath();

        if (dirName.startsWith("../")) {
            dirName = dirName.replace("../", "");
        }

        registry.addResourceHandler("/" + dirName + "/**").addResourceLocations("file:/" + uploadPath + "/");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/*").allowedOrigins("*").allowedMethods("GET", "POST", "OPTIONS", "PUT")
                .allowedHeaders("Content-Type", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
                        "Access-Control-Request-Headers")
                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials")
                .allowCredentials(true).maxAge(3600);
       // WebMvcConfigurer.super.addCorsMappings(registry);
    }

    /**
     * final Environment environment;
     *
     * public MvcConfig(Environment environment) { this.environment =
     * environment; }
     *
     *
     * @Override public void addResourceHandlers(ResourceHandlerRegistry
     * registry) { String location =
     * environment.getProperty("app.file.upload-dir");
     * registry.addResourceHandler("/static/docs/**")
     * .addResourceLocations(location);
     * exposeDirectory("classpath:/static/docs/", registry); }
     *
     * private void exposeDirectory(String dirName, ResourceHandlerRegistry
     * registry) { java.nio.file.Path uploadDir = Paths.get(dirName); String
     * uploadPath = uploadDir.toFile().getAbsolutePath();
     *
     * if (dirName.startsWith("../")) { dirName = dirName.replace("../", ""); }
     *
     * registry.addResourceHandler("/" + dirName +
     * "/**").addResourceLocations("file:/" + uploadPath + "/"); }
     *
     * @Override public void addViewControllers(ViewControllerRegistry registry)
     * { registry.addViewController("/login").setViewName("login"); //
     * registry.addViewController("/index").setViewName("index");
     * registry.addViewController("/").setViewName("index"); }
     *
     * @Override public void addResourceHandlers(ResourceHandlerRegistry
     * registry) { exposeDirectory("user-photos", registry); }
     *
     * private void exposeDirectory(String dirName, ResourceHandlerRegistry
     * registry) { Path uploadDir = Paths.get(dirName); String uploadPath =
     * uploadDir.toFile().getAbsolutePath();
     *
     * if (dirName.startsWith("../")) dirName = dirName.replace("../", "");
     *
     * registry.addResourceHandler("/" + dirName +
     * "/**").addResourceLocations("file:/"+ uploadPath + "/");
    }
     */
}
