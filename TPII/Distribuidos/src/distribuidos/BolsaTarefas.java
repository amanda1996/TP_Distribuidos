/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distribuidos;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

/**
 *
 * @author amanda
 */
public class BolsaTarefas extends UnicastRemoteObject{
    
    public ArrayList<EstacoesTrabalho> estacoesConectadas = new ArrayList<>();
    public static ArrayList<Tarefa> tarefas = new ArrayList();
    
    protected BolsaTarefas() throws RemoteException{
        super();
    }

    
}
