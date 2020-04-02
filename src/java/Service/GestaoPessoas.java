/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

 
import Repository.Pessoas; 
import modelo.Lancamento;
import modelo.Pessoa; 

/**
 *
 * @author Plebani
 */
public class GestaoPessoas {
    
    private Pessoas pessoas;
    
    public GestaoPessoas(Pessoas pessoas){
        this.pessoas = pessoas;
    }
    
    public void salvar(Pessoa pessoa) throws RegraNegocioExpextion {
        this.pessoas.guardar(pessoa);
    }
    
    public void excluir (Pessoa pessoa) throws RegraNegocioExpextion{
        if (!this.pessoas.temLancamentoParaPessoa(pessoa)){
            this.pessoas.remover(pessoa);
        }else{
            throw new RegraNegocioExpextion("Pessoa nao pode ser excluida pos está sendo utilizaddo em um Lancamento.");
        }
        
    }
    
}
