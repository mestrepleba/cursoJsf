/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import Repository.Lancamentos;
import Repository.Pessoas;
import Repository.RamosAtividade;
import Repository.infra.LancamentosHibernate;
import Repository.infra.PessoasHibernate;
import Repository.infra.RamosAtividadeHibernate_old;
import java.io.Serializable;
import org.hibernate.Session;

/**
 *
 * @author Plebani
 */
public class Repositorios implements Serializable{
    
    public Pessoas getPessoas(){
        return new PessoasHibernate(this.getSession());
    }
    
    public Lancamentos getLancamentos(){
        return new LancamentosHibernate(this.getSession());
    }
    
    public RamosAtividade getRamosAtividade(){
        return new RamosAtividadeHibernate_old(this.getSession());
    }
    
    private Session getSession(){
        return (Session) FacesUtil.getRequestAttribute("session"); 
    }
}
