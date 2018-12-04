/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.dao;

import ec.edu.espe.arquitectura.model.ComisionProducto;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Juan
 */
@Stateless
public class ComisionProductoFacade extends AbstractFacade<ComisionProducto>  {

    @PersistenceContext(unitName = "com.mycompany_Modulo_Cuentas-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ComisionProductoFacade() {
        super(ComisionProducto.class);
    }
    
    public List<ComisionProducto> findByCodigo(Integer codigo) {
        Query qry = this.em.createQuery("SELECT obj FROM ComisionProducto obj WHERE obj.idComisionProducto=?1");
        qry.setParameter(1, codigo);
        return qry.getResultList();
    }
}
