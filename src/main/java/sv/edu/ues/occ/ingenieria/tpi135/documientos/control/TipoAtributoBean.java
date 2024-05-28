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
import sv.edu.ues.occ.ingenieria.tpi135.documientos.entity.TipoAtributo;

/**
 *
 * @author alexo
 */
@Stateless
@LocalBean
public class TipoAtributoBean extends AbstractDataAccess<TipoAtributo> implements Serializable {

    @PersistenceContext(unitName = "documientos-PU")
    EntityManager em;

    public TipoAtributoBean() {
        super(TipoAtributo.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

}
