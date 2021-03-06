/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distribuidos;

import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author amanda
 */
public class Tarefa implements Serializable,Comparable<Tarefa>{
    int chave;
    int status;
    HashMap<String, Object> descricao;//2 objetos: ArrayList de vetor representando as linhas e a 2ª matriz
    
    
    public Tarefa(){
        
    }

    public Tarefa(int chave, int status, HashMap<String, Object> descricao) {
        this.chave = chave;
        this.status = status;
        this.descricao = descricao;
    }  

    public int getChave() {
        return chave;
    }

    public void setChave(int chave) {
        this.chave = chave;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    

    public HashMap<String, Object> getDescricao() {
        return descricao;
    }

    public void setDescricao(HashMap<String, Object> descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "chave: " + this.getChave() + "status: " + this.getStatus();
    }
    
    

    @Override
    public int compareTo(Tarefa tarefa) {
        if(this.chave < tarefa.chave){
            return -1;
        }
        if(this.chave < tarefa.chave){
            return 1;
        }
        return 0;
    }
    
    
}
