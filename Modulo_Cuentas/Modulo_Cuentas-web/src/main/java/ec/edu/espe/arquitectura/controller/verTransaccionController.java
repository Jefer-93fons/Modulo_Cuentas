/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.controller;

import ec.edu.espe.arquitectura.model.TipoTransaccion;
import ec.edu.espe.arquitectura.model.Cuenta;
import ec.edu.espe.arquitectura.model.Transaccion;
import ec.edu.espe.arquitectura.service.TransaccionService;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Juan
 */
@Named(value = "verTransaccionController")
@ViewScoped
public class verTransaccionController extends BaseController implements Serializable {
    List<Transaccion> transacciones;
    
    @Inject
    private TransaccionService transaccionService;
    public verTransaccionController() {
    }

    @PostConstruct
    public void init() {
        this.transacciones=transaccionService.obtenerTodos();
    }

    @Override
    public void modificar() {
        super.modificar(); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public void agregar() {

        super.agregar(); //To change body of generated methods, choose Tools | Templates.

    }

    public List<Transaccion> getTransacciones() {
        return transacciones;
    }

    public void setTransacciones(List<Transaccion> transacciones) {
        this.transacciones = transacciones;
    }

    public TransaccionService getTransaccionService() {
        return transaccionService;
    }

    public void setTransaccionService(TransaccionService transaccionService) {
        this.transaccionService = transaccionService;
    }

}
