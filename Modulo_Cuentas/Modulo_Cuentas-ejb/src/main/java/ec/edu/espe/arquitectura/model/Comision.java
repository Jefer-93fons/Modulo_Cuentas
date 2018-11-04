/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Juan
 */
@Entity
@Table(name = "COMISION", catalog = "", schema = "AGENTECUENTAS")
public class Comision implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID_COMISION")
    private Integer idComision;

    @Column(name = "RAZON_COMISION")
    private String razonComision;

    public Comision() {
    }

    public Comision(Integer idComision) {
        this.idComision = idComision;
    }

    public Comision(Integer idComision, String razonComision) {
        this.idComision = idComision;
        this.razonComision = razonComision;
    }

    public Integer getIdComision() {
        return idComision;
    }

    public void setIdComision(Integer idComision) {
        this.idComision = idComision;
    }

    public String getRazonComision() {
        return razonComision;
    }

    public void setRazonComision(String razonComision) {
        this.razonComision = razonComision;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idComision != null ? idComision.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comision)) {
            return false;
        }
        Comision other = (Comision) object;
        if ((this.idComision == null && other.idComision != null) || (this.idComision != null && !this.idComision.equals(other.idComision))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.arquitectura.model.Comision[ idComision=" + idComision + " ]";
    }
    
}
