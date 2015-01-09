/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.teste;

import dao.entity.Decorador;
import dao.entity.Pessoa;
import dao.entity.Reserva;
import dao.entity.Sala;
import dao.entity.Usuario;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Marcos
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public Usuario getUsuarioP(int i) {
        Query query;
        query = getEntityManager().createQuery("SELECT u FROM Usuario u WHERE u.usuarioCod = :i");
        query.setParameter("i", i);
        return (Usuario) query.getSingleResult();
    }

    public Usuario login(String login, String senha) {
        Usuario u = new Usuario();
        Query query;
        query = getEntityManager().createQuery("SELECT u FROM Usuario u WHERE u.login = :login AND u.senha = :senha");
        query.setParameter("login", login);
        query.setParameter("senha", senha);
        List<Usuario> luser = query.getResultList();

        try {
            u = luser.get(0);
        } catch (Exception e) {
            u = null;
        }

        if (u != null) {
            return u;
        } else {
            return null;
        }

    }

    public List<Reserva> getReservas(int p, Date agora) {
        Query query;
        query = getEntityManager().createQuery("SELECT r FROM Reserva r WHERE r.pessoaCod.cod = :pessoa AND r.data >= :agora ORDER BY r.data");
        query.setParameter("pessoa", p);
        query.setParameter("agora", agora);

        return query.getResultList();

    }

    public List<Reserva> getTodasReservas() {
        Query query;
        query = getEntityManager().createQuery("SELECT r FROM Reserva r ORDER BY r.data");

        return query.getResultList();

    }

    public List<Reserva> getMinhasReservas(int p) {
        Query query;
        query = getEntityManager().createQuery("SELECT r FROM Reserva r WHERE r.pessoaCod.cod = :pessoa ORDER BY r.data");
        query.setParameter("pessoa", p);

        return query.getResultList();

    }

    public Pessoa getPessoaCod(int p) {
        Query query;
        query = getEntityManager().createQuery("SELECT r FROM Pessoa r WHERE r.cod = :pessoa");
        query.setParameter("pessoa", p);
        Pessoa pp = new Pessoa();
        if (pp == null) {
            return null;
        } else {
            return (Pessoa) query.getSingleResult();
        }

    }

    public Sala getSalaCod(int p) {
        Query query;
        query = getEntityManager().createQuery("SELECT r FROM Sala r WHERE r.cod = :sala");
        query.setParameter("sala", p);
        Sala s = new Sala();
        s = (Sala) query.getSingleResult();
        if (s == null) {
            return null;
        } else {
            return s;
        }
    }

    public List<Reserva> getReservasAula(int sala, Date agora) {
        Query query;
        query = getEntityManager().createQuery("SELECT r FROM Reserva r WHERE r.salaCod.cod = :sala AND r.data = :agora ORDER BY r.aula");
        query.setParameter("sala", sala);
        query.setParameter("agora", agora);

        return query.getResultList();

    }

    public List<Reserva> getReservasSetor(String categoria, Date agora) {
        Query query;
        query = getEntityManager().createQuery("SELECT r FROM Reserva r WHERE r.salaCod.categoria = :categoria AND r.data = :agora ORDER BY r.data");
        query.setParameter("categoria", categoria);
        query.setParameter("agora", agora);

        return query.getResultList();

    }

    public List<Decorador> decoradorSala(int sala) {
        Query query;
        query = getEntityManager().createQuery("SELECT d FROM Decorador d WHERE d.salaCod.cod = :sala");
        query.setParameter("sala", sala);

        return query.getResultList();

    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
}
