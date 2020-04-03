/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import java.util.List;
import modelo.Lancamento;

/**
 *
 * @author Plebani
 */
public interface Lancamentos {
    public List<Lancamento> todos();
    public void remover(Lancamento lancamento);
    public Lancamento guardar(Lancamento lancamento);
    public Lancamento comDadosIguais(Lancamento lancamento);
    public Lancamento porCodigo (Integer codigo);
    
}
