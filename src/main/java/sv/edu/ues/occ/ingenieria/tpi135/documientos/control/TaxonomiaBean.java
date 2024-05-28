/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi135.documientos.control;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.io.Serializable;
import sv.edu.ues.occ.ingenieria.tpi135.documientos.entity.Taxonomia;

/**
 *
 * @author alexo
 */
@Stateless
@LocalBean
public class TaxonomiaBean extends AbstractDataAccess<Taxonomia> implements Serializable {

    @PersistenceContext(unitName = "documientos-PU")
    EntityManager em;

    public TaxonomiaBean() {
        super(Taxonomia.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public Long findTipoDocumentoIdByDocumento(Long idDocumento) {
        try {
            TypedQuery<Long> query = em.createNamedQuery("Taxonomia.findTipoDocumentoByDocumento", Long.class);
            query.setParameter("idDocumento", idDocumento);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null; // Manejar la situación en la que no se encuentra ningún resultado
        }
    }
}
