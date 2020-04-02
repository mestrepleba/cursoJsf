/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository.infra;

import Repository.Lancamentos;
import Repository.Pessoas;
import java.util.List;
import modelo.Lancamento;
import modelo.Pessoa;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import util.Repositorios;

/**
 *
 * @author Plebani
 */
public class PessoasHibernate implements Pessoas{
    
    private Session session;
    
    public PessoasHibernate(Session session){
        this.session = session;
    }
    
    @Override
    public List<Pessoa> todas() {
        return session.createCriteria(Pessoa.class)
			.addOrder(Order.asc("nome"))
			.list();
    }

    @Override
    public Pessoa porCodigo(Integer codigo) {
        return (Pessoa)session.get(Pessoa.class, codigo); 
    }

    @Override
    public Pessoa guardar(Pessoa pessoa) {
        return (Pessoa) this.session.merge(pessoa);
    }
    

    @Override
    public void remover(Pessoa pessoa) {
         session.delete(pessoa);
    }
    
    @Override
    public boolean temLancamentoParaPessoa(Pessoa pessoa){
        boolean retorno;
        
        Repositorios repositorios = new Repositorios();
        Lancamentos lancamentos = repositorios.getLancamentos();
        try {
            Lancamento lancamento = (Lancamento) this.session.createCriteria(Lancamento.class)
                                        .add(Restrictions.eq("pessoa", pessoa))
                                        .uniqueResult();
            retorno = false;// Tem registro
            if (lancamento != null){
                System.out.println("Lancamento"+ lancamento.getDescricao());
            }
            
        } catch (Exception e) {
            System.out.println("Entrou na exception do metodo temLancamentoParaPessoa");
            retorno = true; //
        }
        
        return retorno;
    }

}
