/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.controller;

import ec.edu.espe.arquitectura.dao.CuentaFacade;
import ec.edu.espe.arquitectura.model.Cuenta;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Jefferson
 */

@ManagedBean (name = "cuentaController")
@SessionScoped
public class CuentaController implements Serializable{
    @EJB
    private CuentaFacade cuentaFacade;
    private Cuenta cuenta;
    
    /**
     * Creates a new instance of CuentaController
     */
    @PostConstruct
    public void init(){
        cuenta = new Cuenta();
    }

    public CuentaController() {
    }
    
    public List<Cuenta> listarTodasCuentas (){
        return cuentaFacade.findAll();
    }
}
