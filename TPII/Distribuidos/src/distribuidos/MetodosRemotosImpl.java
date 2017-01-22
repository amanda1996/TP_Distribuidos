/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distribuidos;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author amanda
 */
public class MetodosRemotosImpl implements MeotodosRemotos {

    @Override
    public void adicionaTarefa(int chave, HashMap<String, Object> descricao) throws RemoteException {
        Tarefa tarefa = new Tarefa(chave, descricao);
        BolsaTarefas.tarefas.add(tarefa);
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HashMap<String, Object> removeTarefa(int chave) throws RemoteException {
        int indice = encontraTarefa(chave);
        Tarefa tarefa = BolsaTarefas.tarefas.get(indice);
        BolsaTarefas.tarefas.remove(indice);
        return tarefa.getDescricao();
    }

    @Override
    public HashMap<String, Object> leTarefa(int chave) throws RemoteException {
        int indice = encontraTarefa(chave);
        Tarefa tarefa = BolsaTarefas.tarefas.get(indice);
        return tarefa.getDescricao();
    }

    private int encontraTarefa(int chave) {
        int esq, dir, meio;
        esq = 0;
        dir = BolsaTarefas.tarefas.size() - 1;
        while (esq <= dir) {
            meio = (esq + dir) / 2;
            if (BolsaTarefas.tarefas.get(meio).getChave() == chave) {
                return meio;
            } else if (BolsaTarefas.tarefas.get(meio).getChave() < chave) {
                esq = meio + 1;
            } else {
                dir = meio - 1;
            }
        }
        return -1;
    }

    @Override
    public Tarefa calculaProduto(Tarefa tarefa) throws RemoteException {
        int[][] matriz = (int[][]) tarefa.getDescricao().get("matriz");
        ArrayList<ArrayList<Integer>> linhas = (ArrayList<ArrayList<Integer>>) tarefa.getDescricao().get("linhas");
        Tarefa tarefaRetorno = new Tarefa();
        ArrayList<ArrayList<Integer>> linhasRet = new ArrayList();

        for (int i = 0; i < linhas.size(); i++) {
            ArrayList<Integer> linha = linhas.get(i);
            int somatorio = 0;
            ArrayList<Integer> linhaRet = new ArrayList<>();
            for (int j = 0; j < matriz[1].length; j++) {
                for (int k = 0; k < linha.size(); k++) {
                    somatorio += linha.get(k) * matriz[k][j];
                }
            linhaRet.add(somatorio);
            }
            linhasRet.add(linhaRet);
        }
        HashMap<String,Object> hRetorno = new HashMap<>();
        hRetorno.put("resultado", linhasRet);
        tarefaRetorno.setChave(tarefa.getChave());
        tarefaRetorno.setDescricao(hRetorno);
        
        return tarefaRetorno;
    }

}
