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
import ec.edu.espe.arquitectura.service.FechaTrabajoService;
import ec.edu.espe.arquitectura.service.TransaccionService;
import ec.edu.espe.arquitectura.web.util.FacesUtil;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Juan
 */
@Named(value = "cuentaRController")
@ViewScoped
public class CuentaRController extends BaseController implements Serializable {

    private List<Cuenta> cuentas;
    private Cuenta cuenta;
    private int cuentaDigitada;
    private Transaccion transaccion;
    private float valor;
    private int tipoTransaccion;

    private boolean visible;

    @Inject
    private CuentaService cuentaService;
    @Inject
    private TransaccionService transaccionService;
    @Inject
    private FechaTrabajoService fechaTrabajoService;

    public CuentaRController() {
    }

    @PostConstruct
    public void init() {
        valor = (float) 0.00;
        this.cuentas = cuentaService.obtenerTodos();
        this.cuenta = new Cuenta();
        this.cuenta.setIdCliente(new Cliente());
        this.cuenta.setIdProducto(new Producto());
    }
    public void resetData(){
        valor = (float) 0.00;
        this.transaccion = new Transaccion();
        this.cuentaDigitada = 0;
        this.cuentas = cuentaService.obtenerTodos();
        this.cuenta = new Cuenta();
        this.cuenta.setIdCliente(new Cliente());
        this.cuenta.setIdProducto(new Producto());
    }
    // <editor-fold defaultstate="collapsed" desc="Getters & Setters">

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public int getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(int tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public int getCuentaDigitada() {
        return cuentaDigitada;
    }

    public void setCuentaDigitada(int cuentaDigitada) {
        this.cuentaDigitada = cuentaDigitada;
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public Transaccion getTransaccion() {
        return transaccion;
    }

    public void setTransaccion(Transaccion transaccion) {
        this.transaccion = transaccion;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    // </editor-fold>
    @Override
    public void agregar() {
        this.transaccion = new Transaccion();
        this.transaccion.setIdTipoTransaccion(new TipoTransaccion());
        this.transaccion.setValorTransaccion(BigDecimal.valueOf(0, 00));
        super.agregar(); //To change body of generated methods, choose Tools | Templates.
        buscarCuenta();

    }

    public void buscarCuenta() {
        for (Cuenta auxXuenta : cuentas) {
            if (auxXuenta.getIdCuenta() == cuentaDigitada) {
                this.cuenta = auxXuenta;
            }
        }
    }

    public void cancelar() {
        super.reset();
        resetData();
    }

    public void guardar() {

        try {
            if (enAgregar) {
                transaccion = new Transaccion();
                transaccion.setFechaTransaccion(this.fechaTrabajoService.obtenerActual().getFechaProceso());
                TipoTransaccion tipoTransaccion = new TipoTransaccion();
                tipoTransaccion.setIdTipoTransaccion(this.tipoTransaccion);
                transaccion.setIdTipoTransaccion(tipoTransaccion);
                transaccion.setValorTransaccion(BigDecimal.valueOf(this.valor));
                transaccion.setIdCuenta(this.cuenta);
                transaccion.setIdTransaccion(6);
                transaccionService.crear(transaccion);
                if (this.tipoTransaccion==1) {
                    this.cuenta.setSaldoCuenta(this.cuenta.getSaldoCuenta().add(BigDecimal.valueOf(this.valor)));
                }else{
                    this.cuenta.setSaldoCuenta(this.cuenta.getSaldoCuenta().add(BigDecimal.valueOf(this.valor*-1)));
                }
                cuentaService.modificar(cuenta);
                FacesUtil.addMessageInfo("Se realiz\u00f3 la transacci\u00f3n por un valor de : $" + this.transaccion.getValorTransaccion());
            }
        } catch (Exception ex) {
            FacesUtil.addMessageError(null, "Ocurrí\u00f3 un error al actualizar la informaci\u00f3n");
        }

        super.reset();
        resetData();
    }


}
