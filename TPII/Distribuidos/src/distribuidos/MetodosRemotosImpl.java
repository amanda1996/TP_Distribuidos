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
public class MetodosRemotosImpl implements MetodosRemotos {
//    ArrayList<Tarefa> arrayTarefasLocais = new ArrayList<>();
    
//    public ArrayList<Tarefa> transporteArray(ArrayList<Tarefa> tarefas, ArrayList<Tarefa> updatetarefas){
//        tarefas = updatetarefas;
//        arrayTarefasLocais = tarefas;
//        return arrayTarefasLocais;
//    }

    @Override
    public void adicionaTarefa(Tarefa tarefa) throws RemoteException {
        BolsaTarefas.tarefas.add(tarefa);
        System.out.println("tarefa adicionada! chave:" + tarefa.getChave());
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HashMap<String, Object> removeTarefa(int chave) throws RemoteException {
        int indice = buscaBinaria(chave);
        Tarefa tarefa = BolsaTarefas.tarefas.get(indice);
        BolsaTarefas.tarefas.remove(indice);
        return tarefa.getDescricao();
    }

    @Override
    public Tarefa leTarefa() throws RemoteException {
        int indice = encontraTarefaDisponivel();
        if (indice != -1) {
            Tarefa tarefa = BolsaTarefas.tarefas.get(indice);
            BolsaTarefas.tarefas.get(indice).setStatus(1);
            return tarefa;
        } else {
            return null;
        }
    }

    @Override
    public void atualizaTarefa(Tarefa tarefa) throws RemoteException {
        int indice = buscaBinaria(tarefa.getChave());
        BolsaTarefas.tarefas.get(indice).setDescricao(tarefa.getDescricao());
        BolsaTarefas.tarefas.get(indice).setStatus(2);
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public boolean terminouTarefas() throws RemoteException {
        if (BolsaTarefas.tarefas.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    private int encontraTarefaDisponivel() {
        for (int i = 0; i < BolsaTarefas.tarefas.size(); i++) {
            if (BolsaTarefas.tarefas.get(i).getStatus() == 0) {
                return BolsaTarefas.tarefas.get(i).getStatus();
            }
        }
        return -1;
    }

    private int buscaBinaria(int chave) {
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
        HashMap<String, Object> hRetorno = new HashMap<>();
        hRetorno.put("linhas", linhasRet);
        tarefaRetorno.setChave(tarefa.getChave());
        tarefaRetorno.setDescricao(hRetorno);

        return tarefaRetorno;
    }

}
