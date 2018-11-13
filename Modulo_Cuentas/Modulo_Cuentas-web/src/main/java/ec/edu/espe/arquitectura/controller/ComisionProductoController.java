/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.controller;

import ec.edu.espe.arquitectura.model.Comision;
import ec.edu.espe.arquitectura.model.ComisionProducto;
import ec.edu.espe.arquitectura.model.Producto;
import ec.edu.espe.arquitectura.service.ComisionProductoService;
import ec.edu.espe.arquitectura.service.ComisionService;
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
public class ComisionProductoController extends BaseController implements Serializable {

    private List<ComisionProducto> comisionproductos;
    
    private List<Comision> comisiones;
    
    private ComisionProducto comisionProducto;
    
    private Comision comision;

    private Producto producto;

    private ComisionProducto comisionProductoSel;

    private List<Producto> productos;

    @Inject
    private ComisionService comisionService;

    @Inject
    private ProductoService productoService;
    
    @Inject
    private ComisionProductoService comisionproductoService;

    @PostConstruct
    public void init() {
        this.comisionProducto = new ComisionProducto();
        this.comision = new Comision();
        this.producto = new Producto();
        this.comisiones = this.comisionService.obtenerTodos();
        this.productos = this.productoService.obtenerTodos();
        this.comisionproductos = this.comisionproductoService.obtenerTodos();

    }

    public List<ComisionProducto> getComisionproductos() {
        return comisionproductos;
    }

    public void setComisionproductos(List<ComisionProducto> comisionproductos) {
        this.comisionproductos = comisionproductos;
    }

    public List<Comision> getComisiones() {
        return comisiones;
    }

    public void setComisiones(List<Comision> comisiones) {
        this.comisiones = comisiones;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    

    

    @Override
    public void agregar() {
        this.comisionProducto = new ComisionProducto();
        super.agregar();
    }

    @Override
    public void modificar() {
        super.modificar();
        this.comisionProducto = new ComisionProducto();
        this.comisionProducto.setIdComision(this.comision);
        this.comisionProducto.setIdProducto(this.producto);
        this.comisionProducto.setValorComisionProducto(this.comisionProductoSel.getValorComisionProducto());

    }

    public void eliminar() {
        try {
            this.comisionproductoService.eliminar(this.comisionProductoSel.getIdComisionProducto());
            this.comisionproductos = this.comisionproductoService.obtenerTodos();
            FacesUtil.addMessageInfo("Se elimino el registro.");
            this.comisionProductoSel = null;
        } catch (Exception e) {
            FacesUtil.addMessageError(null, "No se puede eliminar el registro seleccionado. Verifique que no tenga informacion relacionada.");
        }
    }

    public void cancelar() {
        super.reset();
        this.comisionProducto = new ComisionProducto();
    }

    public void guardar() {
        try {
            if (this.enAgregar) {
                this.comisionProducto.setIdComisionProducto(0);
                this.comisionProducto.setIdComision(this.comision);
                this.comisionProducto.setIdProducto(this.producto);
                this.comisionProducto.setValorComisionProducto(this.comisionProducto.getValorComisionProducto());
                this.comisionproductoService.crear(this.comisionProducto);
                FacesUtil.addMessageInfo("Se agreg\u00f3 la comision al producto: " + this.comisionProducto.getIdComision().getRazonComision());
            } else {
                this.comisionproductoService.modificar(this.comisionProducto);
                FacesUtil.addMessageInfo("Se modific\u00f3 la comision al producto: " + this.comisionProducto.getIdComision().getRazonComision());
            }
        } catch (Exception ex) {
            FacesUtil.addMessageError(null, "Ocurrí\u00f3 un error al actualizar la informaci\u00f3n");
        }

        super.reset();
        this.comisionProducto = new ComisionProducto();
        this.comisionproductos = this.comisionproductoService.obtenerTodos();
    }

    public ComisionProducto getComisionProducto() {
        return comisionProducto;
    }

    public void setComisionProducto(ComisionProducto comisionProducto) {
        this.comisionProducto = comisionProducto;
    }

    public Comision getComision() {
        return comision;
    }

    public void setComision(Comision comision) {
        this.comision = comision;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public ComisionProducto getComisionProductoSel() {
        return comisionProductoSel;
    }

    public void setComisionProductoSel(ComisionProducto comisionProductoSel) {
        this.comisionProductoSel = comisionProductoSel;
    }

    

   
    

}
