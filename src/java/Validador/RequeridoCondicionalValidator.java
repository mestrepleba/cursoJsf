/*  
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validador;

import com.sun.faces.util.MessageFactory;
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
@FacesValidator("RequeridoCondicional")
public class RequeridoCondicionalValidator implements Validator{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        
        Boolean checado = (Boolean)component.getAttributes().get("checado");
        
        if (checado != null && checado && value == null ){
            Object label = MessageFactory.getLabel(context, component);                       
            
            String descricaoErro = "Preencha o campo "+ label;
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,descricaoErro,descricaoErro);
            throw new ValidatorException(msg);
        }
    }
    
}
