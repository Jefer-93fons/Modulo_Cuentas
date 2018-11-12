/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.controller;

import ec.edu.espe.arquitectura.model.Cliente;
import ec.edu.espe.arquitectura.model.Cuenta;
import ec.edu.espe.arquitectura.model.Producto;
import ec.edu.espe.arquitectura.model.TipoProducto;
import ec.edu.espe.arquitectura.model.Transaccion;
import ec.edu.espe.arquitectura.service.ClienteService;
import ec.edu.espe.arquitectura.service.CuentaService;
import ec.edu.espe.arquitectura.service.ProductoService;
import ec.edu.espe.arquitectura.service.TipoProductoService;
import ec.edu.espe.arquitectura.service.TransaccionService;
import ec.edu.espe.arquitectura.web.util.FacesUtil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.swing.JOptionPane;

/**
 *
 * @author Jefferson
 */

@ManagedBean (name = "cuentaController")
@SessionScoped
public class CuentaController extends BaseController implements Serializable{
    private Cuenta cuenta;
    private Cliente cliente;
    private Producto producto;
    private TipoProducto tipo;
    private TipoProducto tipos;
    private Transaccion transaccion;    
    
    private String mes;
    private List<List<String>>mesesList;
    private String anio;
    private List<String> anios;
    private String identificacion;
    
    private boolean formCuenta;
    private BigDecimal[] interes;
    private BigDecimal[] saldo;
    
    private List<Cliente> clientes;
    private List<Cuenta> cuentas;
    private List<Cuenta> cuentasSelec;
    private List<Producto> productos;
    private List<TipoProducto> tiposProducto;
    private List<Transaccion> transacciones;
    private List<Transaccion> transaccionesSelec;
    
    @Inject
    private CuentaService cuentaService;
    
    @Inject
    private ClienteService clienteService;
    
    @Inject
    private ProductoService productoService;
    
    @Inject
    private TipoProductoService tipoProductoService;
    
    @Inject
    private TransaccionService transaccionService;

    /**
     * Creates a new instance of CuentaController
     */
    @PostConstruct
    public void init(){
        cuenta = new Cuenta();
        producto = new Producto();
        tipo = new TipoProducto();
        transaccion = new Transaccion();
        clientes = clienteService.obtenerTodos();
        cuentas = cuentaService.obtenerTodos();
        tiposProducto = tipoProductoService.obtenerTodos();
        productos = productoService.obtenerTodos();
        transacciones = transaccionService.obtenerTodos();
        
        mesesList = new ArrayList<List<String>>();
        anios = new ArrayList<String>();
        cuentasSelec = new ArrayList<Cuenta>();
        transaccionesSelec = new ArrayList<Transaccion>();
        
        String meses[] = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        
        for(int i=1; i<13; i++){
            List<String> mesPosicion = new ArrayList<String>();
            if(i<10){
                mesPosicion.add("0"+i);
            }else{
                mesPosicion.add(String.valueOf(i));
            }
            mesPosicion.add(meses[i-1]);
            mesesList.add(mesPosicion);
        }
    
        anios.add("2016");
        anios.add("2017");
        anios.add("2018");
            
    }
    
    public CuentaController() {
    }
    
    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public List<List<String>> getMesesList() {
        return mesesList;
    }

    public void setMesesList(List<List<String>> mesesList) {
        this.mesesList = mesesList;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public List<String> getAnios() {
        return anios;
    }

    public void setAnios(List<String> anios) {
        this.anios = anios;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public boolean isFormCuenta() {
        return formCuenta;
    }

    public void setFormCuenta(boolean formCuenta) {
        this.formCuenta = formCuenta;
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public List<TipoProducto> getTiposProducto() {
        return tiposProducto;
    }

    public void setTiposProducto(List<TipoProducto> tiposProducto) {
        this.tiposProducto = tiposProducto;
    }

    public TipoProducto getTipo() {
        return tipo;
    }

    public void setTipo(TipoProducto tipo) {
        this.tipo = tipo;
    }
    
    public List<Cuenta> getCuentasSelec() {
        return cuentasSelec;
    }

    public void setCuentasSelec(List<Cuenta> cuentasSelec) {
        this.cuentasSelec = cuentasSelec;
    }
     
    public TipoProducto getTipos() {
        return tipos;
    }

    public void setTipos(TipoProducto tipos) {
        this.tipos = tipos;
    }

    public Transaccion getTransaccion() {
        return transaccion;
    }

    public void setTransaccion(Transaccion transaccion) {
        this.transaccion = transaccion;
    }

    public List<Transaccion> getTransacciones() {
        return transacciones;
    }

    public void setTransacciones(List<Transaccion> transacciones) {
        this.transacciones = transacciones;
    }

    public List<Transaccion> getTransaccionesSelec() {
        return transaccionesSelec;
    }

    public void setTransaccionesSelec(List<Transaccion> transaccionesSelec) {
        this.transaccionesSelec = transaccionesSelec;
    }

    public BigDecimal[] getInteres() {
        return interes;
    }

    public void setInteres(BigDecimal[] interes) {
        this.interes = interes;
    }

    public BigDecimal[] getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal[] saldo) {
        this.saldo = saldo;
    }

    @Override
    public void buscar(){
        formCuenta = buscarCliente (); 
        if(cliente !=null){
            super.buscar();
            
        }else{
            FacesUtil.addMessageError(null,"No existe el cliente !");
        }
        
    }
    
    public void filtrarProductos(){
        List<Producto> auxproductos  = new ArrayList<Producto>();
        
        for (Producto pro : productos){
          if(tipo.getIdTipoProducto() == pro.getIdTipoProducto().getIdTipoProducto()){
              auxproductos.add(pro);
          }
        }
        productos = auxproductos;
    }
    
    
    public void buscarCuenta() throws ParseException {
        if(mes!=null && anio!=null){
            buscarCliente();

            SimpleDateFormat inputFecha = new SimpleDateFormat("MM/yyyy");  
            Date fechabusqueda = inputFecha.parse(this.mes+"/"+this.anio);
            Date fechainput;
            int[] numcuenta = new int[10];
            interes = new BigDecimal[10];
            saldo = new BigDecimal[10];
            BigDecimal[] valortotaltransc = new BigDecimal[10];
            BigDecimal[] valornestranscbusq = new BigDecimal[10];

           // Arrays.fill(valortotaltransc, BigDecimal.ZERO);
           // Arrays.fill(valornestranscbusq, BigDecimal.ZERO);

            int num = 0;

            for (Cuenta cunt : cuentas){
                if (cunt.getIdCliente().getCodCliente().equals(identificacion)) { 
                    cuentasSelec.add(cunt);
                    numcuenta[num] = cunt.getIdCuenta();
                    num ++;
                }
            }

            for (int i=0; i<numcuenta.length; i++){
                if(numcuenta[i]!=0){
                    valortotaltransc[i] = BigDecimal.ZERO;
                    valornestranscbusq[i] = BigDecimal.ZERO;
                    interes[i] = BigDecimal.ZERO;
                    saldo[i] = BigDecimal.ZERO;
                }
            }

            int test=0;
            for (Transaccion trans : transacciones){
                for (int i=0; i<numcuenta.length; i++){
                    if(trans.getIdCuenta().getIdCuenta() == numcuenta[i]){
                        transaccionesSelec.add(trans);
                        fechainput =  inputFecha.parse(inputFecha.format(trans.getFechaTransaccion()));
                         if(fechainput.equals(fechabusqueda) || fechainput.before(fechabusqueda)){
                           if(trans.getIdTipoTransaccion().getIdTipoTransaccion() == 1){
                                valortotaltransc[i] = valortotaltransc[i].add(trans.getValorTransaccion());
                            }
                            if(trans.getIdTipoTransaccion().getIdTipoTransaccion() == 2 || trans.getIdTipoTransaccion().getIdTipoTransaccion() == 3 || trans.getIdTipoTransaccion().getIdTipoTransaccion() == 4){
                                valortotaltransc[i] = valortotaltransc[i].subtract(trans.getValorTransaccion());
                            }
                            if(trans.getIdTipoTransaccion().getIdTipoTransaccion() == 3){
                                //interes[i] = interes[i].add(trans.getValorTransaccion());
                            } 
                        }     
                    }
                }
            }

            for (Transaccion trans : transaccionesSelec){
                for (int i=0; i<numcuenta.length; i++){
                    if(trans.getIdCuenta().getIdCuenta() == numcuenta[i]){
                        fechainput =  inputFecha.parse(inputFecha.format(trans.getFechaTransaccion()));
                        if(fechainput.equals(fechabusqueda)){
    //                        System.out.println("Tipo: " + trans.getIdTipoTransaccion().getIdTipoTransaccion());
    //                        if(trans.getIdTipoTransaccion().getIdTipoTransaccion() == 1){
    //                            valornestranscbusq[i] = valornestranscbusq[i].add(trans.getValorTransaccion());
    //                        }
    //                        if(trans.getIdTipoTransaccion().getIdTipoTransaccion() == 2 || trans.getIdTipoTransaccion().getIdTipoTransaccion() == 3 || trans.getIdTipoTransaccion().getIdTipoTransaccion() == 4){
    //                            valornestranscbusq[i] = valornestranscbusq[i].subtract(trans.getValorTransaccion());
    //                        }
                            if(trans.getIdTipoTransaccion().getIdTipoTransaccion() == 3){
                                interes[i] = interes[i].add(trans.getValorTransaccion());
                            }
                        }
                    }
                }
            }
            saldo = valortotaltransc;

         //   System.out.println(valortotaltransc[0]);
         //   System.out.println(valortotaltransc[1]);
        //    System.out.println(valornestranscbusq[0]);
        //    System.out.println(valornestranscbusq[1]);

        }else{
            FacesUtil.addMessageError(null,"Campos Incompletos!");
        }
    }
     
    public boolean buscarCliente(){
        boolean flag = false;
        for (Cliente auxCliente : clientes) {
            if (auxCliente.getCodCliente().equals(identificacion)) {
                this.cliente = auxCliente;
                flag = true;
            }
        }
        
        return flag;

    }
    
    public void guardar() {
        buscarCliente();
        System.out.println(this.cliente);
        System.out.println(producto);
        try {
            if (buscarCliente()) {
                this.cuenta.setIdCuenta(0);
                this.cuenta.setIdCliente(cliente);
                this.cuenta.setIdProducto(producto);
                this.cuenta.setSaldoCuenta(BigDecimal.valueOf(200));
                this.cuentaService.crear(this.cuenta);
                FacesUtil.addMessageInfo("Se agreg\u00f3 Correctamente la Cuenta ");
            } 
        } catch (Exception ex) {
        }

        super.reset();
        this.cuenta = new Cuenta();
        this.cliente = new Cliente();
        this.tipo = new TipoProducto();
        this.producto = new Producto();
        this.cuentas = this.cuentaService.obtenerTodos();
    }
    
}
