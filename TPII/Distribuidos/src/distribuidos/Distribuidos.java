/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distribuidos;

import java.rmi.RemoteException;
import java.util.Random;

/**
 *
 * @author amanda
 */
public class Distribuidos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws RemoteException {
        // TODO code application logic here
        int[][] matriz1 = preencheuAutomatico();
        System.out.println("\n");
        int[][] matriz2 = preencheuAutomatico();

        int qtdSubTarefas = 2;

        Mestre mestre = new Mestre(matriz1, matriz2, qtdSubTarefas);

    }

    private static int[][] preencheuAutomatico() {
        int[][] matriz = new int[6][6];
        Random rnd = new Random();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                matriz[i][j] = rnd.nextInt(10);
            }
        }
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print(" [" + matriz[i][j] + "]");
            }
            System.out.println("\n");
        }
        return matriz;
    }

}
