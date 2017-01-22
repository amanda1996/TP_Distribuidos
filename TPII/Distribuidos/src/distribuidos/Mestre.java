/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distribuidos;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author amanda
 */
public class Mestre extends UnicastRemoteObject {

    private static int[][] matriz1, matriz2;
    private static int qtd;

    private static Registry registry;
    private static MetodosRemotos metodos;

    ArrayList<Tarefa> tarefas = new ArrayList<>();
    private static ArrayList<ArrayList<Integer>> linhas = new ArrayList<>();

    public Mestre(int[][] matriz1, int[][] matriz2, int qtd) throws RemoteException {
        super();
        this.matriz1 = matriz1;
        this.matriz2 = matriz2;
        this.qtd = qtd;
    }

    public static void main(String[] args) {
        System.out.println("tamanho: " + args.length);
        String host = (args.length == 0) ? null : args[0];
        try {
            registry = LocateRegistry.getRegistry(host);

            metodos = (MetodosRemotos) registry.lookup("Metodos");

            int qtdLinhasPorTarefa = matriz1[0].length / qtd;

            int resto = matriz1[0].length % qtd;

            int aux = 0;
            for (int a = 0; a < qtd; a++) {
                while (aux < qtdLinhasPorTarefa) {
                    System.out.println("Linha " + aux + ": ");
                    ArrayList<Integer> linha = new ArrayList<>();
                    for (int j = 0; j < matriz1[1].length; j++) {
                        System.out.println(matriz1[aux][j] + " ");
                        linha.add(j,matriz1[aux][j]);
                    }
                    linhas.add(linha);
                    aux++;
                }
                HashMap<String, Object> descricao = new HashMap<>();
                descricao.put("linhas", linhas);
                descricao.put("matriz", matriz2);
                Tarefa tarefa = new Tarefa(a, 0, descricao);
                metodos.adicionaTarefa(tarefa);
                if (resto != 0) {
                    if (a + 1 == qtd) {
                        qtdLinhasPorTarefa = resto;
                    }
                }
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();

        }

    }
}
