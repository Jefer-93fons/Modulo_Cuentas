/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.dao;

import ec.edu.espe.arquitectura.model.InteresProducto;
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
public class InteresProductoFacade extends AbstractFacade<InteresProducto>  {

    @PersistenceContext(unitName = "com.mycompany_Modulo_Cuentas-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InteresProductoFacade() {
        super(InteresProducto.class);
    }
    
    public List<InteresProducto> findByCodigo(Integer codigo) {
        Query qry = this.em.createQuery("SELECT obj FROM InteresProducto obj WHERE obj.idInteresProducto=?1");
        qry.setParameter(1, codigo);
        return qry.getResultList();
    }
    
}
