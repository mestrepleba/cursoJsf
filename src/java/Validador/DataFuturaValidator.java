/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validador;

import com.sun.faces.util.MessageFactory;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Plebani
 */
@FacesValidator("ValidaDataFutura")
public class DataFuturaValidator implements Validator{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        Date data = (Date) value;
        
        if (data != null && data.after(new Date())){
            Object label = MessageFactory.getLabel(context, component);
            String descricaoErro = label + " nao pode ser uma data futura";
            FacesMessage msg  = new FacesMessage(FacesMessage.SEVERITY_ERROR,descricaoErro,descricaoErro);
            throw new ValidatorException(msg);
        }
    }
    
}
