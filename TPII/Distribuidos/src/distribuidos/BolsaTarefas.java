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
public class BolsaTarefas extends UnicastRemoteObject implements MeotodosRemotos{
    
    public ArrayList<EstacoesTrabalho> estacoesConectadas = new ArrayList<>();
    public ArrayList<Tarefa> tarefas = new ArrayList();
    
    protected BolsaTarefas() throws RemoteException{
        super();
    }

    @Override
    public void adicionaTarefa(int chave, HashMap<String, Object> descricao) throws RemoteException {
        Tarefa tarefa = new Tarefa(chave,descricao);
        tarefas.add(tarefa);
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HashMap<String, Object> removeTarefa(int chave) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HashMap<String, Object> leTarefa(int chave) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private int encontraTarefa(int chave,ArrayList<Tarefa> ts){
        int esq,dir,meio;
        esq = 0;
        dir = ts.size() -1;
        while(esq <= dir){
            meio = (esq + dir)/2;
            if(ts.get(meio).getChave() == chave){
                return meio;
            }else if(ts.get(meio).getChave() < chave){
                esq = meio + 1;
            }else{
                dir = meio - 1;
            }
        }
        return -1;
    }

    
}
