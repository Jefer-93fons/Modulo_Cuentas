/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.service;

import ec.edu.espe.arquitectura.dao.TipoTransaccionFacade;
import ec.edu.espe.arquitectura.model.TipoTransaccion;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
 
/**
 *
 * @author Victoria
 */

@Stateless
@LocalBean 
public class TipoTransaccionService {
    @EJB
    private TipoTransaccionFacade tipoTransaccionFacde;
    
    public List<TipoTransaccion> obtenerTodos(){
        return this.tipoTransaccionFacde.findAll();
        
    }
    
    public void crear(TipoTransaccion tipoTransaccion){
        this.tipoTransaccionFacde.create(tipoTransaccion);
    }
}



