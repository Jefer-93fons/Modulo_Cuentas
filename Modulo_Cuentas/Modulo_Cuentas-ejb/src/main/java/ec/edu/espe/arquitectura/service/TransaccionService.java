/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.service;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import ec.edu.espe.arquitectura.model.Transaccion;
import ec.edu.espe.arquitectura.dao.TransaccionFacade;
/**
 *
 * @author Victoria
 */
@Stateless
@LocalBean
public class TransaccionService {
    @EJB
    private TransaccionFacade transaccionFacade;
    
    public List<Transaccion> obtenerTodos(){
        return this.transaccionFacade.findAll();
    }
    
    public Transaccion obtenerPorCodigo(Integer codigo){
        return this.transaccionFacade.find(codigo);
    }
    
    public void crear(Transaccion transaccion){
        this.transaccionFacade.create(transaccion);
    }
    
    public void modificar (Transaccion transaccion){
        this.transaccionFacade.edit(transaccion);
    }
    
    public void eliminar(Integer codigo){
        Transaccion transaccion=this.transaccionFacade.find(codigo);
        this.transaccionFacade.remove(transaccion);
    }
    
    
}



