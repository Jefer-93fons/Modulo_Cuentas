/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.controller;

import ec.edu.espe.arquitectura.dao.ClienteFacade;
import ec.edu.espe.arquitectura.dao.CuentaFacade;
import ec.edu.espe.arquitectura.dao.ProductoFacade;
import ec.edu.espe.arquitectura.model.Cliente;
import ec.edu.espe.arquitectura.model.Cuenta;
import ec.edu.espe.arquitectura.model.Producto;
import ec.edu.espe.arquitectura.model.TipoProducto;
import ec.edu.espe.arquitectura.service.ClienteService;
import ec.edu.espe.arquitectura.service.CuentaService;
import ec.edu.espe.arquitectura.service.ProductoService;
import ec.edu.espe.arquitectura.service.TipoProductoService;
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
import javax.inject.Inject;
import javax.swing.JOptionPane;

/**
 *
 * @author Jefferson
 */

@ManagedBean (name = "cuentaController")
@SessionScoped
public class CuentaController extends BaseController implements Serializable{
    private Cuenta cuenta;
    private Cliente cliente;
    private Producto producto;
    private TipoProducto tipo;
    private TipoProducto tipos;

    public TipoProducto getTipos() {
        return tipos;
    }

    public void setTipos(TipoProducto tipos) {
        this.tipos = tipos;
    }
    
    
    
    private String mes;
    private List<String>meses;
    private String anio;
    private List<String> anios;
    private String identificacion;
    private String clientenombre;
    
    private boolean formCuenta;
    
    private List<Cliente> clientes;
    private List<Cuenta> cuentas;
    private List<Producto> productos;
    private List<TipoProducto> tiposProducto;
    private List<Cuenta> cuentasSelec;

    @Inject
    private CuentaService cuentaService;
    
    @Inject
    private ClienteService clienteService;
    
    @Inject
    private ProductoService productoService;
    
    @Inject
    private TipoProductoService tipoProductoService;

    /**
     * Creates a new instance of CuentaController
     */
    @PostConstruct
    public void init(){
        cuenta = new Cuenta();
        producto = new Producto();
        tipo = new TipoProducto();
        clientes = clienteService.obtenerTodos();
        cuentas = cuentaService.obtenerTodos();
        tiposProducto = tipoProductoService.obtenerTodos();
        productos = productoService.obtenerTodos();
        
        
        meses = new ArrayList<String>();
        anios = new ArrayList<String>();
        cuentasSelec = new ArrayList<Cuenta>();
        
        meses.add("Enero");
        meses.add("Febrero");
        meses.add("Marzo");
        meses.add("Abril");
        meses.add("Mayo");
        meses.add("Junio");
        meses.add("Julio");
        meses.add("Agosto");
        meses.add("Septiembre");
        meses.add("Octubre");
        meses.add("Noviembre");
        meses.add("Diciembre");
       
        anios.add("2016");
        anios.add("2017");
        anios.add("2018");
            
    }
    
    public CuentaController() {
    }
    
    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
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

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public List<String> getMeses() {
        return meses;
    }

    public void setMeses(List<String> meses) {
        this.meses = meses;
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

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public boolean isFormCuenta() {
        return formCuenta;
    }

    public void setFormCuenta(boolean formCuenta) {
        this.formCuenta = formCuenta;
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public List<TipoProducto> getTiposProducto() {
        return tiposProducto;
    }

    public void setTiposProducto(List<TipoProducto> tiposProducto) {
        this.tiposProducto = tiposProducto;
    }

    public TipoProducto getTipo() {
        return tipo;
    }

    public void setTipo(TipoProducto tipo) {
        this.tipo = tipo;
    }
    
    public List<Cuenta> getCuentasSelec() {
        return cuentasSelec;
    }

    public void setCuentasSelec(List<Cuenta> cuentasSelec) {
        this.cuentasSelec = cuentasSelec;
    }
     
    
    
    
    @Override
    public void buscar(){
        super.buscar();
        formCuenta = buscarCliente ();
    }
    
    public void filtrarProductos(){
        //productos = productoService.obtenerTodos();
        List<Producto> auxproductos  = new ArrayList<Producto>();
        
        for (Producto pro : productos){
          if(tipo.getIdTipoProducto() == pro.getIdTipoProducto().getIdTipoProducto()){
              auxproductos.add(pro);
          }
        }
        productos = auxproductos;
    }
    
    
    public void buscarCuenta() {
        
        for (Cuenta cunt : cuentas){
           // cuentasSelec.add(cunt);
            if (cunt.getIdCliente().getCodCliente().equals(identificacion)) { 
                cuentasSelec.add(cunt);
            }
        }
    }
     
    public boolean buscarCliente(){
        boolean flag = false;
        for (Cliente auxCliente : clientes) {
            if (auxCliente.getCodCliente().equals(identificacion)) {
                this.cliente = auxCliente;
                flag = true;
            }
        }
        
        return flag;

    }
    
    public void cargarDatos(){
            anios.add("Enero");
            anios.add("Febrero");
            anios.add("Marzo");
            anios.add("Abril");
            anios.add("Mayo");
            anios.add("Junio");
            anios.add("Julio");
            anios.add("Agosto");
            anios.add("Septiembre");
            anios.add("Octubre");
            anios.add("Noviembre");
            anios.add("Diciembre");
       
            meses.add("2016");
            meses.add("2017");
            meses.add("2018");
            
    }
}
