/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.teste;

import dao.entity.Decorador;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Marcos
 */
@Stateless
public class DecoradorFacade extends AbstractFacade<Decorador> {
    @PersistenceContext(unitName = "SalasPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DecoradorFacade() {
        super(Decorador.class);
    }
    
}
