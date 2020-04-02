/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import java.util.List;
import modelo.Lancamento;
import modelo.Pessoa;

/**
 *
 * @author Plebani
 */
public interface Pessoas {
    
    public List<Pessoa> todas();
    public Pessoa porCodigo(Integer codigo);
    public Pessoa guardar(Pessoa pessoa);
    public void remover(Pessoa pessoa);
    public boolean temLancamentoParaPessoa(Pessoa pessoa);
}
