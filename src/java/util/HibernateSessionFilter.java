/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Plebani
 */


@WebFilter(servletNames={"Faces Servlet"})
public class HibernateSessionFilter implements Filter{    

    //Todos as classes irão buscar a sessão dessa classe
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Session session = HibernateUtil.getSession();
        Transaction trx = null;
        try {
            trx = session.beginTransaction();
            request.setAttribute("session", session);
            
            chain.doFilter(request, response); //Continua o processo, senão irá ficar parado nesse ponto
            
            trx.commit(); // deu tudo certo nos processos;
            
        } catch (Exception e) {
            if (trx != null){
                trx.rollback();
            }
        }finally{
            session.close();
        }
    }
    
    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
    }
    
}
