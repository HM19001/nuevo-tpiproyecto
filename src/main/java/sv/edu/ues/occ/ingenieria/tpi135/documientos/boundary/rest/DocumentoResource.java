/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi135.documientos.boundary.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import java.io.Serializable;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import sv.edu.ues.occ.ingenieria.tpi135.documientos.control.DocumentoBean;
import sv.edu.ues.occ.ingenieria.tpi135.documientos.entity.Documento;

/**
 *
 * @author alexo
 */
@Path("documento")
public class DocumentoResource implements Serializable {

    @Inject
    DocumentoBean dBean;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createDocumento(Documento nuevo) {
        if (nuevo == null) {
            // Payload nulo
            return Response.status(Response.Status.BAD_REQUEST)
                    .header(RestResourceHeaderPattern.DETALLE_PARAMETRO_EQUIVOCADO, "Payload nulo")
                    .build();
        } else if (nuevo.getIdDocumento() != null) {
            // Payload no nulo, pero ID de documento no debe estar establecido
            return Response.status(Response.Status.BAD_REQUEST)
                    .header(RestResourceHeaderPattern.DETALLE_PARAMETRO_EQUIVOCADO, "ID de documento no debe estar establecido")
                    .build();
        } else if (nuevo.getNombre() == null || nuevo.getNombre().isEmpty()) {
            // Payload no nulo, pero nombre de documento no puede estar vacío
            return Response.status(Response.Status.BAD_REQUEST)
                    .header(RestResourceHeaderPattern.DETALLE_PARAMETRO_EQUIVOCADO, "Nombre de documento no puede estar vacío")
                    .build();
        }

        dBean.create(nuevo);

        URI location = UriBuilder.fromResource(DocumentoResource.class)
                .path(nuevo.getIdDocumento().toString())
                .build();
        return Response.created(location).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Documento> findRange(
            @QueryParam(value = "first")
            @DefaultValue(value = "0") int first,
            @QueryParam(value = "pagesize")
            @DefaultValue(value = "50") int pageSize
    ) {
        if (first >= 0 && pageSize > 0) {
            return dBean.findRange(first, pageSize);
        }
        return Collections.EMPTY_LIST;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response findById(@PathParam("id") final Long idDocumento) {
        if (idDocumento != null) {
            Documento found = dBean.findById(idDocumento);
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
    @DELETE
    @Path("/{id}")
    public Response deleteDocumento(@PathParam("id") Long idDocumento) {
        try {
            if (idDocumento == null) {
                throw new IllegalArgumentException("ID de documento no especificado");
            }

            Documento documento = dBean.findById(idDocumento);
            if (documento != null) {
                dBean.delete(documento);
                return Response.status(Response.Status.NO_CONTENT).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("No se encontró el documento con ID: " + idDocumento)
                        .build();
            }
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Error en los parámetros de la solicitud: " + e.getMessage())
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error interno del servidor: " + e.getMessage())
                    .build();
        }
    }

}