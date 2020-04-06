/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conversor;

import Repository.RamosAtividade;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
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
        RamosAtividade ramos = this.repositorios.getRamosAtividade();

        if (value != null && !value.equals("")) {
            retorno = ramos.porCodigo(new Integer(value));

            if (retorno == null) {
                String descricaoErro = "Ramo de Atividade não existe.";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        descricaoErro, descricaoErro);
                throw new ConverterException(message);
            }
        }

        return retorno;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            Integer codigo = ((RamoAtividade) value).getCodigo();
            return codigo == null ? "" : codigo.toString();
        }
        return null;

    }
    
}
