/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import Repository.Lancamentos;
import Service.GestaoLancamentos;
import Service.RegraNegocioExpextion;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import modelo.Lancamento;
import util.FacesUtil;
import util.Repositorios;

@ManagedBean
@ViewScoped
/**
 *
 * @author Plebani
 */
public class ConsultaLancamentoBean implements Serializable{
    private List<Lancamento> lancamentos = new ArrayList<>();
    private Lancamento lancamentoSelecionado = new Lancamento();
    private Repositorios repositorios = new Repositorios();
    
    @PostConstruct
    public void inicialiazar(){
        Lancamentos lancamentos = this.repositorios.getLancamentos();
        this.lancamentos =  lancamentos.todos();
    }

    public Lancamento getLancamentoSelecionado() {
        return lancamentoSelecionado;
    }

    public void setLancamentoSelecionado(Lancamento lancamentoSelecionado) {
        this.lancamentoSelecionado = lancamentoSelecionado;
    }
        
    public List<Lancamento> getLancamentos() {
        return lancamentos;
    }

    public void setLancamentos(List<Lancamento> lancamentos) {
        this.lancamentos = lancamentos;
    }
    
    public void excluir(){
        String msg ="";
        
        GestaoLancamentos gestaoLancamentos = new GestaoLancamentos(this.repositorios.getLancamentos());
        
        try {
            gestaoLancamentos.excluir(lancamentoSelecionado);
            this.inicialiazar(); //Recarecar os registros apos exclusao
            
            msg = "Lancamento excluido com sucesso";           
            //Adiciona mensagem se o lancamento foi excluido com sucesso.
            FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_INFO, msg,msg);
        } catch (RegraNegocioExpextion e) {
            msg = "Lancamento ja foi pago e não pode ser excluido";
            FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_ERROR,msg, msg);
        }
        
    }
}
