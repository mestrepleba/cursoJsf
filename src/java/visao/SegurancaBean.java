/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import util.FacesUtil;

/**
 *
 * @author Plebani
 */
@ManagedBean
public class SegurancaBean {

    private String usuario;
    private String senha;

    public String logar() {
        try {
            this.getRequest().login(this.usuario, this.senha);
            return "Home?faces-redirect=true";
        } catch (ServletException e) {
            System.out.println("ERRO: "+e.getMessage());    
            FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_ERROR, "Usuario/senha Incorreta", "Usuario/senha Incorreta");
                
            return null;
        }
    }

    public String sair() throws ServletException {
		this.getRequest().logout();
		return "Login?faces-redirect=true";
	}
	
	private HttpServletRequest getRequest() {
		FacesContext context = FacesContext.getCurrentInstance();
		return (HttpServletRequest) context.getExternalContext().getRequest();
	}
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

}
