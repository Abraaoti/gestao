package br.ind.cmil.gestao;

import br.ind.cmil.gestao.model.dto.mappers.FuncionarioMapper;
import br.ind.cmil.gestao.model.entidades.Funcionario;
import br.ind.cmil.gestao.model.repositorys.IFuncionarioRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GestaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestaoApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(IFuncionarioRepository fr, FuncionarioMapper fm) {
        return args -> {
            fr.deleteAll();

            Funcionario f = new Funcionario();
            f.setNome("abraao calelesso ");
            f.setSobrenome("cassinda");
            f.setNascimento(new Date());
            f.setCpf("01250284902");
            f.setRg("9978787");
            f.setMae("adriano chipondia");
            f.setPai("agostinho cassinda");
            f.setPassaporte("n4878878");

            f.setGenero(fm.convertGeneroValue("masculino"));
            f.setEstado_civil(fm.convertECValue("solteiro(a)"));
            f.setNaturalidade("lubango-huíla/Angola");
            f.setAdmissao(LocalDate.now());
            f.setMatricula("2023001");
            f.setDemissao(LocalDate.now());
            f.setSalario(BigDecimal.valueOf(5000));

            fr.save(f);
        };
    }

}
