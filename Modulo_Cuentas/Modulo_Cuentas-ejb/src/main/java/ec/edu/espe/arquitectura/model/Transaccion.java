/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Juan
 */
@Entity
@Table(name = "TRANSACCION", catalog = "", schema = "AGENTECUENTAS")

public class Transaccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID_TRANSACCION")
    private Integer idTransaccion;
    @Column(name = "VALOR_TRANSACCION")
    private BigDecimal valorTransaccion;
    @Column(name = "FECHA_TRANSACCION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaTransaccion;
    @JoinColumn(name = "ID_CUENTA", referencedColumnName = "ID_CUENTA")
    @ManyToOne
    private Cuenta idCuenta;
    @JoinColumn(name = "ID_TIPO_TRANSACCION", referencedColumnName = "ID_TIPO_TRANSACCION")
    @ManyToOne
    private TipoTransaccion idTipoTransaccion;

    public Transaccion() {
    }

    public Transaccion(Integer idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public Transaccion(Integer idTransaccion, BigDecimal valorTransaccion, Date fechaTransaccion) {
        this.idTransaccion = idTransaccion;
        this.valorTransaccion = valorTransaccion;
        this.fechaTransaccion = fechaTransaccion;
    }

    public Integer getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(Integer idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public BigDecimal getValorTransaccion() {
        return valorTransaccion;
    }

    public void setValorTransaccion(BigDecimal valorTransaccion) {
        this.valorTransaccion = valorTransaccion;
    }

    public Date getFechaTransaccion() {
        return fechaTransaccion;
    }

    public void setFechaTransaccion(Date fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }

    public Cuenta getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(Cuenta idCuenta) {
        this.idCuenta = idCuenta;
    }

    public TipoTransaccion getIdTipoTransaccion() {
        return idTipoTransaccion;
    }

    public void setIdTipoTransaccion(TipoTransaccion idTipoTransaccion) {
        this.idTipoTransaccion = idTipoTransaccion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTransaccion != null ? idTransaccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transaccion)) {
            return false;
        }
        Transaccion other = (Transaccion) object;
        if ((this.idTransaccion == null && other.idTransaccion != null) || (this.idTransaccion != null && !this.idTransaccion.equals(other.idTransaccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.arquitectura.model.Transaccion[ idTransaccion=" + idTransaccion + " ]";
    }
    
}
