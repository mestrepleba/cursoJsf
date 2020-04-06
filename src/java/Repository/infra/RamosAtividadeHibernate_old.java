/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository.infra;

import Repository.RamosAtividade;
import java.util.List;
import modelo.Pessoa;
import modelo.RamoAtividade;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

/**
 *
 * @author Plebani
 */
public class RamosAtividadeHibernate_old implements RamosAtividade{
    
    private Session session;
    
    public RamosAtividadeHibernate_old(Session session){
        this.session = session;
    }

    @Override
    public List<RamoAtividade> todos() {
        return session.createCriteria(RamoAtividade.class)
			.addOrder(Order.asc("descricao"))
			.list();
    }

    @Override
    public RamoAtividade porCodigo(Integer codigo) {
        return (RamoAtividade) session.get(RamoAtividade.class, codigo);
    }
    
}
