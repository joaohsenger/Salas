/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.teste;

import dao.entity.Pessoa;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Marcos
 */
@Stateless
public class PessoaFacade extends AbstractFacade<Pessoa> {
    @PersistenceContext(unitName = "SalasPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PessoaFacade() {
        super(Pessoa.class);
    }
    
}
