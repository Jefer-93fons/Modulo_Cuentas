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
@Table(name = "ESTADO_PRODUCTO", catalog = "", schema = "AGENTECUENTAS")

public class EstadoProducto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID_ESTADO_PRODUCTO")
    private Integer idEstadoProducto;
    @Column(name = "NOMBRE_ESTADO_PRODUCTO")
    private String nombreEstadoProducto;
    @OneToMany(mappedBy = "idEstadoProducto")
    private List<Producto> productoList;

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

    @XmlTransient
    public List<Producto> getProductoList() {
        return productoList;
    }

    public void setProductoList(List<Producto> productoList) {
        this.productoList = productoList;
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
