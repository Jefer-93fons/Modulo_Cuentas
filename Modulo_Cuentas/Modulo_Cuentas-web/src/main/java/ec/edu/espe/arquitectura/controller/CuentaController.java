/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.controller;

import ec.edu.espe.arquitectura.dao.ClienteFacade;
import ec.edu.espe.arquitectura.dao.CuentaFacade;
import ec.edu.espe.arquitectura.dao.ProductoFacade;
import ec.edu.espe.arquitectura.dao.TipoProductoFacade;
import ec.edu.espe.arquitectura.model.Cliente;
import ec.edu.espe.arquitectura.model.Cuenta;
import ec.edu.espe.arquitectura.model.Producto;
import ec.edu.espe.arquitectura.model.TipoProducto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
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
    private ClienteFacade clienteFacade;
    private ProductoFacade productoFacade;
    
    private Cuenta cuenta;
    private Cliente cliente;
    private Producto producto;
    
    private String periodo;
    private List<String> periodos;
    private String rango;
    private List<String> rangos;
    private String identificacion;
    private String clientenombre;

    public CuentaFacade getCuentaFacade() {
        return cuentaFacade;
    }

    public void setCuentaFacade(CuentaFacade cuentaFacade) {
        this.cuentaFacade = cuentaFacade;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public ProductoFacade getProductoFacade() {
        return productoFacade;
    }

    public void setProductoFacade(ProductoFacade productoFacade) {
        this.productoFacade = productoFacade;
    }
    

    public ClienteFacade getClienteFacade() {
        return clienteFacade;
    }

    public void setClienteFacade(ClienteFacade clienteFacade) {
        this.clienteFacade = clienteFacade;
    }

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

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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
        producto = new Producto();
       
        
        periodos = new ArrayList<String>();
        periodos.add("Mensual");
        periodos.add("Anual"); 
        
        rangos = new ArrayList<String>();
            
    }

    public CuentaController() {
    }
    
    public List<Cuenta> listarTodasCuentas (){
        return cuentaFacade.findAll();
    }
    
    
    public List<Producto> listarTodosProductos (){
        return productoFacade.findAll();
    }
     
    public void buscarCliente (){
        List<Cliente> clientes;
        
        clientes = cuentaFacade.buscarCliente(identificacion);
        
        Iterator<Cliente> it = clientes.iterator();
        // mientras al iterador queda proximo juego
        while(it.hasNext()){
            Cliente cli = it.next();
            clientenombre = cli.getNombreCliente();
        }
        
//        try{
//
//            if (clientes!=null){
//                clientenombre = cliente.getNombreCliente() + " " + cliente.getApellidoCliente();
//            }else{
//                clientenombre = "No exit coincidencia";
//            }
//            
//        }catch(Exception e){
//            
//        }
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
