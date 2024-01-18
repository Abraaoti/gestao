package br.ind.cmil.gestao;

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
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String[] seasons = {"Winter", "Spring", "Summer", "Autumn"};
        for (int i = 0; i < 4; i++) {
            System.out.println(seasons[i]);
        }
        System.out.println(new BCryptPasswordEncoder().encode("123"));
    }

}
