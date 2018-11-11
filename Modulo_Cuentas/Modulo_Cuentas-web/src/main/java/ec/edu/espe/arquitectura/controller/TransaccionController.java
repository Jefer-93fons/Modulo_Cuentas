/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.controller;

import ec.edu.espe.arquitectura.model.Transaccion;
import ec.edu.espe.arquitectura.model.TipoTransaccion;
import ec.edu.espe.arquitectura.service.TransaccionService;
import ec.edu.espe.arquitectura.service.TipoTransaccionService;
import ec.edu.espe.arquitectura.web.util.FacesUtil;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;


/**
 *
 * @author Victoria
 */

@Named
@ViewScoped
public class TransaccionController extends BaseController implements Serializable{
    private List<Transaccion> transacciones;
    private List<TipoTransaccion> tiposTransaccion;
    private Transaccion transaccionSel;
    private TipoTransaccion tipo;
    private Transaccion transaccion;
    
    @Inject
    
    private TransaccionService transaccionService;
    
    @Inject
     private TipoTransaccionService tipoTransaccionService;
    
    @PostConstruct
    public void init(){
        this.transaccion= new Transaccion();
        this.transacciones=this.transaccionService.obtenerTodos();
        this.tipo=new TipoTransaccion();
        this.tiposTransaccion=this.tipoTransaccionService.obtenerTodos();
        
    }
    
    public List<TipoTransaccion> getTiposTransaccion() {
        return tiposTransaccion;
    }

    public List<Transaccion> getTransacciones() {
        return transacciones;
    }

    @Override
    public void agregar() {
        this.transaccion = new Transaccion();
        super.agregar();
    }

    @Override
    public void modificar() {
        super.modificar();
        this.transaccion = new Transaccion();
        this.transaccion.setIdTransaccion(this.transaccionSel.getIdTransaccion ());
        this.transaccion.setIdTransaccion(this.transaccionSel.getIdTransaccion());
        
       // this.transaccion.setIdTipoTransaccion(this.tipo.getNombreTipoTransaccion());
    }

    public void eliminar() {
        try {
            this.transaccionService.eliminar(this.transaccionSel.getIdTransaccion ());
            this.transacciones = this.transaccionService.obtenerTodos();
            FacesUtil.addMessageInfo("Se elimino el registro.");
            this.transaccionSel = null;
        } catch (Exception e) {
            FacesUtil.addMessageError(null, "No se puede eliminar el registro seleccionado. Verifique que no tenga informacion relacionada.");
        }
    }

    public void cancelar() {
        super.reset();
        this.transaccion = new Transaccion();
    }

    public void guardar() {
        try {
            if (this.enAgregar) {

                this.transaccion.setIdTipoTransaccion(this.transaccion.getIdTipoTransaccion());
                
              //  this.transaccion.setIdTransaccion(this.tipo.getTransaccionList());
                this.transaccionService.crear(this.transaccion);
                FacesUtil.addMessageInfo("Se agreg\u00f3 el Transaccion: " + this.transaccion.getIdTipoTransaccion());
            } else {
                this.transaccionService.modificar(this.transaccion);
                FacesUtil.addMessageInfo("Se modific\u00f3 el Transaccion: " + this.transaccion.getIdTipoTransaccion());
            }
        } catch (Exception ex) {
            FacesUtil.addMessageError(null, "Ocurrí\u00f3 un error al actualizar la informaci\u00f3n");
        }
        System.out.println("Tipo Transaccion: " + this.transaccion.getIdTipoTransaccion ());
        super.reset();
        this.transaccion = new Transaccion();
        this.transacciones = this.transaccionService.obtenerTodos();
    }

    public Transaccion getTransaccion () {
        return transaccion;
    }

    public void setTransaccion (Transaccion transaccion) {
        this.transaccion = transaccion;
    }

    public Transaccion getTransaccionSel() {
        return transaccionSel;
    }

    public void setTransaccionSel(Transaccion transaccionSel) {
        this.transaccionSel = transaccionSel;
    }

    public TransaccionService getTransaccionService() {
        return transaccionService;
    }

    public void setTransaccionService(TransaccionService transaccionService) {
        this.transaccionService = transaccionService;
    }

    public TipoTransaccionService getTipoTransaccionService() {
        return tipoTransaccionService;
    }

    public void setTipoTransaccionService(TipoTransaccionService tipoTransaccionService) {
        this.tipoTransaccionService = tipoTransaccionService;
    }

    public TipoTransaccion getTipo() {
        return tipo;
    }

    public void setTipo(TipoTransaccion tipo) {
        this.tipo = tipo;
    }

    
}
