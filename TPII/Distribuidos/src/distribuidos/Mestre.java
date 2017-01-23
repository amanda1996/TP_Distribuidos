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
import java.util.Collections;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private static ArrayList<Tarefa> tarefasFinalizadas = new ArrayList<>();
    private static ArrayList<ArrayList<Integer>> linhas = new ArrayList<>();

    public Mestre(int[][] matriz1, int[][] matriz2, int qtd) throws RemoteException {
        super();
        this.matriz1 = matriz1;
        this.matriz2 = matriz2;
        this.qtd = qtd;
    }

    public static void main(String[] args) {
        String host = (args.length == 0) ? null : args[0];
        try {
            registry = LocateRegistry.getRegistry(host);

            metodos = (MetodosRemotos) registry.lookup("Metodos");

            int qtdLinhasPorTarefa = matriz1[0].length / qtd;

            int resto = matriz1[0].length % qtd;

            int aux = 0;
            for (int a = 0; a < qtd; a++) {
                linhas = new ArrayList<>();
                while (aux < qtdLinhasPorTarefa) {
                    ArrayList<Integer> linha = new ArrayList<>();
                    for (int j = 0; j < matriz1[1].length; j++) {
                        linha.add(matriz1[aux][j]);
                    }
                    linhas.add(linha);
                    aux++;
                }
                //aux tem que ser incrementado pq senao chega uma hora que a qtdLinhasPorTarefa vai ser sempre menor
                qtdLinhasPorTarefa += aux;
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

            ConsultaTarefas consulta = new ConsultaTarefas();
            Thread thread = new Thread(consulta);
            thread.start();

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();

        }

    }

    private static class ConsultaTarefas implements Runnable {

        @Override
        public void run() {
            try {
                System.out.println("entrou thread");
                while (!metodos.estacaoConectada()) {
                    Thread.sleep(5000);
                    System.out.println("verificando...");
                    if (metodos.estacaoConectada()) {
                        while (!metodos.terminouTarefas()) {
                            System.out.println("entrou while mestre");
                            Tarefa tarefaFinalizada = metodos.removeTarefa();
                            tarefasFinalizadas.add(tarefaFinalizada);
                        }
                        Collections.sort(tarefasFinalizadas);
                        for (int i = 0; i < tarefasFinalizadas.size(); i++) {
                            ArrayList<ArrayList<Integer>> linhasTarefa = (ArrayList<ArrayList<Integer>>) tarefasFinalizadas.get(i).getDescricao().get("linhas");
                            for (int j = 0; j < linhasTarefa.size(); j++) {
                                System.out.print("[");
                                ArrayList<Integer> linhaT = linhasTarefa.get(j);
                                for (int k = 0; k < linhaT.size(); k++) {
                                    System.out.print(linhaT.get(k) + " ");
                                }
                                System.out.print("]\n");
                            }
                        }
                    }
                }
            } catch (RemoteException ex) {
                Logger.getLogger(Mestre.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(Mestre.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
