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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    private String mes;
    private List<List<String>>mesesList;
    private String anio;
    private List<String> anios;
    
    @Inject
    private TransaccionService transaccionService;
    @Inject
    private CuentaService cuentaService;

    public verTransaccionController() {
    }

    @PostConstruct
    public void init() {
        resetData();
        
        mesesList = new ArrayList<List<String>>();
        anios = new ArrayList<String>();
        
        String meses[] = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        
        for(int i=1; i<13; i++){
            List<String> mesPosicion = new ArrayList<String>();
            if(i<10){
                mesPosicion.add("0"+i);
            }else{
                mesPosicion.add(String.valueOf(i));
            }
            mesPosicion.add(meses[i-1]);
            mesesList.add(mesPosicion);
        }
    
        anios.add("2016");
        anios.add("2017");
        anios.add("2018");
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

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public List<List<String>> getMesesList() {
        return mesesList;
    }

    public void setMesesList(List<List<String>> mesesList) {
        this.mesesList = mesesList;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public List<String> getAnios() {
        return anios;
    }

    public void setAnios(List<String> anios) {
        this.anios = anios;
    }

    
    public void resetData() {
        this.cuentaDigitada = 0;
        this.cuentas = cuentaService.obtenerTodos();
        this.cuenta = new Cuenta();
        this.cuenta.setIdCliente(new Cliente());
        this.cuenta.setIdProducto(new Producto());
    }
    
    public void buscarTransaccion() throws ParseException {
        this.transacciones = this.transaccionService.porCuenta(cuentaDigitada);
        if(mes!=null && anio!=null){
            SimpleDateFormat inputFecha = new SimpleDateFormat("MM/yyyy");  
            Date fechabusqueda = inputFecha.parse(this.mes+"/"+this.anio);
            Date fechainput;
            List<Transaccion> transaccionSel = new ArrayList<Transaccion>();

            for (Transaccion trans : transacciones){
                fechainput =  inputFecha.parse(inputFecha.format(trans.getFechaTransaccion()));
                 if(fechainput.equals(fechabusqueda)){
                     transaccionSel.add(trans);
                }          
                
            }
            transacciones = transaccionSel;
        }else{
            FacesUtil.addMessageError(null,"Campos Incompletos!");
        }
    }
}
