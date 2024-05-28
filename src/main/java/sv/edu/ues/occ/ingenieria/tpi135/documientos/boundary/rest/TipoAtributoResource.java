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
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sv.edu.ues.occ.ingenieria.tpi135.documientos.control.TipoAtributoBean;
import sv.edu.ues.occ.ingenieria.tpi135.documientos.entity.TipoAtributo;

/**
 *
 * @author alexo
 */
@Path("tipoatributo")
public class TipoAtributoResource {

    @Inject
    TipoAtributoBean taBean;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createTipoAtributo(TipoAtributo nuevo) {
        if (nuevo == null || nuevo.getIdTipoAtributo() != null) {
            // Payload nulo o incorrecto
            return Response.status(RestResourceHeaderPattern.STATUS_PARAMETRO_EQUIVOCADO)
                    .header(RestResourceHeaderPattern.DETALLE_PARAMETRO_EQUIVOCADO, "Payload nulo o incorrecto")
                    .build();
        }

        // Realizar otras validaciones necesarias
        // Crear el tipo de atributo
        taBean.create(nuevo);

        // Retornar la respuesta exitosa con el código 201 y la ubicación del nuevo recurso creado
        return Response.status(Response.Status.CREATED)
                .header("Location", "tipoatributo/" + nuevo.getIdTipoAtributo())
                .build();
    }

   @GET
    public Response findRange(
            @QueryParam("first") @DefaultValue("0") int first,
            @QueryParam("pagesize") @DefaultValue("50") int pageSize) {

        try {
            if (first < 0 || pageSize <= 0) {
                throw new IllegalArgumentException("Parámetros de consulta inválidos");
            }

            List<TipoAtributo> result = taBean.findRange(first, pageSize);
            Long total = taBean.count();
            return Response.ok(result)
                    .header("Total-Registros", total)
                    .build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Error en los parámetros de consulta: " + e.getMessage())
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error interno del servidor: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") final Integer idTipoAtributo) {
        try {
            if (idTipoAtributo == null) {
                throw new IllegalArgumentException("ID de documento no especificado");
            }

            TipoAtributo found = taBean.findById(idTipoAtributo);
            if (found != null) {
                return Response.ok(found).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("No se encontró el documento con ID: " + idTipoAtributo)
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
    
    @DELETE
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response delete(@PathParam("id") Integer idTipoAtributo) {
        if (idTipoAtributo != null) {
            TipoAtributo registro = taBean.findById(idTipoAtributo);

            if (registro != null) {
                try {
                    taBean.delete(registro);
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
