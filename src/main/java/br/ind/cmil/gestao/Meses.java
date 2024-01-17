package br.ind.cmil.gestao;

import java.util.Scanner;

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
      Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		
		while (N != 0) {
			
			int[][] mat = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					mat[i][j] = 1 + Math.abs(i - j);
				}
			}
			
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < N; i++) {
				sb.append(String.format("%3d", mat[i][0]));
				for (int j = 1; j < N; j++) {
					sb.append(String.format(" %3d", mat[i][j]));
				}
				sb.append(System.lineSeparator());
			}
			System.out.println(sb);
			
			N = sc.nextInt();
		}		
    }

}
