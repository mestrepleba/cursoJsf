/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Plebani
 */
public class FacesUtil {
    
    public static void adicionarMensagem(FacesMessage.Severity tipo, String msg_sumario,String msg_dateil){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(tipo,msg_sumario,msg_dateil));
    }
    
    public static Object getRequestAttribute(String name){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest)externalContext.getRequest();
        
        return request.getAttribute(name);
    }
}
