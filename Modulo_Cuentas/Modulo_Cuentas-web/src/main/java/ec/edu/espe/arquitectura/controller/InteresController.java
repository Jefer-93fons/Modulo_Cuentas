/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.controller;

import ec.edu.espe.arquitectura.model.Interes;
import ec.edu.espe.arquitectura.model.Periodo;
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

    private Periodo periodo;

    private Interes interesSel;

    private List<Periodo> periodos;

    @Inject
    private InteresService interesService;

    @Inject
    private PeriodoService periodoService;

    @PostConstruct
    public void init() {
        this.interes = new Interes();
        this.periodo = new Periodo();
        this.intereses = this.interesService.obtenerTodos();
        this.periodos = this.periodoService.obtenerTodos();

    }

    public List<Interes> getIntereses() {
        return intereses;
    }

    public void setIntereses(List<Interes> intereses) {
        this.intereses = intereses;
    }

    public List<Periodo> getPeriodos() {
        return periodos;
    }

    public void setPeriodos(List<Periodo> periodos) {
        this.periodos = periodos;
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
        this.interes.setPorcentajeInteres(this.interesSel.getPorcentajeInteres());
        this.interes.setValorMax(this.interesSel.getValorMax());
        this.interes.setValorMin(this.interesSel.getValorMin());
        this.interes.setIdPeriodo(this.periodo);

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
                //this.interes.setIdProducto(0);
                this.interes.setPorcentajeInteres(this.interesSel.getPorcentajeInteres());
                this.interes.setValorMax(this.interesSel.getValorMax());
                this.interes.setValorMin(this.interesSel.getValorMin());
                this.interes.setIdPeriodo(this.periodo);
                this.interesService.crear(this.interes);
                FacesUtil.addMessageInfo("Se agreg\u00f3 el Interes con Porcentaje: " + this.interes.getPorcentajeInteres());
            } else {
                this.interesService.modificar(this.interes);
                FacesUtil.addMessageInfo("Se modific\u00f3 el Interes con Porcentaje: " + this.interes.getPorcentajeInteres());
            }
        } catch (Exception ex) {
            FacesUtil.addMessageError(null, "Ocurrí\u00f3 un error al actualizar la informaci\u00f3n");
        }

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

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    public Interes getInteresSel() {
        return interesSel;
    }

    public void setInteresSel(Interes interesSel) {
        this.interesSel = interesSel;
    }

    

}
