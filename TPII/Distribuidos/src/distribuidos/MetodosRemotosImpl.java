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
        int[][] m = (int[][]) tarefa.getDescricao().get("matriz");
        System.out.println("tarefa adicionada! " + tarefa.toString());
        for (int i = 0; i < m[0].length; i++) {
            System.out.print("[");
            for (int j = 0; j < m[1].length; j++) {
                System.out.print(m[i][j] + " ");
            }
            System.out.print("]\n");
        }
    }

    @Override
    public Tarefa removeTarefa() throws RemoteException {
        int indice = buscaTarefaFinalizada();
        if (indice != -1) {
            Tarefa tarefa = BolsaTarefas.tarefas.get(indice);
            BolsaTarefas.tarefas.remove(indice);
            return tarefa;
        } else {
            return null;
        }
    }

    @Override
    public Tarefa leTarefa() throws RemoteException {
        int indice = encontraTarefaDisponivel();
        System.out.println("indiceee:" + indice);
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
        for (int i = 0; i < BolsaTarefas.tarefas.size(); i++) {
            System.out.println("status tarefa " + BolsaTarefas.tarefas.get(i).getChave() + ": " + BolsaTarefas.tarefas.get(i).getStatus());
        }
    }

    @Override
    public boolean terminouTarefas() throws RemoteException {
        System.out.println("tamanho bolsa:" + BolsaTarefas.tarefas.size());
        if (BolsaTarefas.tarefas.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean estacaoConectada() throws RemoteException {
        return BolsaTarefas.estacaoConectada;
    }

    @Override
    public void conectaEstacao() throws RemoteException {
        System.out.println("conectou estacao");
        BolsaTarefas.estacaoConectada = true;
        BolsaTarefas.cont++;
    }

    @Override
    public void desconectaEstacao() throws RemoteException {
        System.out.println("desconectou estacao");
        BolsaTarefas.cont--;
        if (BolsaTarefas.cont == 0) {
            BolsaTarefas.estacaoConectada = false;
        }
    }

    private int encontraTarefaDisponivel() {
        for (int i = 0; i < BolsaTarefas.tarefas.size(); i++) {
            System.out.println("status tarefa " + i + ": " + BolsaTarefas.tarefas.get(i).getStatus());
            if (BolsaTarefas.tarefas.get(i).getStatus() == 0) {
                System.out.println("achoou");
                return i;
            }
        }
        return -1;
    }

    private int buscaTarefaFinalizada() {
        for (int i = 0; i < BolsaTarefas.tarefas.size(); i++) {
            if (BolsaTarefas.tarefas.get(i).getStatus() == 2) {
                return i;
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
        System.out.println("oioioi");
        ArrayList<ArrayList<Integer>> linhas = (ArrayList<ArrayList<Integer>>) tarefa.getDescricao().get("linhas");
        Tarefa tarefaRetorno = new Tarefa();
        ArrayList<ArrayList<Integer>> linhasRet = new ArrayList();

        for (int i = 0; i < linhas.size(); i++) {
            ArrayList<Integer> linha = linhas.get(i);
            int somatorio = 0;
            ArrayList<Integer> linhaRet = new ArrayList<>();
            for (int j = 0; j < matriz[1].length; j++) {
                somatorio = 0;
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
