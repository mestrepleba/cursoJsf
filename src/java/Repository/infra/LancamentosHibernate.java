/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository.infra;

import Repository.Lancamentos;
import java.util.List;
import modelo.Lancamento;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Plebani
 */
public class LancamentosHibernate implements Lancamentos{

    private Session  session;
        
    public LancamentosHibernate(Session session){
        this.session =session;
    }
    
    @Override
    public List<Lancamento> todos() {
        return session.createCriteria(Lancamento.class)
                .addOrder(Order.desc("dataVencimento"))
                .list();
    }

    @Override
    public Lancamento guardar(Lancamento lancamento) {
        return (Lancamento) session.merge(lancamento); //Merge : Atualiza se existir e insere se não existir
    }

    @Override
    public void remover(Lancamento lancamento) {
        this.session.delete(lancamento);
    }

    @Override
    public Lancamento comDadosIguais(Lancamento lancamento) {
        return (Lancamento) this.session.createCriteria(Lancamento.class)
				.add(Restrictions.eq("tipo", lancamento.getTipo()))
				.add(Restrictions.eq("pessoa", lancamento.getPessoa()))
				.add(Restrictions.ilike("descricao", lancamento.getDescricao()))
				.add(Restrictions.eq("valor", lancamento.getValor()))
				.add(Restrictions.eq("dataVencimento", lancamento.getDataVencimento()))
				.uniqueResult();
    }
    
}
