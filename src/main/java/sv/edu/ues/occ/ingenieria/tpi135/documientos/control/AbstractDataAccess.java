/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi135.documientos.control;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alexo
 */
public abstract class AbstractDataAccess<T> implements Serializable {

    final Class<T> tipoDato;

    public AbstractDataAccess(Class tipoDato) {
        this.tipoDato = tipoDato;
    }

    public abstract EntityManager getEntityManager();

    public void create(T registro) throws IllegalArgumentException, IllegalStateException {

        if (registro != null) {
            try {
                EntityManager em = getEntityManager();
                if (em != null) {
                    em.persist(registro);
                    return;
                }

            } catch (Exception ex) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);

            }
            throw new IllegalStateException();
        }
        throw new IllegalArgumentException();
    }

    public List<T> findRange(int first, int pageSize) throws IllegalStateException {

        EntityManager em = null;

        if (first >= 0 && pageSize > 0) {

            try {
                em = getEntityManager();
                if (em != null) {
                    CriteriaBuilder cb = em.getCriteriaBuilder();
                    CriteriaQuery cq = cb.createQuery(tipoDato);
                    Root<T> raiz = cq.from(tipoDato);
                    cq.select(raiz);
                    TypedQuery q = em.createQuery(cq);

                    q.setFirstResult(first);
                    q.setMaxResults(pageSize);
                    return q.getResultList();

                }

            } catch (Exception ex) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);

            }
            throw new IllegalStateException();

        }
        return Collections.EMPTY_LIST;

    }

    public T modify(T registro) throws IllegalStateException {
        EntityManager em = null;
        try {
            em = getEntityManager();

        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }

        if (registro != null) {
            if (em != null) {
                try {
                    return em.merge(registro);
                } catch (Exception ex) {
                    Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
                }
            }
            throw new IllegalStateException();
        }

        return null;

    }

    public void delete(T registro) throws IllegalStateException, IllegalArgumentException {

        if (registro != null) {
            EntityManager em = null;
            try {
                em = getEntityManager();
            } catch (Exception ex) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }
            if (em != null) {
                if (!em.contains(registro)) {
                    registro = em.merge(registro);
                }
                em.remove(registro);
                return;
            } else {
                throw new IllegalStateException();
            }
        }
        throw new IllegalArgumentException();

    }

    public T findById(Object id) throws IllegalStateException, IllegalArgumentException {
        if (id != null) {
            try {
                EntityManager em = getEntityManager();
                if (em != null) {
                    return (T) em.find(tipoDato, id);
                } else {
                    throw new IllegalStateException("El EntityManager es nulo");
                }
            } catch (Exception ex) {
                throw new IllegalStateException("Error al buscar el registro con el ID proporcionado: " + id, ex);
            }
        } else {
            throw new IllegalArgumentException("El ID proporcionado no puede ser nulo");
        }
    }

    public Long count() throws IllegalStateException {
        EntityManager em = null;
        try {
            em = getEntityManager();
        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        if (em != null) {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Long> cq = cb.createQuery(Long.class);

            cq.select(cb.count(cq.from(tipoDato)));

            return em.createQuery(cq).getSingleResult();
        }
        throw new IllegalStateException();

    }

    public Query generarConsultaBase(EntityManager em) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(tipoDato));
        Query q = em.createQuery(cq);
        return q;
    }

    public List<T> findAll() throws IllegalStateException {
        EntityManager em = null;
        try {
            em = this.getEntityManager();
        } catch (Exception e) {
            throw new IllegalStateException();
        }
        if (em != null) {
            Query q = generarConsultaBase(em);
            return q.getResultList();
        } else {
            return new ArrayList<>();
        }
    }
}
