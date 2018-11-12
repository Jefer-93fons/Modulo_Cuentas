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
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Juan
 */
@Named(value = "fechaController")
@ViewScoped
public class FechaController extends BaseController implements Serializable {

    private List<FechaTrabajo> fechas;
    private FechaTrabajo fecha;
    private FechaTrabajo fechaSel;

    private boolean visible;

    @Inject
    private FechaTrabajoService fechaTrabajoService;

    public FechaController() {
    }

    @PostConstruct
    public void init() {
        visible = false;
        this.fechas = fechaTrabajoService.obtenerTodos();
        this.fecha = new FechaTrabajo();
    }
    // <editor-fold defaultstate="collapsed" desc="Getters & Setters">

    public List<FechaTrabajo> getFechasTrabajo() {
        return fechas;
    }

    public List<FechaTrabajo> getFechas() {
        return fechas;
    }

    public void setFechas(List<FechaTrabajo> fechas) {
        this.fechas = fechas;
    }

    public FechaTrabajo getFecha() {
        return fecha;
    }

    public void setFecha(FechaTrabajo fecha) {
        this.fecha = fecha;
    }

    public FechaTrabajo getFechaSel() {
        return fechaSel;
    }

    public void setFechaSel(FechaTrabajo fechaSel) {
        this.fechaSel = fechaSel;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    // </editor-fold>
    @Override
    public void modificar() {
        super.modificar(); //To change body of generated methods, choose Tools | Templates.
        this.fecha = new FechaTrabajo();
        this.fecha.setIdFecha(fechaSel.getIdFecha());
        this.fecha.setFechaProceso(fechaSel.getFechaProceso());
    }

    @Override
    public void agregar() {
        this.fecha = new FechaTrabajo();
        super.agregar(); //To change body of generated methods, choose Tools | Templates.
    }

    public void pasar() {
        try {
            this.fechaSel = this.fechas.get(0);
            this.fechaSel = this.fechas.get(0);
            this.fechaSel.setFechaProceso(getFechaMin());
            this.fechaTrabajoService.modificar(this.fechaSel);
            this.fechas = this.fechaTrabajoService.obtenerTodos();
            this.fechaSel = null;
            FacesUtil.addMessageInfo("Se realizo el cierre del dia.");

        } catch (Exception e) {
            FacesUtil.addMessageError(null, "No se puede realizar el cierre del dia. Verifique que no tenga informacion relacionada.");
        }
    }

    public void cancelar() {
        super.reset();
        this.fecha = new FechaTrabajo();
    }

    public void guardar() {

        try {
            if (enAgregar) {
                this.fecha.setFechaProceso(this.fecha.getFechaProceso());
                this.fechaTrabajoService.crear(this.fecha);
                FacesUtil.addMessageInfo("Se agreg\u00f3 la nueva fecha: " + this.fecha.getFechaProceso());
            } else {
                ProcesosController controller=new ProcesosController();
                controller.CerrarDias();
                this.fechaTrabajoService.modificar(fecha);
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                FacesUtil.addMessageInfo("Se cambio la fecha del sistema a : " + format.format(this.fecha.getFechaProceso()));

            }
        } catch (Exception ex) {
            FacesUtil.addMessageError(null, "Ocurrí\u00f3 un error al actualizar la informaci\u00f3n");
        }

        super.reset();

        this.fecha = new FechaTrabajo();

        this.fechas = fechaTrabajoService.obtenerTodos();
    }

    public Date getFechaMin() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechas.get(0).getFechaProceso());
        calendar.add(calendar.DAY_OF_YEAR, 1);
        return calendar.getTime();
    }

    public String transformar(Date fecha) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        return format.format(fecha);
    }
}
