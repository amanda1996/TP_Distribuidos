/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distribuidos;

import java.util.HashMap;

/**
 *
 * @author amanda
 */
public class Tarefa {
    int chave;
    HashMap<String, Object> descricao;
    
    
    public Tarefa(){
        
    }

    public Tarefa(int chave, HashMap<String, Object> descricao) {
        this.chave = chave;
        this.descricao = descricao;
    }
    

    public int getChave() {
        return chave;
    }

    public void setChave(int chave) {
        this.chave = chave;
    }

    public HashMap<String, Object> getDescricao() {
        return descricao;
    }

    public void setDescricao(HashMap<String, Object> descricao) {
        this.descricao = descricao;
    }
    
    
}
