/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.model;

import java.io.Serializable;
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
@Table(name = "PROCESOS", catalog = "", schema = "AGENTECUENTAS")

public class Procesos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "IDPROCESO")
    private Integer idproceso;
    @Column(name = "FECHAPROCESO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaproceso;
    @JoinColumn(name = "IDTIPOPROCESO", referencedColumnName = "IDTIPOPROCESO")
    @ManyToOne
    private Tipoprocesos idtipoproceso;

    public Procesos() {
    }

    public Procesos(Integer idproceso) {
        this.idproceso = idproceso;
    }

    public Procesos(Integer idproceso, Date fechaproceso) {
        this.idproceso = idproceso;
        this.fechaproceso = fechaproceso;
    }

    public Integer getIdproceso() {
        return idproceso;
    }

    public void setIdproceso(Integer idproceso) {
        this.idproceso = idproceso;
    }

    public Date getFechaproceso() {
        return fechaproceso;
    }

    public void setFechaproceso(Date fechaproceso) {
        this.fechaproceso = fechaproceso;
    }

    public Tipoprocesos getIdtipoproceso() {
        return idtipoproceso;
    }

    public void setIdtipoproceso(Tipoprocesos idtipoproceso) {
        this.idtipoproceso = idtipoproceso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idproceso != null ? idproceso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Procesos)) {
            return false;
        }
        Procesos other = (Procesos) object;
        if ((this.idproceso == null && other.idproceso != null) || (this.idproceso != null && !this.idproceso.equals(other.idproceso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.arquitectura.model.Procesos[ idproceso=" + idproceso + " ]";
    }

}
