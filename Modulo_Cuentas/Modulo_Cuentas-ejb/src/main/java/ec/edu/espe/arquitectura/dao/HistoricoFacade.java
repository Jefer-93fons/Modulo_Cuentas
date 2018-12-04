/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.dao;

import ec.edu.espe.arquitectura.model.Historico;
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
public class HistoricoFacade extends AbstractFacade<Historico> {

    @PersistenceContext(unitName = "com.mycompany_Modulo_Cuentas-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HistoricoFacade() {
        super(Historico.class);
    }

    public List<Historico> findByAccount(Integer idCuenta) {
        Query qry = this.em.createQuery("SELECT obj FROM Historico obj WHERE obj.idCuenta.idCuenta=?1");
        qry.setParameter(1, idCuenta);
        return qry.getResultList();
    }
}
