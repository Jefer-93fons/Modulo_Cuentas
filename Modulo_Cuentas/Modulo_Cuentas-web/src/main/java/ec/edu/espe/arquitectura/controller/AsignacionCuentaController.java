/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.controller;

import ec.edu.espe.arquitectura.dao.CuentaFacade;
import ec.edu.espe.arquitectura.dao.ProductoFacade;
import ec.edu.espe.arquitectura.model.Cuenta;
import ec.edu.espe.arquitectura.model.Producto;
import ec.edu.espe.arquitectura.model.TipoProducto;
import ec.edu.espe.arquitectura.service.CuentaService;
import ec.edu.espe.arquitectura.service.ProductoService;
import ec.edu.espe.arquitectura.service.TipoProductoService;
import ec.edu.espe.arquitectura.web.util.FacesUtil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

/**
 *
 * @author Jefferson
 */
@ManagedBean (name = "asignacionController")
@SessionScoped
public class AsignacionCuentaController extends BaseController implements Serializable{
    private TipoProducto tipo;
    private List<TipoProducto> tiposProducto;
    private Producto producto;
    private List<Producto> productos;
    private Cuenta cuenta;
    
    @Inject
    private TipoProductoService tipoProductoService;
    private ProductoService productoService;
    private CuentaService cuentaService;
    
    @PostConstruct
    public void init() {
        this.tipo = new TipoProducto();
        this.producto = new Producto();
    }

    public TipoProducto getTipo() {
        return tipo;
    }

    public void setTipo(TipoProducto tipo) {
        this.tipo = tipo;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
    
    public List<TipoProducto> getTiposProducto() {
        return tiposProducto;
    }
    
    public List<Producto> getProductos() {
        return productos;
    }
    
    public void guardar() {
        try {
            if (this.enAgregar) {

                //this.cuenta.setIdCliente('1');
                this.cuenta.setIdProducto(producto);
                this.cuenta.setSaldoCuenta(BigDecimal.ONE);
                
                this.cuentaService.crear(this.cuenta);
                FacesUtil.addMessageInfo("Se agreg\u00f3 la cuenta: " + this.cuenta.getIdCuenta());
            } 
        } catch (Exception ex) {
            FacesUtil.addMessageError(null, "Ocurrí\u00f3");
        }
        super.reset();
        this.cuenta = new Cuenta();
        this.productos = this.productoService.obtenerTodos();
    }
    
    
}
