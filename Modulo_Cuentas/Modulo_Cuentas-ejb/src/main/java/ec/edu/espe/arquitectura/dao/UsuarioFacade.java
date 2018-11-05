/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.dao;

import ec.edu.espe.arquitectura.model.Usuario;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Jefferson
 */
@Stateless
@LocalBean
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "com.mycompany_Modulo_Cuentas-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    public String iniciarSesion(Usuario us) {
        String usuario="";
        String consulta;
        Object object = new Object();


        try{
            consulta = "SELECT u.nombre_Usuario FROM Usuario u WHERE u.nombre_Usuario like ?1 and u.clave_Usuario like ?2";
            Query query = em.createNativeQuery(consulta);
            query.setParameter(1, us.getNombreUsuario());
            query.setParameter(2, us.getClaveUsuario());

            object = query.getSingleResult();
            if(object!=null){
                usuario = String.valueOf(query.getSingleResult());
            }
        }catch(Exception e){
            throw e;
        }


        return usuario;
    }
    
}
