package br.ind.cmil.gestao;

import br.ind.cmil.gestao.funcionario.domain.Funcionario;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author ti
 */
public class Meses {

    static String[] meses = {"janeiro", "fevereiro", "março", "abril", "maio", "junho", "julho", "agosto", "setembro", "outubro", "novembro", "dezembro"};
    static int[] diasmes = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};

    /**
     * @param args the command line arguments @RequestParam(value = "name", defaultValue = "World")
     */
    public static void main(String[] args) {
        String[] seasons = {"Winter", "Spring", "Summer", "Autumn"};
        for (int i = 0; i < 4; i++) {
            System.out.println(seasons[i]);
        }
        System.out.println(new BCryptPasswordEncoder().encode("123"));
        
        String[] funcionarios = {"Abraão", "João", "Maria", "Pedro"};
        
        do {            
            LocalDate dataAtual = LocalDate.now();
            
            for (String funcionario : funcionarios) {
                System.out.println(funcionario + " " + dataAtual);
            }
        } while (0>funcionarios.length);
    }

}
