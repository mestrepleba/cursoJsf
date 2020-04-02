
import java.util.List;
import modelo.Pessoa;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import util.HibernateUtil;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Plebani
 */
public class TesteHibernate {
    public static void main(String[] args) {
        
        Session session =  HibernateUtil.getSession();
        
        List<Pessoa> pessoas = session.createCriteria(Pessoa.class)
                .add(Restrictions.gt("codigo", 2)) // clausula WHERE onde codigo seja maior que                
                .list();              
        for (Pessoa p : pessoas){
            System.out.println("Codigo: " + p.getCodigo()+ " Nome: "+ p.getNome());
        }
        
        session.close();
    }
 
}
