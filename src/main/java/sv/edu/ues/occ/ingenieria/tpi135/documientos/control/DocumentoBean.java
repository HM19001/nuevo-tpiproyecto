/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi135.documientos.control;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.io.Serializable;
import sv.edu.ues.occ.ingenieria.tpi135.documientos.entity.Documento;

/**
 *
 * @author alexo
 */
@Stateless
@LocalBean
public class DocumentoBean extends AbstractDataAccess<Documento> implements Serializable {

    @PersistenceContext(unitName = "documientos-PU")
    EntityManager em;

    public DocumentoBean() {
        super(Documento.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public Documento findDocumentoById(Long idDocumento) {
        return em.find(Documento.class, idDocumento);
    }

}
