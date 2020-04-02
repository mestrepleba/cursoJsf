/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Repository.Lancamentos;
import modelo.Lancamento;

/**
 *
 * @author Plebani
 */


// Regra de Negocio ref. Lancamentos

public class GestaoLancamentos {
    
    private Lancamentos lancamentos;
    
    public GestaoLancamentos(Lancamentos lancamentos){
        this.lancamentos = lancamentos;
    }
    
    public void salvar(Lancamento lancamento) throws RegraNegocioExpextion {
        if (existeLancamentoSemelhante(lancamento)) {
                throw new RegraNegocioExpextion("Já existe um lançamento igual a este.");
        }

        this.lancamentos.guardar(lancamento);
    }
    
    public boolean existeLancamentoSemelhante(Lancamento lancamento) throws RegraNegocioExpextion{
        Lancamento lancamentoSemelhante = this.lancamentos.comDadosIguais(lancamento);
        
        return(lancamentoSemelhante != null && !lancamentoSemelhante.equals(lancamento));
    }
    
    public void excluir (Lancamento lancamento) throws RegraNegocioExpextion{
        if (lancamento.isPago()){
            throw new RegraNegocioExpextion("Lancamento pago nao pode ser excluido");
        }
        
        this.lancamentos.remover(lancamento);
    }
    
}
