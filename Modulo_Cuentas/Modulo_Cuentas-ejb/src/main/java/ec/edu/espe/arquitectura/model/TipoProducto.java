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
@Table(name = "TIPO_PRODUCTO", catalog = "", schema = "AGENTECUENTAS")

public class TipoProducto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID_TIPO_PRODUCTO")
    private Integer codigo;
    
   @Column(name = "NOMBRE_TIPO_PRODUCTO")
    private String nombreTipoProducto;
   
  

    public TipoProducto() {
    }

    public TipoProducto(Integer codigo) {
        this.codigo = codigo;
    }

    

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }


    public String getNombreTipoProducto() {
        return nombreTipoProducto;
    }

    public void setNombreTipoProducto(String nombreTipoProducto) {
        this.nombreTipoProducto = nombreTipoProducto;
    }

 

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoProducto)) {
            return false;
        }
        TipoProducto other = (TipoProducto) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.arquitectura.model.TipoProducto[ idTipoProducto=" + codigo + " ]";
    }
    
}
