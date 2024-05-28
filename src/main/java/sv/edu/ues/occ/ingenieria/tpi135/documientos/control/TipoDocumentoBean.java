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
import sv.edu.ues.occ.ingenieria.tpi135.documientos.entity.TipoDocumento;

/**
 *
 * @author alexo
 */
@Stateless
@LocalBean
public class TipoDocumentoBean extends AbstractDataAccess<TipoDocumento> implements Serializable {

    @PersistenceContext(unitName = "documientos-PU")
    EntityManager em;

    public TipoDocumentoBean() {
        super(TipoDocumento.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public TipoDocumento findTipoByDocumentoId(Long documentoId) {
        try {
            TypedQuery<TipoDocumento> query = em.createQuery(
                    "SELECT td FROM Documento d JOIN d.tipoDocumento td WHERE d.idDocumento = :documentoId",
                    TipoDocumento.class);
            query.setParameter("documentoId", documentoId);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null; // No se encontró el tipo de documento
        }
    }

    public TipoDocumento find(Integer id) {
        return em.find(TipoDocumento.class, id);
    }

    public boolean isAtributoValidoParaDocumento(Long idDocumento, Long idAtributo) {
        TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(a) FROM Atributo a "
                + "JOIN a.idTipoDocumento td "
                + "JOIN td.taxonomia t "
                + "WHERE t.idDocumento.idDocumento = :idDocumento AND a.idAtributo = :idAtributo", Long.class);
        query.setParameter("idDocumento", idDocumento);
        query.setParameter("idAtributo", idAtributo);
        return query.getSingleResult() > 0;
    }
}
