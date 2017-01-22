/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distribuidos;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;

/**
 *
 * @author amanda
 */
public interface TarefasClientes extends Remote{
    
    public void adicionaDescTarefa(HashMap<String,Object> descricao) throws RemoteException;
    
    public Tarefa removeTarefa(int chave) throws RemoteException;
    
    public Tarefa recuperaTarefa(int chave) throws RemoteException;
    
}
