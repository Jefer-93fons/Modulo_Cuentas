/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Juan
 */
@Entity
@Table(name = "TIPO_TRANSACCION", catalog = "", schema = "AGENTECUENTAS")

public class TipoTransaccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID_TIPO_TRANSACCION")
    private Integer idTipoTransaccion;
    @Column(name = "NOMBRE_TIPO_TRANSACCION")
    private String nombreTipoTransaccion;
    @OneToMany(mappedBy = "idTipoTransaccion")
    private List<Transaccion> transaccionList;

    public TipoTransaccion() {
    }

    public TipoTransaccion(Integer idTipoTransaccion) {
        this.idTipoTransaccion = idTipoTransaccion;
    }

    public TipoTransaccion(Integer idTipoTransaccion, String nombreTipoTransaccion) {
        this.idTipoTransaccion = idTipoTransaccion;
        this.nombreTipoTransaccion = nombreTipoTransaccion;
    }

    public Integer getIdTipoTransaccion() {
        return idTipoTransaccion;
    }

    public void setIdTipoTransaccion(Integer idTipoTransaccion) {
        this.idTipoTransaccion = idTipoTransaccion;
    }

    public String getNombreTipoTransaccion() {
        return nombreTipoTransaccion;
    }

    public void setNombreTipoTransaccion(String nombreTipoTransaccion) {
        this.nombreTipoTransaccion = nombreTipoTransaccion;
    }

    @XmlTransient
    public List<Transaccion> getTransaccionList() {
        return transaccionList;
    }

    public void setTransaccionList(List<Transaccion> transaccionList) {
        this.transaccionList = transaccionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoTransaccion != null ? idTipoTransaccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoTransaccion)) {
            return false;
        }
        TipoTransaccion other = (TipoTransaccion) object;
        if ((this.idTipoTransaccion == null && other.idTipoTransaccion != null) || (this.idTipoTransaccion != null && !this.idTipoTransaccion.equals(other.idTipoTransaccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.arquitectura.model.TipoTransaccion[ idTipoTransaccion=" + idTipoTransaccion + " ]";
    }
    
}
