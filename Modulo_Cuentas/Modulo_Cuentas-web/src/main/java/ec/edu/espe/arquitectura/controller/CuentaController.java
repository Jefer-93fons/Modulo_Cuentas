/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.controller;

import ec.edu.espe.arquitectura.dao.CuentaFacade;
import ec.edu.espe.arquitectura.model.Cuenta;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.swing.JOptionPane;

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
    private String periodo;
    private List<String> periodos;
    private String rango;
    private List<String> rangos;
    private String identificacion;
    private String clientenombre;

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getClientenombre() {
        return clientenombre;
    }

    public void setClientenombre(String clientenombre) {
        this.clientenombre = clientenombre;
    }
    
    
    

    public String getRango() {
        return rango;
    }

    public void setRango(String rango) {
        this.rango = rango;
    }

    public List<String> getRangos() {
        return rangos;
    }

    public void setRangos(List<String> rangos) {
        this.rangos = rangos;
    }
    
    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public List<String> getPeriodos() {
        return periodos;
    }

    public void setPeriodos(List<String> periodos) {
        this.periodos = periodos;
    }
    
    
    /**
     * Creates a new instance of CuentaController
     */
    @PostConstruct
    public void init(){
        cuenta = new Cuenta();
        
        periodos = new ArrayList<String>();
        periodos.add("Diario");
        periodos.add("Mensual");
        periodos.add("Anual"); 
        
        rangos = new ArrayList<String>();
            
    }

    public CuentaController() {
    }
    
    public List<Cuenta> listarTodasCuentas (){
        return cuentaFacade.findAll();
    }
    
    public void cargarDatos(){
        if(periodo.equals("Mensual")){
            rangos.add("Enero");
            rangos.add("Febrero");
            rangos.add("Marzo");
            rangos.add("Abril");
            rangos.add("Mayo");
            rangos.add("Junio");
            rangos.add("Julio");
            rangos.add("Agosto");
            rangos.add("Septiembre");
            rangos.add("Octubre");
            rangos.add("Noviembre");
            rangos.add("Diciembre");
        }
        
        if(periodo.equals("Anual")){
            rangos.add("2016");
            rangos.add("2017");
            rangos.add("2018");
        }
            
    }
}
