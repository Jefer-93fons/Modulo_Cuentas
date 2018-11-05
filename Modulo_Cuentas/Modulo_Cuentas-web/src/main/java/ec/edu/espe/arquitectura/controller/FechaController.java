/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.controller;

import ec.edu.espe.arquitectura.model.FechaTrabajo;
import ec.edu.espe.arquitectura.service.FechaTrabajoService;
import ec.edu.espe.arquitectura.web.util.FacesUtil;
import javax.inject.Named;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Juan
 */
@Named(value = "fechaController")
@ViewScoped
public class FechaController extends BaseController implements Serializable {

    private List<FechaTrabajo> fechasTrabajo;
    private FechaTrabajo fechaTrabajo;
    private FechaTrabajo fechaSel;
    private FechaTrabajo actualFecha;

    private boolean visible;

    @Inject
    private FechaTrabajoService fechaTrabajoService;

    public FechaController() {
    }

    @PostConstruct
    public void init() {
        visible=false;
        this.fechasTrabajo = fechaTrabajoService.obtenerTodos();
        this.fechaTrabajo = new FechaTrabajo();
        this.actualFecha = new FechaTrabajo();
        this.fechaSel=new FechaTrabajo();
        if (!fechasTrabajo.isEmpty()) {
            this.actualFecha = this.fechasTrabajo.get(0);
        } else {
            this.fechaTrabajo.setIdFecha(1);
            this.fechaTrabajo.setFechaProceso(new Date("02/03/2018"));
            crear();
        }
    }

    public Date getFechaMin() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(actualFecha.getFechaProceso());
        calendar.add(calendar.DAY_OF_YEAR, 1);
        return calendar.getTime();
    }

    public List<FechaTrabajo> getFechasTrabajo() {
        return fechasTrabajo;
    }

    // <editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public void setFechasTrabajo(List<FechaTrabajo> fechasTrabajo) {
        this.fechasTrabajo = fechasTrabajo;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

   

    public FechaTrabajo getFechaTrabajo() {
        return fechaTrabajo;
    }

    public void setFechaTrabajo(FechaTrabajo fechaTrabajo) {
        this.fechaTrabajo = fechaTrabajo;
    }

    public FechaTrabajo getActualFecha() {
        return actualFecha;
    }

    public void setActualFecha(FechaTrabajo actualFecha) {
        this.actualFecha = actualFecha;
    }

    public FechaTrabajo getFechaSel() {
        return fechaSel;
    }

    public void setFechaSel(FechaTrabajo fechaSel) {
        this.fechaSel = fechaSel;
    }
    // </editor-fold>
    
    
    public void mostrar() {
        this.visible = !this.visible;
        modificar();
    }
    


    @Override
    public void modificar() {
        super.modificar(); //To change body of generated methods, choose Tools | Templates.
        this.fechaTrabajo = new FechaTrabajo();
        this.fechaTrabajo.setIdFecha(1);
        this.fechaTrabajo.setFechaProceso(fechaSel.getFechaProceso());
    }

    @Override
    public void agregar() {
        this.fechaTrabajo = new FechaTrabajo();
        super.agregar(); //To change body of generated methods, choose Tools | Templates.
    }

    public void cancelar() {
        super.reset();
        this.fechaTrabajo = new FechaTrabajo();
    }

    public void crear() {
        try {
            agregar();
            this.fechaTrabajoService.crear(fechaTrabajo);
        } catch (Exception ex) {
            FacesUtil.addMessageError(null, "Ocurrí\u00f3 un error guardar la informaci\u00f3n");
        }
        super.reset();
        this.fechaTrabajo = new FechaTrabajo();
        this.fechasTrabajo = fechaTrabajoService.obtenerTodos();
        this.actualFecha = fechasTrabajo.get(0);
    }

    public void guardar() {

        try {
            this.fechaTrabajoService.modificar(fechaTrabajo);
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            FacesUtil.addMessageInfo("Se cambio la fecha del sistema a : " + format.format(this.fechaTrabajo.getFechaProceso()));
        } catch (Exception ex) {
            FacesUtil.addMessageError(null, "Ocurrí\u00f3 un error al actualizar la informaci\u00f3n");
        }
        super.reset();
        this.fechaTrabajo = new FechaTrabajo();
        this.fechasTrabajo = fechaTrabajoService.obtenerTodos();
        this.actualFecha = fechasTrabajo.get(0);
        FacesUtil.addMessageInfo("asda lkasjd"+this.actualFecha.getFechaProceso());
    }
    public void cerrarDia(){
        Date fechaNueva=getFechaMin();
        modificar();
        this.fechaTrabajo.setFechaProceso(fechaNueva);
        guardar();
        this.setActualFecha(fechaTrabajo);
    }
    public void cambiarFecha(){
        modificar();
        guardar();
    }
}
