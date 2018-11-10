/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.controller;

import ec.edu.espe.arquitectura.model.Producto;
import ec.edu.espe.arquitectura.model.TipoProducto;
import ec.edu.espe.arquitectura.service.ProductoService;
import ec.edu.espe.arquitectura.service.TipoProductoService;
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

    private TipoProducto tipo;

    private Producto productoSel;

    private List<TipoProducto> tiposProducto;

    @Inject
    private ProductoService productoService;

    @Inject
    private TipoProductoService tipoProductoService;

    @PostConstruct
    public void init() {
        this.producto = new Producto();
        this.tipo = new TipoProducto();
        this.productos = this.productoService.obtenerTodos();
        this.tiposProducto = this.tipoProductoService.obtenerTodos();

    }

    public List<TipoProducto> getTiposProducto() {
        return tiposProducto;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    @Override
    public void agregar() {
        this.producto = new Producto();
        super.agregar();
    }

    @Override
    public void modificar() {
        super.modificar();
        this.producto = new Producto();
        this.producto.setIdProducto(this.productoSel.getIdProducto());
        this.producto.setNombreProducto(this.productoSel.getNombreProducto());
        this.producto.setRestriccionProducto(this.productoSel.getRestriccionProducto());
        this.producto.setIdTipoProducto(this.tipo);

    }

    public void eliminar() {
        try {
            this.productoService.eliminar(this.productoSel.getIdProducto());
            this.productos = this.productoService.obtenerTodos();
            FacesUtil.addMessageInfo("Se elimino el registro.");
            this.productoSel = null;
        } catch (Exception e) {
            FacesUtil.addMessageError(null, "No se puede eliminar el registro seleccionado. Verifique que no tenga informacion relacionada.");
        }
    }
    
    public void cancelar() {
        super.reset();
        this.producto = new Producto();
    }

    public void guardar() {
        try {
            if (this.enAgregar) {
                this.producto.setIdProducto(0);
                this.producto.setNombreProducto(this.producto.getNombreProducto());
                this.producto.setRestriccionProducto(this.producto.getRestriccionProducto());
                this.producto.setIdTipoProducto(this.tipo);
                this.productoService.crear(this.producto);
                FacesUtil.addMessageInfo("Se agreg\u00f3 el Producto: " + this.producto.getNombreProducto());
            } else {
                this.productoService.modificar(this.producto);
                FacesUtil.addMessageInfo("Se modific\u00f3 el Producto: " + this.producto.getNombreProducto());
            }
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

    public TipoProductoService getTipoProductoService() {
        return tipoProductoService;
    }

    public void setTipoProductoService(TipoProductoService tipoProductoService) {
        this.tipoProductoService = tipoProductoService;
    }

    public TipoProducto getTipo() {
        return tipo;
    }

    public void setTipo(TipoProducto tipo) {
        this.tipo = tipo;
    }

}
