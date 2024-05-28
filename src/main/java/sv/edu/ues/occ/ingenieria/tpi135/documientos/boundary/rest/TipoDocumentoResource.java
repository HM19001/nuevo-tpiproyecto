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
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sv.edu.ues.occ.ingenieria.tpi135.documientos.control.TipoDocumentoBean;
import sv.edu.ues.occ.ingenieria.tpi135.documientos.entity.TipoDocumento;

/**
 *
 * @author alexo
 */
@Path("tipodocumento")
public class TipoDocumentoResource implements Serializable {

    @Inject
    TipoDocumentoBean tdBean;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createTipoDocumento(TipoDocumento nuevo) {
        if (nuevo == null || nuevo.getIdTipoDocumento() != null) {
            // Payload nulo o incorrecto
            return Response.status(RestResourceHeaderPattern.STATUS_PARAMETRO_EQUIVOCADO)
                    .header(RestResourceHeaderPattern.DETALLE_PARAMETRO_EQUIVOCADO, "Payload nulo o incorrecto")
                    .build();
        }

        // Realizar otras validaciones necesarias
        // Crear el tipo de documento
        tdBean.create(nuevo);

        // Retornar la respuesta exitosa con el código 201 y la ubicación del nuevo recurso creado
        return Response.status(Response.Status.CREATED)
                .header("Location", "tipodocumento/" + nuevo.getIdTipoDocumento())
                .build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<TipoDocumento> findRange(
            @QueryParam(value = "first")
            @DefaultValue(value = "0") int first,
            @QueryParam(value = "pagesize")
            @DefaultValue(value = "50") int pageSize
    ) {
        if (first >= 0 && pageSize > 0) {
            return tdBean.findRange(first, pageSize);
        }
        return Collections.EMPTY_LIST;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response findById(@PathParam("id") final Integer idTipoDocumento) {
        if (idTipoDocumento != null) {
            TipoDocumento found = tdBean.findById(idTipoDocumento);
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
    @Produces({MediaType.APPLICATION_JSON})
    public Response delete(@PathParam("id") Integer idTipoDocumento) {
        if (idTipoDocumento != null) {
            TipoDocumento registro = tdBean.findById(idTipoDocumento);

            if (registro != null) {
                try {
                    tdBean.delete(registro);
                    return Response.status(Response.Status.NO_CONTENT).build();
                } catch (Exception ex) {
                    Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
                    return Response.status(500).header("delete-exception", registro.toString()).build();
                }
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .header("not-found", "id")
                        .build();
            }
        }

        return Response.status(422)
                .header("missing-parameter", "id")
                .build();
    }
}
