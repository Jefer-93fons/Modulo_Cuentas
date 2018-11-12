/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.controller;

import ec.edu.espe.arquitectura.model.Cuenta;
import ec.edu.espe.arquitectura.model.Interes;
import ec.edu.espe.arquitectura.model.InteresProducto;
import ec.edu.espe.arquitectura.model.Producto;
import ec.edu.espe.arquitectura.model.TipoTransaccion;
import ec.edu.espe.arquitectura.model.Transaccion;
import ec.edu.espe.arquitectura.service.CuentaService;
import ec.edu.espe.arquitectura.service.InteresProductoService;
import ec.edu.espe.arquitectura.service.ProductoService;
import ec.edu.espe.arquitectura.service.TransaccionService;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
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
    private Transaccion transaccion;

    @Inject
    private ProductoService productoService;
    @Inject
    private InteresProductoService interesServiceJ;
    @Inject
    private CuentaService cuentaService;
    @Inject
    private TransaccionService transaccionService;

    @PostConstruct
    public void init() {
        this.productos = this.productoService.obtenerTodos();
        this.intereses = this.interesServiceJ.obtenerTodos();
        this.cuentas = this.cuentaService.obtenerTodos();
    }

    public void CerrarDias() {

        float Max, Min, Saldo;
//        for (InteresProducto inter : intereses) {
//            Max = inter.getIdInteres().getValorMax();
//            Min = inter.getIdInteres().getValorMin();
//            System.out.println("INTERES " + inter.getIdInteres().getPorcentajeInteres() + "\t" + Min + "  " + Max + "\tProducto: " + inter.getIdProducto().getNombreProducto());
//        }

        for (Cuenta auxCuenta : cuentas) {
            System.out.println(auxCuenta.getIdCuenta() + "\t" + auxCuenta.getIdProducto().getNombreProducto());
            Saldo = auxCuenta.getSaldoCuenta().floatValue();
            for (InteresProducto auxIntPro : auxCuenta.getIdProducto().getInteresProductoList()) {
                Interes interes = auxIntPro.getIdInteres();
                Max = interes.getValorMax().floatValue();
                Min = interes.getValorMin().floatValue();
                System.out.println(interes.getPorcentajeInteres() + "\t" + Min + "\t" + Max);

                if (Min <= Saldo && Max >= Saldo) {
                    float ganancia = 0;
                    ganancia = Saldo * ((float) interes.getIdInteres().intValueExact() / 100);
                    System.out.println("GANO: " + ganancia);
                    Transaccion transaccion = new Transaccion();
                    Date date = new Date(2018, 11, 8);
                    transaccion.setFechaTransaccion(date);
                    TipoTransaccion tipoTransaccion = new TipoTransaccion();
                    tipoTransaccion.setIdTipoTransaccion(3);
                    transaccion.setIdTipoTransaccion(tipoTransaccion);
                    transaccion.setValorTransaccion(BigDecimal.valueOf(ganancia));
                    transaccion.setIdCuenta(auxCuenta);
                    transaccion.setIdTransaccion(6);
                    transaccionService.crear(transaccion);

                }
            }
        }

    }
}
