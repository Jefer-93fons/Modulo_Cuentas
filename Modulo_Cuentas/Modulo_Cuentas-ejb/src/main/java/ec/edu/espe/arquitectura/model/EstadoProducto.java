/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Juan
 */
@Entity
@Table(name = "ESTADO_PRODUCTO")
@NamedQueries({
    @NamedQuery(name = "EstadoProducto.findAll", query = "SELECT e FROM EstadoProducto e")})
public class EstadoProducto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ESTADO_PRODUCTO")
    private Integer idEstadoProducto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBRE_ESTADO_PRODUCTO")
    private String nombreEstadoProducto;
    @OneToMany(mappedBy = "idEstadoProducto")
    private List<HistoricoProducto> historicoProductoList;

    public EstadoProducto() {
    }

    public EstadoProducto(Integer idEstadoProducto) {
        this.idEstadoProducto = idEstadoProducto;
    }

    public EstadoProducto(Integer idEstadoProducto, String nombreEstadoProducto) {
        this.idEstadoProducto = idEstadoProducto;
        this.nombreEstadoProducto = nombreEstadoProducto;
    }

    public Integer getIdEstadoProducto() {
        return idEstadoProducto;
    }

    public void setIdEstadoProducto(Integer idEstadoProducto) {
        this.idEstadoProducto = idEstadoProducto;
    }

    public String getNombreEstadoProducto() {
        return nombreEstadoProducto;
    }

    public void setNombreEstadoProducto(String nombreEstadoProducto) {
        this.nombreEstadoProducto = nombreEstadoProducto;
    }

    public List<HistoricoProducto> getHistoricoProductoList() {
        return historicoProductoList;
    }

    public void setHistoricoProductoList(List<HistoricoProducto> historicoProductoList) {
        this.historicoProductoList = historicoProductoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstadoProducto != null ? idEstadoProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoProducto)) {
            return false;
        }
        EstadoProducto other = (EstadoProducto) object;
        if ((this.idEstadoProducto == null && other.idEstadoProducto != null) || (this.idEstadoProducto != null && !this.idEstadoProducto.equals(other.idEstadoProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.arquitectura.model.EstadoProducto[ idEstadoProducto=" + idEstadoProducto + " ]";
    }
    
}
