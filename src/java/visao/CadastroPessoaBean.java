/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import Repository.RamosAtividade;
import Service.GestaoPessoas;
import Service.RegraNegocioExpextion;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import modelo.Pessoa;
import modelo.RamoAtividade;
import modelo.TipoPessoa;
import util.FacesUtil;
import util.Repositorios;

@ManagedBean
@ViewScoped
/**
 *
 * @author Plebani
 */
public class CadastroPessoaBean implements Serializable{
    private Pessoa pessoa = new Pessoa();
    private List<Pessoa> pessoas = new ArrayList<>();
    private List<RamoAtividade> ramosAtividade = new ArrayList<>();
    private boolean pessoaFisica;
    private boolean pessoaJuridica;
    private Repositorios repositorios = new Repositorios();
    
    @PostConstruct
    public void init(){
        this.pessoaFisica = false;
        this.pessoaJuridica = false;
        
        RamosAtividade ramos  = this.repositorios.getRamosAtividade();  
        this.ramosAtividade = ramos.todos();
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) throws CloneNotSupportedException {
        this.pessoa = pessoa;
        
        //Colocado devido a tag viewparam colocada na tela Consulta lancamento.
        if (this.pessoa == null){
            this.pessoa = new Pessoa();            
        }else{
            this.setaTipoPessoa();
            this.pessoa = (Pessoa) pessoa.clone();            
        }
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }
    
    public TipoPessoa[] getTipoPessoas(){
        return TipoPessoa.values();
    }

    public List<RamoAtividade> getRamosAtividade() {
        return ramosAtividade;
    }

    public boolean isPessoaFisica() {
        return pessoaFisica;
    }

    public boolean isPessoaJuridica() {
        return pessoaJuridica;
    }
    
    public void salvar(){
        String msg;
        GestaoPessoas gestaoPessoas = new GestaoPessoas(this.repositorios.getPessoas());
               
            gestaoPessoas.salvar(this.pessoa);            
             
            if (this.isEditando()){
                msg = "Alteracao efetuada com sucesso!";
            }else{
               msg = "Cadastro efetuado com sucesso!";
            }
            
            this.pessoa = new Pessoa();
            
            FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_INFO, msg, msg);
    }
    
    public void defineTipoPessoa(ValueChangeEvent event){
        this.pessoa.setTipoPessoa((TipoPessoa)event.getNewValue());
        
        this.setaTipoPessoa();
        
        FacesContext.getCurrentInstance().renderResponse(); // Pular para ultima etapa do ciclo de vida do JSF
    }    
    
    public boolean isEditando(){
        return this.pessoa.getCodigo() != null;
    }
        
    public void setaTipoPessoa() {

        if (this.pessoa != null && this.pessoa.getTipoPessoa() != null) {
            if (this.pessoa.getTipoPessoa().getDescricao().equalsIgnoreCase("fisica")) {
                this.pessoaFisica = true;
                this.pessoaJuridica = false;
                this.pessoa.setRamoAtividade(null);
            } else {
                this.pessoaJuridica = true;
                this.pessoaFisica = false;
                this.pessoa.setDataNascimento(null);
            }
        }

    }
}
