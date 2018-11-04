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
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author Juan
 */
@Named(value = "fechaController")
@SessionScoped
public class FechaController extends BaseController implements Serializable {

    private List<FechaTrabajo> fechasTrabajo;
    private FechaTrabajo fechaTrabajo;
    private FechaTrabajo nuevaFecha;
    private FechaTrabajo actualFecha;

    @Inject
    private FechaTrabajoService fechaTrabajoService;

    public FechaController() {
    }
    
    @PostConstruct
    public void init(){
        this.fechasTrabajo=fechaTrabajoService.obtenerTodos();
        this.fechaTrabajo=new FechaTrabajo();
        this.nuevaFecha=new FechaTrabajo();
        if (!fechasTrabajo.isEmpty()) {
            this.actualFecha=this.fechasTrabajo.get(0);
        }else{
            this.actualFecha.setIdFecha(13);
            this.actualFecha.setFechaProceso(new Date());
        }
    }
    
    public Date getFechaMin(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(actualFecha.getFechaProceso());
        calendar.add(calendar.DAY_OF_YEAR, 1);
        return calendar.getTime();
    }
    public FechaTrabajo getActualFecha() {
        return actualFecha;
    }

    public void setActualFecha(FechaTrabajo actualFecha) {
        this.actualFecha = actualFecha;
    }



    public List<FechaTrabajo> getFechasTrabajo() {
        return fechasTrabajo;
    }

    public FechaTrabajo getFechaTrabajo() {
        return fechaTrabajo;
    }

    public void setFechaTrabajo(FechaTrabajo fechaTrabajo) {
        this.fechaTrabajo = fechaTrabajo;
    }

    public FechaTrabajo getNuevaFecha() {
        return nuevaFecha;
    }

    public void setNuevaFecha(FechaTrabajo nuevaFecha) {
        this.nuevaFecha = nuevaFecha;
    }





    @Override
    public void modificar() {
        super.modificar(); //To change body of generated methods, choose Tools | Templates.
        this.fechaTrabajo=new FechaTrabajo();
        this.fechaTrabajo.setIdFecha(1);
        this.fechaTrabajo.setFechaProceso(nuevaFecha.getFechaProceso());
    }

    @Override
    public void agregar() {
        this.fechaTrabajo=new FechaTrabajo();
        super.agregar(); //To change body of generated methods, choose Tools | Templates.
    }
     public void cancelar() {
        super.reset();
        this.fechaTrabajo=new FechaTrabajo();
    }

    public void guardar() {
        try {
            this.fechaTrabajoService.modificar(fechaTrabajo);
            FacesUtil.addMessageInfo("Se cambio la fecha del sistema a : " + this.fechaTrabajo.getFechaProceso());
        } catch (Exception ex) {
            FacesUtil.addMessageError(null, "Ocurrí\u00f3 un error al actualizar la informaci\u00f3n");
        }
        super.reset();
        this.fechaTrabajo=new FechaTrabajo();
        this.fechasTrabajo=fechaTrabajoService.obtenerTodos();
    }   
    public void mensaje(){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Mensaje Boton",null));
    }
}
