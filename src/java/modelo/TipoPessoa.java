/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Plebani
 */
public enum TipoPessoa {
    FISICA("Fisica"),
    JURIDICA("Juridica");
    
    private String descricao;
    
    TipoPessoa(String descricao){
        this.descricao = descricao;
    }     

    public String getDescricao() {
        return this.descricao;
    }
    
}


 