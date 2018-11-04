/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.controller;

import ec.edu.espe.arquitectura.model.Producto;
import ec.edu.espe.arquitectura.service.ProductoService;
import ec.edu.espe.arquitectura.web.util.FacesUtil;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author DAVID
 */
@Named
@ViewScoped
public class ProductoController extends BaseController implements Serializable {
    private List<Producto> productos;
    
    private Producto producto;
    
    private Producto productoSel;
    
    @Inject
    private ProductoService productoService;
    
    @PostConstruct
    public void init(){
        this.productos = this.productoService.obtenerTodos();
        this.producto = new Producto();
        
    }

    public List<Producto> getProductos() {
        return productos;
    }
    
    @Override 
    public void agregar(){
        this.producto = new Producto();
        super.agregar();
    }
    
    @Override
    public void modificar(){
        super.modificar();
        this.producto = new Producto();
        this.producto.setNombreProducto(this.productoSel.getNombreProducto());
        //this.producto.setIdEstadoProducto(this.productoSel.getIdEstadoProducto());
        this.producto.setRestriccionProducto(this.productoSel.getRestriccionProducto());
    }
    
    @Override
    public void detalles() {
        super.detalles();
        this.producto = this.productoSel;
    }

    public void cancelar() {
        super.reset();
        this.producto = new Producto();
    }

    public void guardar() {
        try {
            this.productoService.crear(this.producto);
            FacesUtil.addMessageInfo("Se agregó el Producto: " + this.producto.getNombreProducto());
        } catch (Exception ex) {
            FacesUtil.addMessageError(null, "Ocurrí\u00f3 un error al actualizar la informaci\u00f3n");
        }
        super.reset();
        this.producto = new Producto();
        this.productos = this.productoService.obtenerTodos();
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Producto getProductoSel() {
        return productoSel;
    }

    public void setProductoSel(Producto productoSel) {
        this.productoSel = productoSel;
    }

    public ProductoService getProductoService() {
        return productoService;
    }

    public void setProductoService(ProductoService productoService) {
        this.productoService = productoService;
    }
    
    
}
