/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi135.documientos.boundary.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import sv.edu.ues.occ.ingenieria.tpi135.documientos.control.TaxonomiaBean;
import sv.edu.ues.occ.ingenieria.tpi135.documientos.entity.Taxonomia;

/**
 *
 * @author alexo
 */
@Path("documento/{idDocumento}/taxonomia")
public class TaxonomiaResuorce implements Serializable {

    @Inject
    TaxonomiaBean tBean;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createTaxonomia(Taxonomia nuevo) {
        if (nuevo == null || nuevo.getIdDocumento() == null || nuevo.getIdTipoDocumento() == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .header(RestResourceHeaderPattern.DETALLE_PARAMETRO_EQUIVOCADO, "Payload nulo o datos incompletos")
                    .build();
        }
        tBean.create(nuevo);
        return Response.status(Response.Status.CREATED)
                .header("Location", "taxonomia/" + nuevo.getIdTaxonomia())
                .build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Taxonomia> findRange(
            @QueryParam(value = "first")
            @DefaultValue(value = "0") int first,
            @QueryParam(value = "pagesize")
            @DefaultValue(value = "50") int pageSize
    ) {
        if (first >= 0 && pageSize > 0) {
            return tBean.findRange(first, pageSize);
        }
        return Collections.EMPTY_LIST;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response findById(@PathParam("id") final Long idTaxonomia) {
        if (idTaxonomia != null) {
            Taxonomia found = tBean.findById(idTaxonomia);
            if (found != null) {
                return Response.status(Response.Status.OK)
                        .entity(found)
                        .build();
            }
            return Response.status(Response.Status.NOT_FOUND)
                    .header("not-found", "id")
                    .build();
        }
        return Response.status(422)
                .header("missing-parameter", "id")
                .build();
    }
}
