/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conversor;

import Repository.Pessoas;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
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
        Pessoas pessoas = this.repositorios.getPessoas();

        if (value != null && !value.equals("")) {
            retorno = pessoas.porCodigo(new Integer(value));

            if (retorno == null) {
                String descricaoErro = "Pessoa não existe.";
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
            Integer codigo = ((Pessoa) value).getCodigo();
            return codigo == null ? "" : codigo.toString();
        }
        return null;

    }
    
}
