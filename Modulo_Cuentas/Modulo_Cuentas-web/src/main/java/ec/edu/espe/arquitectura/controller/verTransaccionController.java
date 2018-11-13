/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.controller;

import ec.edu.espe.arquitectura.model.Cliente;
import ec.edu.espe.arquitectura.model.TipoTransaccion;
import ec.edu.espe.arquitectura.model.Cuenta;
import ec.edu.espe.arquitectura.model.Producto;
import ec.edu.espe.arquitectura.model.Transaccion;
import ec.edu.espe.arquitectura.service.CuentaService;
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
    private int cuentaDigitada;
    private Cuenta cuenta;
    private List<Cuenta> cuentas;
    @Inject
    private TransaccionService transaccionService;
    @Inject
    private CuentaService cuentaService;

    public verTransaccionController() {
    }

    @PostConstruct
    public void init() {
        resetData();
    }

    @Override
    public void modificar() {
        super.modificar(); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public void agregar() {
        super.agregar(); //To change body of generated methods, choose Tools | Templates
        buscarCuenta();
        if (buscarCuenta()) {
            this.transacciones = this.transaccionService.porCuenta(cuentaDigitada);
        }    
    }

    public Boolean buscarCuenta() {
        this.cuentas=this.cuentaService.obtenerTodos();
        for (Cuenta auxXuenta : cuentas) {
            if (auxXuenta.getIdCuenta() == cuentaDigitada) {
                this.cuenta = auxXuenta;
                return true;
            }
        }
        return false;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    public int getCuentaDigitada() {
        return cuentaDigitada;
    }

    public void setCuentaDigitada(int cuentaDigitada) {
        this.cuentaDigitada = cuentaDigitada;
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

    public void resetData() {
        this.cuentaDigitada = 0;
        this.cuentas = cuentaService.obtenerTodos();
        this.cuenta = new Cuenta();
        this.cuenta.setIdCliente(new Cliente());
        this.cuenta.setIdProducto(new Producto());
    }
    
    public void buscar(){
        
    }
}
