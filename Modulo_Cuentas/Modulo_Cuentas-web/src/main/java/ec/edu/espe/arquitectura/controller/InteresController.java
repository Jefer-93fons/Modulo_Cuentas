/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.controller;

import ec.edu.espe.arquitectura.model.Interes;
import ec.edu.espe.arquitectura.model.Periodo;
import ec.edu.espe.arquitectura.model.Producto;
import ec.edu.espe.arquitectura.service.InteresService;
import ec.edu.espe.arquitectura.service.PeriodoService;
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
public class InteresController extends BaseController implements Serializable {

    private List<Interes> intereses;

    private Interes interes;

    private Periodo tipo;

    private Interes interesSel;

    private List<Periodo> tiposPeriodo;

    @Inject
    private InteresService interesService;

    @Inject
    private PeriodoService tipoService;

    @PostConstruct
    public void init() {
        this.interes = new Interes();
        this.tipo = new Periodo();
        this.intereses = this.interesService.obtenerTodos();
        this.tiposPeriodo = this.tipoService.obtenerTodos();

    }

    public List<Interes> getIntereses() {
        return intereses;
    }

    public void setIntereses(List<Interes> intereses) {
        this.intereses = intereses;
    }

    @Override
    public void agregar() {
        this.interes = new Interes();
        super.agregar();
    }

    @Override
    public void modificar() {
        super.modificar();
        this.interes = new Interes();
        this.interes.setIdInteres(this.interesSel.getIdInteres());
        this.interes.setPorcentajeInteres(this.interesSel.getPorcentajeInteres());
        this.interes.setValorMax(this.interesSel.getValorMax());
        this.interes.setValorMin(this.interesSel.getValorMin());
        this.interes.setIdPeriodo(tipo);
        this.interes.setCodPeriodo(this.interes.getIdPeriodo().getIdPeriodo());
    }

    public void eliminar() {
        try {
            this.interesService.eliminar(this.interesSel.getIdInteres());
            this.intereses = this.interesService.obtenerTodos();
            FacesUtil.addMessageInfo("Se elimino el registro.");
            this.interesSel = null;
        } catch (Exception e) {
            FacesUtil.addMessageError(null, "No se puede eliminar el registro seleccionado. Verifique que no tenga informacion relacionada.");
        }
    }

    public void cancelar() {
        super.reset();
        this.interes = new Interes();
    }

    public void guardar() {
        try {
            if (this.enAgregar) {

                this.interes.setPorcentajeInteres(this.interes.getPorcentajeInteres());
                this.interes.setValorMax(this.interes.getValorMax());
                this.interes.setValorMin(this.interes.getValorMin());
                this.interes.setCodPeriodo(this.tipo.getIdPeriodo());
                this.interesService.crear(this.interes);
                FacesUtil.addMessageInfo("Se agreg\u00f3 el Interes: " + this.interes.getPorcentajeInteres());
            } else {
                this.interesService.modificar(this.interes);
                FacesUtil.addMessageInfo("Se modific\u00f3 el Producto: " + this.interes.getPorcentajeInteres());
            }
        } catch (Exception ex) {
            FacesUtil.addMessageError(null, "Ocurrí\u00f3 un error al actualizar la informaci\u00f3n");
        }
        //System.out.println("Tipo Producto: " + this.producto.getIdTipoProducto());
        super.reset();
        this.interes = new Interes();
        this.intereses = this.interesService.obtenerTodos();
    }

    public Interes getInteres() {
        return interes;
    }

    public void setInteres(Interes interes) {
        this.interes = interes;
    }

    public Periodo getTipo() {
        return tipo;
    }

    public void setTipo(Periodo tipo) {
        this.tipo = tipo;
    }

    public Interes getInteresSel() {
        return interesSel;
    }

    public void setInteresSel(Interes interesSel) {
        this.interesSel = interesSel;
    }

    public List<Periodo> getTiposPeriodo() {
        return tiposPeriodo;
    }

    public void setTiposPeriodo(List<Periodo> tiposPeriodo) {
        this.tiposPeriodo = tiposPeriodo;
    }

    
}
