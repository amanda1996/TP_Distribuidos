/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distribuidos;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

/**
 *
 * @author amanda
 */
public class BolsaTarefas  {

    public static int cont = 0;
    public static ArrayList<Tarefa> tarefas = new ArrayList();
    public static boolean estacaoConectada;

    protected BolsaTarefas() throws RemoteException {
        super();
    }

    public static void main(String[] args) {
        MetodosRemotosImpl metodos = new MetodosRemotosImpl();

        try {
            MetodosRemotos stub = (MetodosRemotos) UnicastRemoteObject.exportObject(metodos, 0);

            Registry registry = LocateRegistry.getRegistry();
            registry.bind("Metodos", stub);

            System.err.println("Servidor Metodos Rodando");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }

    }
    
    

//    public void transportaTarefas(ArrayList<Tarefa> updateTarefas) {
//        MetodosRemotosImpl metodos = new MetodosRemotosImpl();
//
//        tarefas = metodos.transporteArray(tarefas,updateTarefas);
//
//    }

}
