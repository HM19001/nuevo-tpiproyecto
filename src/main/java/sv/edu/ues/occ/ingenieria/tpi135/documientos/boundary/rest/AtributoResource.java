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
import sv.edu.ues.occ.ingenieria.tpi135.documientos.control.AtributoBean;
import sv.edu.ues.occ.ingenieria.tpi135.documientos.entity.Atributo;

/**
 *
 * @author alexo
 */
@Path("tipodocumento/{idTipoDocumento}/atributo")
public class AtributoResource implements Serializable {

    @Inject
    AtributoBean aBean;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAtributo(@PathParam("idTipoDocumento") Integer idTipoDocumento, Atributo nuevo) {
        if (nuevo == null) {
            // Payload nulo
            return Response.status(RestResourceHeaderPattern.STATUS_PARAMETRO_EQUIVOCADO)
                    .header(RestResourceHeaderPattern.DETALLE_PARAMETRO_EQUIVOCADO, "Payload nulo")
                    .build();
        }

        // Verificar que el ID del tipo de documento en el payload coincida con el ID en la URL
        if (nuevo.getIdTipoDocumento() == null || !nuevo.getIdTipoDocumento().getIdTipoDocumento().equals(idTipoDocumento)) {
            // El ID del tipo de documento en el payload no coincide con el de la URL
            return Response.status(RestResourceHeaderPattern.STATUS_PARAMETRO_EQUIVOCADO)
                    .header(RestResourceHeaderPattern.DETALLE_PARAMETRO_EQUIVOCADO, "El ID del tipo de documento en el payload no coincide con el de la URL")
                    .build();
        }

        // Realizar otras validaciones necesarias
        // Crear el atributo
        aBean.create(nuevo);

        // Retornar la respuesta exitosa con el código 201 y la ubicación del nuevo recurso creado
        return Response.status(Response.Status.CREATED)
                .header("Location", "tipodocumento/" + idTipoDocumento + "/atributo/" + nuevo.getIdAtributo())
                .build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Atributo> findRange(
            @QueryParam(value = "first")
            @DefaultValue(value = "0") int first,
            @QueryParam(value = "pagesize")
            @DefaultValue(value = "50") int pageSize
    ) {
        if (first >= 0 && pageSize > 0) {
            return aBean.findRange(first, pageSize);
        }
        return Collections.EMPTY_LIST;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response findById(@PathParam("id") final Long idAtributo) {
        if (idAtributo != null) {
            Atributo found = aBean.findById(idAtributo);
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
