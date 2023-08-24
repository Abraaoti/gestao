package br.ind.cmil.gestao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GestaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestaoApplication.class, args);
    }
/*
    @Bean
    CommandLineRunner init(IPerfilService sercice) {

        return args -> {
            Stream.of("admin", "administrativo", "comprador", "diretor", "engenheiro", "financeiro", "funcionário", "rh", "técnico", "usuário").forEach(perfil -> {
                PerfilDTO user = new PerfilDTO(null, perfil);
                sercice.create(user);
            });
            //sercice.list();
        };
    }*/
}
