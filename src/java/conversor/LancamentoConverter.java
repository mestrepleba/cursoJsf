/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conversor;

import Repository.Lancamentos;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import modelo.Lancamento;
import util.Repositorios;

/**
 *
 * @author Plebani
 */
@FacesConverter(forClass=Lancamento.class)
public class LancamentoConverter implements Converter {

	private Repositorios repositoriosBean = new Repositorios();
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Lancamento retorno = null;
		Lancamentos lancamentos = this.repositoriosBean.getLancamentos();
		
		if (value != null && !value.equals("")) {
			retorno = lancamentos.porCodigo(new Integer(value));
			
			if (retorno == null) {
				String descricaoErro = "Lançamento não existe.";
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
			Integer codigo = ((Lancamento) value).getCodigo();
			return codigo == null ? "" : codigo.toString();
		}
		return null;
	}

}