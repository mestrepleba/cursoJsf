/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conversor;

import Repository.Pessoas;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import modelo.Pessoa;
import util.Repositorios;

/**
 *
 * @author Plebani
 */
@FacesConverter(forClass = Pessoa.class)
public class PessoaConverter implements Converter{
    private Repositorios repositorios = new Repositorios();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Pessoa retorno = new Pessoa();
        
        if (value !=null){       
            Pessoas pessoas = this.repositorios.getPessoas();
            retorno = pessoas.porCodigo(new Integer(value));
        }
        return retorno;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((Pessoa)value).getCodigo().toString();
    }
    
}
