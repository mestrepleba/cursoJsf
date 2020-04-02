/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conversor;

import Repository.RamosAtividade;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import modelo.RamoAtividade;
import util.Repositorios;

/**
 *
 * @author Plebani
 */
    
@FacesConverter(forClass = RamoAtividade.class)
public class RamoAtividadeConverter implements Converter{
    private Repositorios repositorios = new Repositorios();

     @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        RamoAtividade retorno = new RamoAtividade();
        
        if (value !=null){
            RamosAtividade ramos = this.repositorios.getRamosAtividade();
            retorno = ramos.porCodigo(new Integer(value));
        }
        return retorno;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((RamoAtividade)value).getCodigo().toString();
    }

   
    
}
