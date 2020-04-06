/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import Repository.Pessoas;
import Service.GestaoPessoas;
import Service.RegraNegocioExpextion;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Pessoa;
import util.FacesUtil;
import util.Repositorios;

@ManagedBean
/**
 *
 * @author Plebani
 */
public class ConsultaPessoaBean implements Serializable{
    private List<Pessoa> pessoas = new ArrayList<>();
    private Pessoa pessoaSelecionada;
    private Repositorios repositorios = new Repositorios();
    
    @PostConstruct
    public void inicializar(){
        this.pessoaSelecionada = new Pessoa();
        
        Pessoas pessoas = this.repositorios.getPessoas();
        this.pessoas = pessoas.todas();
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    public Pessoa getPessoaSelecionada() {
        return pessoaSelecionada;
    }

    public void setPessoaSelecionada(Pessoa pessoaSelecionada) {
        this.pessoaSelecionada = pessoaSelecionada;
    }    
    
    public void excluir(){
        String msg ="";
        
        GestaoPessoas gestaoPessoas = new GestaoPessoas(this.repositorios.getPessoas());
        
        try {
            gestaoPessoas.excluir(pessoaSelecionada);
            this.inicializar(); //Recarecar os registros apos exclusao
            
            msg = "Pessoa foi removida com sucesso";           
            //Adiciona mensagem se o lancamento foi excluido com sucesso.
            FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_INFO, msg,msg);
        } catch (RegraNegocioExpextion e) {
            msg = "Pessoa nao foi removida. "+e.getMessage();
            FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_ERROR,msg, msg);
        }
    }
    
}
