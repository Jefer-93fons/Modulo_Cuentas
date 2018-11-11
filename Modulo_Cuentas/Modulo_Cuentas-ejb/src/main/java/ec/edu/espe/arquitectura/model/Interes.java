/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Juan
 */
@Entity
@Table(name = "INTERES")
public class Interes implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    
    @Column(name = "ID_INTERES")

    private Integer idInteres;
    @Column(name = "ID_PERIODO", nullable = false)
    private Integer codPeriodo;

    
 
    @Column(name = "PORCENTAJE_INTERES")
    private BigDecimal porcentajeInteres;

    @Column(name = "VALOR_MIN")
    private BigDecimal valorMin;

    

    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR_MAX")
    private BigDecimal valorMax;
    @JoinColumn(name = "ID_PERIODO", referencedColumnName = "ID_PERIODO")

    @ManyToOne
    private Periodo idPeriodo;
    @OneToMany(mappedBy = "idInteres")
    private List<InteresProducto> interesProductoList;

    public Interes() {
    }

   

    public Interes(Integer idInteres, BigDecimal porcentajeInteres, BigDecimal valorMin, BigDecimal valorMax) {
        this.idInteres = idInteres;
        this.porcentajeInteres = porcentajeInteres;
        this.valorMin = valorMin;
        this.valorMax = valorMax;
    }

    public Integer getIdInteres() {
        return idInteres;
    }

    public void setIdInteres(Integer idInteres) {
        this.idInteres = idInteres;
    }

  
    public BigDecimal getPorcentajeInteres() {
        return porcentajeInteres;
    }

    public void setPorcentajeInteres(BigDecimal porcentajeInteres) {
        this.porcentajeInteres = porcentajeInteres;
    }

    public BigDecimal getValorMin() {
        return valorMin;
    }

    public void setValorMin(BigDecimal valorMin) {
        this.valorMin = valorMin;
    }

    public BigDecimal getValorMax() {
        return valorMax;
    }

    public void setValorMax(BigDecimal valorMax) {
        this.valorMax = valorMax;
    }

    public Periodo getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(Periodo idPeriodo) {
        this.idPeriodo = idPeriodo;
    }


    public Integer getCodPeriodo() {
        return codPeriodo;
    }

    public void setCodPeriodo(Integer codPeriodo) {
        this.codPeriodo = codPeriodo;
    }

   
    


    public List<InteresProducto> getInteresProductoList() {
        return interesProductoList;
    }

    public void setInteresProductoList(List<InteresProducto> interesProductoList) {
        this.interesProductoList = interesProductoList;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInteres != null ? idInteres.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Interes)) {
            return false;
        }
        Interes other = (Interes) object;
        if ((this.idInteres == null && other.idInteres != null) || (this.idInteres != null && !this.idInteres.equals(other.idInteres))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.arquitectura.model.Interes[ idInteres=" + idInteres + " ]";
    }
    
}
