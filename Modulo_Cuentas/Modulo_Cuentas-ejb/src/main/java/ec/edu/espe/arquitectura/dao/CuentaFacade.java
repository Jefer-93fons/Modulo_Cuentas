/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.dao;

import ec.edu.espe.arquitectura.model.Cuenta;
import ec.edu.espe.arquitectura.model.Cliente;
import ec.edu.espe.arquitectura.model.Producto;
import java.util.List;
import javax.ejb.Stateless;
import javax.naming.spi.DirStateFactory.Result;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Jefferson
 */
@Stateless
public class CuentaFacade extends AbstractFacade<Cuenta> {

    @PersistenceContext(unitName = "com.mycompany_Modulo_Cuentas-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CuentaFacade() {
        super(Cuenta.class);
    }
    
    public List<Cliente> findByCodigo(String cedula) {
        Query qry = this.em.createQuery("SELECT c FROM Cliente c WHERE c.cod_cliente=?1");
        qry.setParameter(1, cedula);
        return qry.getResultList();
        
    }
   
    
    public List<Cliente> buscarCliente(String cedula) {
        String consulta;
        Query query;

        try{
            consulta = "SELECT * FROM Cliente c WHERE c.cod_cliente like ?1";
            query = em.createNativeQuery(consulta);
            query.setParameter(1, cedula);
            
            
        }catch(Exception e){
            throw e;
        }

        return query.getResultList();
    }
    
}
