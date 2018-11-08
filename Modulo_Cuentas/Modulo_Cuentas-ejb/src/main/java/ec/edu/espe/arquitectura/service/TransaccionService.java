/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.service;

import ec.edu.espe.arquitectura.dao.TransaccionFacade;
import ec.edu.espe.arquitectura.model.Transaccion;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author DAVID
 */
@Stateless
@LocalBean
public class TransaccionService {

    @EJB
    private TransaccionFacade transaccionFacade;
    
        public List<Transaccion> obtenerTodos(){
        return this.transaccionFacade.findAll();
    }
    
    public void crear(Transaccion transaccion){
        this.transaccionFacade.create(transaccion);
    }
    
    public void modificar(Transaccion transaccion){
        this.transaccionFacade.edit(transaccion);
    }

}
