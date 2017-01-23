/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distribuidos;

import java.rmi.ConnectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author amanda
 */
public class EstacoesTrabalho extends UnicastRemoteObject {

    private static MetodosRemotos metodos;
    private static Registry registry;

    public EstacoesTrabalho() throws RemoteException {
        super();
    }

    public static void main(String[] args) {
        String host = "127.0.0.1";

        try {
            registry = LocateRegistry.getRegistry(host);

            metodos = (MetodosRemotos) registry.lookup("Metodos");

            EsperaTarefas runnable = new EsperaTarefas();
            Thread thread = new Thread(runnable);
            thread.start();

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }

    private static class EsperaTarefas implements Runnable {

        @Override
        public void run() {
            System.out.println("entrou thread");
            try {
                metodos.conectaEstacao();
                while (!metodos.terminouTarefas()) {
                    if (metodos.estacaoConectada()) {
                        System.out.println("tem estacao conectada");
                    }
                    System.out.println("entrou while");
                    Tarefa t = metodos.leTarefa();
                    if (t != null) {
                        System.out.println("tarefa lida: \nchave: " + t.toString());
                        int[][] matriz = (int[][]) t.getDescricao().get("matriz");
                        for (int i = 0; i < matriz[0].length; i++) {
                            System.out.print("[");
                            for (int j = 0; j < matriz[1].length; j++) {
                                System.out.print(matriz[i][j] + " ");
                            }
                            System.out.print("]\n");
                        }
                        Tarefa tarefaConcluida = new Tarefa();
                        if (t != null) {
                            tarefaConcluida = metodos.calculaProduto(t);
                            metodos.atualizaTarefa(tarefaConcluida);
                        }
                    }
                }
            } catch (RemoteException ex) {
                try {
                    System.out.println("desconectando estacao...");
                    metodos.desconectaEstacao();
                } catch (RemoteException ex1) {
                    Logger.getLogger(EstacoesTrabalho.class.getName()).log(Level.SEVERE, null, ex1);
                    System.out.println("Conexão recusada!");
                }
            }
        }

    }

}
