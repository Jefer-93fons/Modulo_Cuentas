/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.controller;

import ec.edu.espe.arquitectura.model.EstadoProducto;
import ec.edu.espe.arquitectura.model.HistoricoProducto;
import ec.edu.espe.arquitectura.model.Producto;
import ec.edu.espe.arquitectura.model.TipoProducto;
import ec.edu.espe.arquitectura.service.EstadoProductoService;
import ec.edu.espe.arquitectura.service.HistoricoProductoService;
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

    private String filtro;
    private Integer tipoBusqueda;
    private boolean enBusquedaPorTipo;
    
    private List<Producto> productos;
    
    private List<EstadoProducto> estados;
    
    private List<HistoricoProducto> historicos;

    private Producto producto;

    private TipoProducto tipo;
    
    private EstadoProducto estado;
    
    private HistoricoProducto historico;

    private Producto productoSel;

    private List<TipoProducto> tiposProducto;

    private String fecha;
    @Inject
    private ProductoService productoService;

    @Inject
    private TipoProductoService tipoProductoService;
    
    @Inject
    private EstadoProductoService estadoProductoService;
    
    @Inject
    private HistoricoProductoService historicoProductoService;

    @PostConstruct
    public void init() {
        this.filtro = "TIP";
        this.enBusquedaPorTipo = true;
        this.producto = new Producto();
        this.tipo = new TipoProducto();
        this.estado = new EstadoProducto();
        this.historico = new HistoricoProducto();
        this.productos = this.productoService.obtenerTodos();
        this.tiposProducto = this.tipoProductoService.obtenerTodos();
        this.estados = this.estadoProductoService.obtenerTodos();
        this.historicos = this.historicoProductoService.obtenerTodos();

    }

    public List<TipoProducto> getTiposProducto() {
        return tiposProducto;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public List<EstadoProducto> getEstados() {
        return estados;
    }

    public List<HistoricoProducto> getHistoricos() {
        return historicos;
    }
    
   

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public Integer getTipoBusqueda() {
        return tipoBusqueda;
    }

    public void setTipoBusqueda(Integer tipoBusqueda) {
        this.tipoBusqueda = tipoBusqueda;
    }

    public boolean isEnBusquedaPorTipo() {
        return enBusquedaPorTipo;
    }

    public void setEnBusquedaPorTipo(boolean enBusquedaPorTipo) {
        this.enBusquedaPorTipo = enBusquedaPorTipo;
    }
    
     public void buscar() {
        if (this.enBusquedaPorTipo) {
            this.productos = this.productoService.buscarPorTipo(this.tipoBusqueda);}
        
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
                //para la tabla producto
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

    public EstadoProducto getEstado() {
        return estado;
    }

    public void setEstado(EstadoProducto estado) {
        this.estado = estado;
    }

    public HistoricoProducto getHistorico() {
        return historico;
    }

    public void setHistorico(HistoricoProducto historico) {
        this.historico = historico;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    

}
