/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.service;

import ec.edu.espe.arquitectura.dao.CuentaFacade;
import ec.edu.espe.arquitectura.model.Cuenta;
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
public class CuentaService {

    @EJB
    private CuentaFacade cuentaFacade;
    
        public List<Cuenta> obtenerTodos(){
        return this.cuentaFacade.findAll();
    }
    
    public void crear(Cuenta cuenta){
        this.cuentaFacade.create(cuenta);
    }
    
    public void modificar(Cuenta cuenta){
        this.cuentaFacade.edit(cuenta);
    }

}
