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
@FacesValidator("ValidarEmail")
public class ValidarEmailValidator implements Validator{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
       if (!value.toString().contains("@")){
           Object label = MessageFactory.getLabel(context, component);
           String descricaoErro = label + " precisa ter o caracter @";
           FacesMessage msg  = new FacesMessage(FacesMessage.SEVERITY_ERROR,descricaoErro,descricaoErro);
           throw new ValidatorException(msg);
       } 
    }
    
}
