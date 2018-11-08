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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Juan
 */
@Entity
@Table(name = "PRODUCTO", catalog = "", schema = "AGENTECUENTAS")
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    
    @Column(name = "ID_PRODUCTO")
    private Integer idProducto;
    
    @Column(name = "ID_TIPO_PRODUCTO", nullable = false)
    private Integer codTipoProducto;
    
    @Column(name = "NOMBRE_PRODUCTO")
    private String nombreProducto;
    
    @Column(name = "RESTRICCION_PRODUCTO")
    private String restriccionProducto;
   
    
    @JoinColumn(name = "ID_TIPO_PRODUCTO", referencedColumnName = "ID_TIPO_PRODUCTO", insertable = false, updatable = false)
    @ManyToOne
    private TipoProducto idTipoProducto;

    public Producto() {
    }

    public Producto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Producto(Integer idProducto, String nombreProducto, String restriccionProducto) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.restriccionProducto = restriccionProducto;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getRestriccionProducto() {
        return restriccionProducto;
    }

    public void setRestriccionProducto(String restriccionProducto) {
        this.restriccionProducto = restriccionProducto;
    }

 

    public TipoProducto getIdTipoProducto() {
        return idTipoProducto;
    }

    public void setIdTipoProducto(TipoProducto idTipoProducto) {
        this.idTipoProducto = idTipoProducto;
    }

    public Integer getCodTipoProducto() {
        return codTipoProducto;
    }

    public void setCodTipoProducto(Integer codTipoProducto) {
        this.codTipoProducto = codTipoProducto;
    }

   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProducto != null ? idProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.idProducto == null && other.idProducto != null) || (this.idProducto != null && !this.idProducto.equals(other.idProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.arquitectura.model.Producto[ idProducto=" + idProducto + " ]";
    }
    
}
