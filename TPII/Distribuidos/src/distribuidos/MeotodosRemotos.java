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
public interface MeotodosRemotos extends Remote{
    
    public void adicionaTarefa(int chave, HashMap<String,Object> descricao) throws RemoteException;
    
    public HashMap<String,Object> removeTarefa(int chave) throws RemoteException;
    
    public HashMap<String,Object> leTarefa(int chave) throws RemoteException;
    
    public HashMap<String, Object> calculaProduto(HashMap<String, Object>) throws RemoteException;
}
