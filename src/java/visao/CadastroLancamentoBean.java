/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import Repository.Pessoas;
import Service.GestaoLancamentos;
import Service.RegraNegocioExpextion;
import modelo.Lancamento;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import modelo.Pessoa;
import modelo.TipoLancamento;
import util.FacesUtil;
import util.Repositorios;


@ManagedBean
@ViewScoped
/**
 *
 * @author Plebani
 */
public class CadastroLancamentoBean implements Serializable{
    private List<Pessoa> pessoas = new ArrayList<>();
    private Lancamento lancamento = new Lancamento();
    private Repositorios repositorios = new Repositorios();
    
    @PostConstruct
    public void init() {
        Pessoas pessoas =  this.repositorios.getPessoas();        
        this.pessoas = pessoas.todas();
    }
    
    public TipoLancamento[] getTipoLancamento(){
        return TipoLancamento.values();
    }

    public Lancamento getLancamento() {
        return lancamento;
    }

    public void setLancamento(Lancamento lancamento) throws CloneNotSupportedException {
        this.lancamento = lancamento;
        
        //Colocado devido a tag viewparam colocada na tela Consulta lancamento.
        if (this.lancamento == null){
            this.lancamento = new Lancamento();
        }else{
            this.lancamento = (Lancamento) lancamento.clone();
        }
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }
    
    public boolean isEditando(){
        return this.lancamento.getCodigo() != null;
    }
    
    public void salvar() {
        GestaoLancamentos gestaoLancamentos = new GestaoLancamentos(this.repositorios.getLancamentos());
        String msg;
        try{
            gestaoLancamentos.salvar(this.lancamento);
            
            if (this.isEditando()){
                msg = "Alteracao efetuada com sucesso!";
            }else{
                msg = "Cadastro efetuado com sucesso!";
            }
            
            this.lancamento = new Lancamento();
            FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_INFO, msg, msg);
    
        }catch (RegraNegocioExpextion e){
            FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
        }
        
    }
    
    //Usado com o atributo immediate e o valueChangeListener para alterar o valor da variavel.
    //Isso é necessario para antecipar o preenchimento da variavel
    public void lancamentoPagoModificado(ValueChangeEvent event){
        this.lancamento.setPago((Boolean)event.getNewValue());
        this.lancamento.setDataPagamento(null);
        
        FacesContext.getCurrentInstance().renderResponse(); // Pular para ultima etapa do ciclo de vida do JSF
    }
}
