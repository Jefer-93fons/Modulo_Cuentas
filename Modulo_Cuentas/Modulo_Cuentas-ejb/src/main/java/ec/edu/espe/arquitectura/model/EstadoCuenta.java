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
@Table(name = "ESTADO_CUENTA", catalog = "", schema = "AGENTECUENTAS")
public class EstadoCuenta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID_ESTADO_CUENTA")
    private Integer idEstadoCuenta;
   
    @Column(name = "NOMBRE_ESTADO_CUENTA")
    private String nombreEstadoCuenta;
    @OneToMany(mappedBy = "idEstadoCuenta")
    private List<Historico> historicoList;

    public EstadoCuenta() {
    }

    public EstadoCuenta(Integer idEstadoCuenta) {
        this.idEstadoCuenta = idEstadoCuenta;
    }

    public EstadoCuenta(Integer idEstadoCuenta, String nombreEstadoCuenta) {
        this.idEstadoCuenta = idEstadoCuenta;
        this.nombreEstadoCuenta = nombreEstadoCuenta;
    }

    public Integer getIdEstadoCuenta() {
        return idEstadoCuenta;
    }

    public void setIdEstadoCuenta(Integer idEstadoCuenta) {
        this.idEstadoCuenta = idEstadoCuenta;
    }

    public String getNombreEstadoCuenta() {
        return nombreEstadoCuenta;
    }

    public void setNombreEstadoCuenta(String nombreEstadoCuenta) {
        this.nombreEstadoCuenta = nombreEstadoCuenta;
    }

    @XmlTransient
    public List<Historico> getHistoricoList() {
        return historicoList;
    }

    public void setHistoricoList(List<Historico> historicoList) {
        this.historicoList = historicoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstadoCuenta != null ? idEstadoCuenta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoCuenta)) {
            return false;
        }
        EstadoCuenta other = (EstadoCuenta) object;
        if ((this.idEstadoCuenta == null && other.idEstadoCuenta != null) || (this.idEstadoCuenta != null && !this.idEstadoCuenta.equals(other.idEstadoCuenta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.arquitectura.model.EstadoCuenta[ idEstadoCuenta=" + idEstadoCuenta + " ]";
    }
    
}
