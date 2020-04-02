/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import java.util.List;
import modelo.Pessoa;
import modelo.RamoAtividade;

/**
 *
 * @author Plebani
 */
public interface RamosAtividade {
    public List<RamoAtividade> todos();
    public RamoAtividade porCodigo(Integer codigo);
}
