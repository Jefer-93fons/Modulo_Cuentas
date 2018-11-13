/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.controller;

import ec.edu.espe.arquitectura.model.Interes;
import ec.edu.espe.arquitectura.model.InteresProducto;
import ec.edu.espe.arquitectura.model.Producto;
import ec.edu.espe.arquitectura.service.InteresProductoService1;
import ec.edu.espe.arquitectura.service.InteresService;
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
public class InteresProductoController extends BaseController implements Serializable {

    private List<InteresProducto> interesesproductos;
    
    private List<Interes> intereses;
    
    private InteresProducto interesProducto;
    
    private Interes interes;

    private Producto producto;

    private InteresProducto interesProductoSel;

    private List<Producto> productos;

    @Inject
    private InteresService interesService;

    @Inject
    private ProductoService productoService;
    
    @Inject
    private InteresProductoService1 interesproductoService;

    @PostConstruct
    public void init() {
        this.interesProducto = new InteresProducto();
        this.interes = new Interes();
        this.producto = new Producto();
        this.intereses = this.interesService.obtenerTodos();
        this.productos = this.productoService.obtenerTodos();
        this.interesesproductos = this.interesproductoService.obtenerTodos();

    }

    public List<Interes> getIntereses() {
        return intereses;
    }

    public void setIntereses(List<Interes> intereses) {
        this.intereses = intereses;
    }

    public List<InteresProducto> getInteresesproductos() {
        return interesesproductos;
    }

    public void setInteresesproductos(List<InteresProducto> interesesproductos) {
        this.interesesproductos = interesesproductos;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    

    @Override
    public void agregar() {
        this.interesProducto = new InteresProducto();
        super.agregar();
    }

    @Override
    public void modificar() {
        super.modificar();
        this.interesProducto = new InteresProducto();
        this.interesProducto.setIdInteresProducto(this.interesProductoSel.getIdInteresProducto());
        this.interesProducto.setIdInteres(this.interes);
        this.interesProducto.setIdProducto(this.producto);

    }

    public void eliminar() {
        try {
            this.interesproductoService.eliminar(this.interesProductoSel.getIdInteresProducto());
            this.interesesproductos = this.interesproductoService.obtenerTodos();
            FacesUtil.addMessageInfo("Se elimino el registro.");
            this.interesProductoSel = null;
        } catch (Exception e) {
            FacesUtil.addMessageError(null, "No se puede eliminar el registro seleccionado. Verifique que no tenga informacion relacionada.");
        }
    }

    public void cancelar() {
        super.reset();
        this.interesProducto = new InteresProducto();
    }

    public void guardar() {
        try {
            if (this.enAgregar) {
                this.interesProducto.setIdInteresProducto(0);
                this.interesProducto.setIdInteres(this.interes);
                this.interesProducto.setIdProducto(this.producto);
                this.interesproductoService.crear(this.interesProducto);
                FacesUtil.addMessageInfo("Se agreg\u00f3 el Interes al producto: " + this.interesProducto.getIdProducto().getNombreProducto());
            } else {
                this.interesproductoService.modificar(this.interesProducto);
                FacesUtil.addMessageInfo("Se modific\u00f3 el Interes al producto: " + this.interesProducto.getIdProducto().getNombreProducto());
            }
        } catch (Exception ex) {
            FacesUtil.addMessageError(null, "Ocurrí\u00f3 un error al actualizar la informaci\u00f3n");
        }

        super.reset();
        this.interesProducto = new InteresProducto();
        this.interesesproductos = this.interesproductoService.obtenerTodos();
    }

    public Interes getInteres() {
        return interes;
    }

    public void setInteres(Interes interes) {
        this.interes = interes;
    }

    public InteresProducto getInteresProducto() {
        return interesProducto;
    }

    public void setInteresProducto(InteresProducto interesProducto) {
        this.interesProducto = interesProducto;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public InteresProducto getInteresProductoSel() {
        return interesProductoSel;
    }

    public void setInteresProductoSel(InteresProducto interesProductoSel) {
        this.interesProductoSel = interesProductoSel;
    }

   
    

}
