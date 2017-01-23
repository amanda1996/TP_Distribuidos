/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distribuidos;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import distribuidos.Tarefa;

/**
 *
 * @author amanda
 */
public interface MetodosRemotos extends Remote{
        
    public void adicionaTarefa(Tarefa tarefa) throws RemoteException;
    
    public Tarefa removeTarefa() throws RemoteException;
    
    public Tarefa leTarefa() throws RemoteException;
    
    public void atualizaTarefa(Tarefa tarefa) throws RemoteException;
    
    public boolean terminouTarefas() throws RemoteException;
    
    public Tarefa calculaProduto(Tarefa tarefa) throws RemoteException;
    
    public boolean estacaoConectada() throws RemoteException;
    
    public void conectaEstacao() throws RemoteException;
    
    public void desconectaEstacao() throws RemoteException;
}
