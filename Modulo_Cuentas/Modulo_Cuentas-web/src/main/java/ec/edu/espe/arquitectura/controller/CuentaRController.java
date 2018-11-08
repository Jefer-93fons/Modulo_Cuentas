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
import ec.edu.espe.arquitectura.web.util.FacesUtil;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
    private Cuenta cuentaSel;
    private int cuentaDigitada;
    private Transaccion transaccion;
    private int tipoTransaccion;

    private boolean visible;

    @Inject
    private CuentaService cuentaService;
        @Inject
    private TransaccionService transaccionService;

    public CuentaRController() {
    }

    @PostConstruct
    public void init() {
        visible = false;
        this.cuentas = cuentaService.obtenerTodos();
        for(Cuenta cnt:cuentas){
            System.out.println("asd: "+cnt.getIdCliente().getNombreCliente());
        }

        
        this.cuenta=new Cuenta();
        this.transaccion=new Transaccion();
        this.transaccion.setIdTipoTransaccion(new TipoTransaccion());
        this.transaccion.setValorTransaccion(BigDecimal.valueOf(0,00));
        this.cuenta.setIdCliente(new Cliente());
        this.cuenta.setIdProducto(new Producto());
    }
    // <editor-fold defaultstate="collapsed" desc="Getters & Setters">

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

    public Cuenta getCuentaSel() {
        return cuentaSel;
    }

    public void setCuentaSel(Cuenta cuentaSel) {
        this.cuentaSel = cuentaSel;
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
    public void modificar() {
        super.modificar(); //To change body of generated methods, choose Tools | Templates.
        this.cuenta = new Cuenta();
        this.cuenta=cuentaSel;
        
    }

    @Override
    public void agregar() {
        this.transaccion = new Transaccion();
        this.transaccion.setIdTipoTransaccion(new TipoTransaccion());
        this.transaccion.setValorTransaccion(BigDecimal.valueOf(0,00));
        super.agregar(); //To change body of generated methods, choose Tools | Templates.
        buscarCuenta();
        
    }

    public void buscarCuenta(){   
        for(Cuenta auxXuenta:cuentas){
            if (auxXuenta.getIdCuenta()==cuentaDigitada) {
                this.cuenta=auxXuenta;
            }
        }
    }
    public void cancelar() {
        super.reset();
        this.transaccion = new Transaccion();
    }

    public void guardar() {
        
        try {
            if (enAgregar) {
                System.out.println("ENTRO");
                    transaccion = new Transaccion();
                    Date date = new Date(2018, 11, 8);
                    transaccion.setFechaTransaccion(date);
                    TipoTransaccion tipoTransaccion = new TipoTransaccion();
                    tipoTransaccion.setIdTipoTransaccion(1);
                    transaccion.setIdTipoTransaccion(tipoTransaccion);
                    transaccion.setValorTransaccion(BigDecimal.valueOf(50));
                    transaccion.setIdCuenta(this.cuenta);
                    transaccion.setIdTransaccion(6);
                    transaccionService.crear(transaccion);
                //this.transaccionService.crear(this.transaccion);
                FacesUtil.addMessageInfo("Se realiz\u00f3 la transacci\u00f3n con un valor de : " + this.transaccion.getValorTransaccion());
            }
        } catch (Exception ex) {
            FacesUtil.addMessageError(null, "Ocurrí\u00f3 un error al actualizar la informaci\u00f3n");
        }

        super.reset();

        this.transaccion = new Transaccion();
        this.cuentas = cuentaService.obtenerTodos();
    }



    public String transformar(Date fecha) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        return format.format(fecha);
    }
}
