package br.ind.cmil.gestao;

import br.ind.cmil.gestao.model.dto.PerfilDTO;
import br.ind.cmil.gestao.model.services.interfaces.IPerfilService;
import java.util.stream.Stream;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GestaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestaoApplication.class, args);
    }
/**
    @Bean
    CommandLineRunner init(IPerfilService sercice) {

        return args -> {
            Stream.of("admin", "administrativo", "comprador", "diretor", "engenheiro", "financeiro", "funcionário", "rh", "técnico", "usuário").forEach(perfil -> {
                PerfilDTO user = new PerfilDTO(null, perfil);
                sercice.create(user);
            });
            //sercice.list();
        };
    }**/
}
