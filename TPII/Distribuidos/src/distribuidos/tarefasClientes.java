/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distribuidos;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author amanda
 */
public interface tarefasClientes extends Remote{
    
    public void adicionaTarefa(Tarefa tarefa);
    
}
