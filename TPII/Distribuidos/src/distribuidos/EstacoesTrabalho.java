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
        String host = (args.length == 0) ? null : args[0];

        try {
            registry = LocateRegistry.getRegistry(host);

            metodos = (MetodosRemotos) registry.lookup("Metodos");

            Runnable runnable = new EsperaTarefas();
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
            try {
                while (metodos.terminouTarefas()) {
                    Tarefa t = metodos.leTarefa();
                    Tarefa tarefaConcluida = new Tarefa();
                    if (t != null) {
                        tarefaConcluida = metodos.calculaProduto(t);
                        metodos.atualizaTarefa(tarefaConcluida);
                    }
                }

            } catch (RemoteException ex) {
                Logger.getLogger(EstacoesTrabalho.class.getName()).log(Level.SEVERE, null, ex);
            }

            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }

}
