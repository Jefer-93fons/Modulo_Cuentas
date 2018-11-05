
package ec.edu.espe.arquitectura.dao;

import ec.edu.espe.arquitectura.model.Producto;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless
@LocalBean
public class ProductoFacade extends AbstractFacade<Producto> {

    @PersistenceContext(unitName = "com.mycompany_Modulo_Cuentas-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductoFacade() {
        super(Producto.class);
    }
    public List<Producto> findByCodigo(Integer codigo) {
        Query qry = this.em.createQuery("SELECT obj FROM Producto obj WHERE obj.idProducto=?1");
        qry.setParameter(1, codigo);
        return qry.getResultList();
    }
}


