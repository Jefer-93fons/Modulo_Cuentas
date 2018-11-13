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
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class HistoricoController extends BaseController implements Serializable {

    private Date date3;
    
    private Integer n;
    
    private List<Producto> productos;

    private List<EstadoProducto> estados;

    private List<HistoricoProducto> historicos;

    private Producto producto;

    private TipoProducto tipo;

    private EstadoProducto estado;

    private HistoricoProducto historico;

    private HistoricoProducto historicoproductoSel;

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
        this.producto = new Producto();
        this.tipo = new TipoProducto();
        this.estado = new EstadoProducto();
        this.historico = new HistoricoProducto();
        this.productos = this.productoService.obtenerTodos();
        this.tiposProducto = this.tipoProductoService.obtenerTodos();
        this.estados = this.estadoProductoService.obtenerTodos();
        this.historicos = this.historicoProductoService.obtenerTodos();
        this.n = 200;

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

    @Override
    public void agregar() {
        this.historico = new HistoricoProducto();
        super.agregar();
    }

    @Override
    public void modificar() {
        super.modificar();
        this.historico = new HistoricoProducto();
        this.historico.setIdHistoricoProducto(this.historicoproductoSel.getIdHistoricoProducto());
        this.historico.setIdEstadoProducto(this.estado);
        this.historico.setIdProducto(this.producto);
        this.historico.setFechaVigencia(this.historicoproductoSel.getFechaVigencia());

    }

    public void eliminar() {
        try {
            this.historicoProductoService.eliminar(this.historicoproductoSel.getIdHistoricoProducto());
            this.productos = this.productoService.obtenerTodos();
            FacesUtil.addMessageInfo("Se elimino el registro.");
            this.historicoproductoSel = null;
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

                //para la tabla estado
                int numero = (int) (Math.random() * n) + 1;
                this.historico.setIdHistoricoProducto(numero);
                this.historico.setIdProducto(this.producto);
                this.historico.setIdEstadoProducto(this.estado);

                this.historico.setFechaVigencia(date3);
                this.historicoProductoService.crear(this.historico);

                FacesUtil.addMessageInfo("Se agreg\u00f3 el Estado al producto: " + this.historico.getIdProducto().getNombreProducto());
            } else {
                this.historicoProductoService.modificar(this.historico);
                FacesUtil.addMessageInfo("Se modific\u00f3 el Estado al Producto: " + this.historico.getIdProducto().getNombreProducto());
            }
        } catch (Exception ex) {
            FacesUtil.addMessageError(null, "Ocurrí\u00f3 un error al actualizar la informaci\u00f3n");
        }

        super.reset();
        this.historico = new HistoricoProducto();
        this.historicos = this.historicoProductoService.obtenerTodos();
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public HistoricoProducto getHistoricoproductoSel() {
        return historicoproductoSel;
    }

    public void setHistoricoproductoSel(HistoricoProducto historicoproductoSel) {
        this.historicoproductoSel = historicoproductoSel;
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
    
     public Date getDate3() {
        return date3;
    }
 
    public void setDate3(Date date3) {
        this.date3 = date3;
    }

}
