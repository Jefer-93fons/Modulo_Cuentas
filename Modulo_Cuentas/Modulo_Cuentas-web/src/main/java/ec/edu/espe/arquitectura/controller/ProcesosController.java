/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.controller;

import ec.edu.espe.arquitectura.model.Cuenta;
import ec.edu.espe.arquitectura.model.Historico;
import ec.edu.espe.arquitectura.model.Interes;
import ec.edu.espe.arquitectura.model.InteresProducto;
import ec.edu.espe.arquitectura.model.Producto;
import ec.edu.espe.arquitectura.model.TipoTransaccion;
import ec.edu.espe.arquitectura.model.Transaccion;
import ec.edu.espe.arquitectura.service.CierreService;
import ec.edu.espe.arquitectura.service.CuentaService;
import ec.edu.espe.arquitectura.service.FechaTrabajoService;
import ec.edu.espe.arquitectura.service.HistoricoCuentaService;
import ec.edu.espe.arquitectura.service.InteresProductoService;
import ec.edu.espe.arquitectura.service.ProductoService;
import ec.edu.espe.arquitectura.service.TransaccionService;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Juan
 */
@Named(value = "procesosController")
@ViewScoped
public class ProcesosController implements Serializable {

    private List<Producto> productos;
    private List<InteresProducto> intereses;
    private List<Cuenta> cuentas;
    private List<Historico> historicos;
    private Transaccion transaccion;

    @Inject
    private ProductoService productoService;
    @Inject
    private InteresProductoService interesService;
    @Inject
    private CuentaService cuentaService;
    @Inject
    private TransaccionService transaccionService;
    @Inject
    private HistoricoCuentaService historicoCuentaService;
    @Inject
    private FechaTrabajoService fechaTrabajoService;

    @PostConstruct
    public void init() {
        this.productos = this.productoService.obtenerTodos();
        this.intereses = this.interesService.obtenerTodos();
        this.cuentas = this.cuentaService.obtenerTodos();
        this.historicos = this.historicoCuentaService.obtenerTodos();
    }

    public void CerrarDias() {
        this.cuentas = this.cuentaService.obtenerTodos();
        //Obtengo la fecha actual del sistema con la cual realizar los procesos.
        Date fechaActual = fechaTrabajoService.obtenerActual().getFechaProceso();
        //Obtener configuracion de modalidad de periodos de cobro de interes
        Map<Integer, Boolean> productPeriod = ProductPeriod();
        float Saldo;


        for (Cuenta auxCuenta : cuentas) {


            //System.out.println(auxCuenta.getIdCuenta() + "\t" + auxCuenta.getIdProducto().getNombreProducto());
            List<Historico> fechasDestacadas = obtFechasDestacadas(auxCuenta.getIdCuenta());
            //Revisar si la cuenta esta habilitada
            if (fechasDestacadas.get(1).getIdEstadoCuenta().getIdEstadoCuenta() == 1) {
                //Verificar modalidad de cobro configurada  FALSE: unico  -- TRUE: multiple
                if (!productPeriod.get(auxCuenta.getIdProducto().getIdProducto())) {
                    String valoresPeriodo = auxCuenta.getIdProducto().getInteresProductoList().get(0).getIdInteres().getIdPeriodo().getNombrePeriodo();
                    int perDias;
                    try {
                        perDias = Integer.parseInt(valoresPeriodo.split(" ")[0]);
                    } catch (Exception e) {
                        perDias = 0;
                    }
                    //Verificar que se debe pagar ahora su interes
                    if (isAbleToReceive(fechasDestacadas.get(0).getFechaHistorico(), fechaActual, perDias)) {
                        //obtenemos el interes que debria pagarse
                        Saldo = auxCuenta.getSaldoCuenta().floatValue();
                        float interestAmount = interestAmount(Saldo, auxCuenta.getIdProducto().getInteresProductoList());

                        System.out.println("GANO: " + interestAmount);
                        createInterestTrans(fechaActual, interestAmount, auxCuenta);

                    }

                }
            }

        }
    }

    /**
     * Este método se encarga de obtener las fechas importantes para el proceso
     * del interes
     *
     * @author Juan Andrade
     * @param idCuenta es el número de cuenta de la cual se desean obtener las
     * fechas
     * @return retorna una lista <Historico> con 2 fechas 1: la fecha de
     * creaci'on para el calculo del interes 2: El estado actual de la cuenta.
     */
    private List<Historico> obtFechasDestacadas(Integer idCuenta) {
        this.historicos = this.historicoCuentaService.porNumCuenta(idCuenta);
        List<Historico> fechasImportantes = new ArrayList<>();
        if (this.historicos.size() > 0) {
            fechasImportantes.add(this.historicos.get(0));
            fechasImportantes.add(this.historicos.get(this.historicos.size() - 1));
        }
        return fechasImportantes;
    }

    /**
     * Este método se encarga de obtener la configuracion respecto al periodo de
     * cobro de interes de los productos que puede ser multiple o unico.
     *
     * @author Juan Andrade
     * @return retorna un Map<Integer,Boolean> en la que el identificador es el
     * idProducto y el atributo booleano False: Periodo Unico TRUE: Multiple
     * periodo.
     */
    private Map<Integer, Boolean> ProductPeriod() {
        Map<Integer, Boolean> mapMultiPeriod = new HashMap<>();
        this.productos = this.productoService.obtenerTodos();
        for (Producto auxProducto : productos) {
            List<InteresProducto> interesProductoList = auxProducto.getInteresProductoList();
            Map<Integer, String> mapIntereses = new HashMap<>();
            for (InteresProducto auxInteresProducto : interesProductoList) {
                mapIntereses.put(auxInteresProducto.getIdInteres().getIdPeriodo().getIdPeriodo(), auxInteresProducto.getIdInteres().toString());
            }
            if (mapIntereses.size() == 1) {
                mapMultiPeriod.put(auxProducto.getIdProducto(), false);
            } else {
                mapMultiPeriod.put(auxProducto.getIdProducto(), true);
            }
        }
        return mapMultiPeriod;
    }

    /**
     * Este método se encarga de verificar si una cuenta puede realizar el cobro
     * de interes en la fecha actual dependiendo de la fecha de creacion de la
     * misma.
     *
     * @param fechaCreacion Fecha de creacion de la cuenta
     * @param fechaActual Fehcha actual para el claculo de interes
     * @param Periodo periodo de cobro de interes
     * @return TRUE: si esta habilitado a cobrar interes o FALSE en caso
     * contrario.
     */
    private Boolean isAbleToReceive(Date fechaCreacion, Date fechaActual, Integer Periodo) {
        int currentYear, currentMonth, currentDay;
        int createYear, createMonth, createDay;
        //Configuracion habitual por meses 30 equivale a un mensual
        Calendar calendar = Calendar.getInstance();
        //Periodos cerrados
        if ((Periodo % 30) == 0) {
            int cantidad = Periodo / 30;
            //Datos Fecha de creacion
            calendar.setTime(fechaCreacion);
            createYear = calendar.get(Calendar.YEAR);
            createMonth = calendar.get(Calendar.MONTH);
            createDay = calendar.get(calendar.DAY_OF_MONTH);
            //Datos fecha actual
            calendar.setTime(fechaActual);
            currentYear = calendar.get(Calendar.YEAR);
            currentMonth = calendar.get(Calendar.MONTH);
            currentDay = calendar.get(calendar.DAY_OF_MONTH);

            //Verificacion de habilitacion por periodo
            if ((Math.abs(createMonth - (currentMonth + (12 * (currentYear - createYear))))) % cantidad == 0) {
                //Verificar casos especiales 29,28,30
                if (createDay >= 29) {
                    calendar.add(Calendar.DATE, 1);
                    int newMonth = calendar.get(Calendar.MONTH);
                    if (newMonth != currentMonth) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    if (currentDay == createDay) {
                        return true;
                    } else {
                        return false;
                    }


                }
            } else {
                return false;
            }
        } else {//Periodos dias
            return false;
        }
    }

    /**
     * Este método se encarga de obtener el interes que gana.
     *
     * @param Base Saldo con lo que la cuenta dispone para el calculo del
     * interes
     * @param listSearch Lista con los intereses que podria ganar segun el saldo
     * @return valor del interes ganado.
     */
    private float interestAmount(float Base, List<InteresProducto> listSearch) {
        float Max, Min, interest = 0;
        for (InteresProducto auxIntPro : listSearch) {
            Interes interes = auxIntPro.getIdInteres();
            Max = interes.getValorMax().floatValue();
            Min = interes.getValorMin().floatValue();
            System.out.println(interes.getPorcentajeInteres() + "\t" + Min + "\t" + Max);

            if (Min <= Base && Max >= Base) {
                interest = ((float) interes.getIdInteres() / 100);
            }
        }
        return interest * Base;
    }

    private boolean createInterestTrans(Date date, float value, Cuenta account) {
        try {
            Transaccion transaccion = new Transaccion();
            transaccion.setFechaTransaccion(date);
            TipoTransaccion tipoTransaccion = new TipoTransaccion();
            tipoTransaccion.setIdTipoTransaccion(3);
            transaccion.setIdTipoTransaccion(tipoTransaccion);
            transaccion.setValorTransaccion(BigDecimal.valueOf(value));
            transaccion.setIdCuenta(account);
            transaccion.setIdTransaccion(0);
            transaccionService.crear(transaccion);
        } catch (Exception e) {
            System.err.println("Error al guardar la transaccion de interes.");
            return false;
        }
        return true;
    }
}
