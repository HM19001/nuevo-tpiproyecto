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
import java.util.Collections;
import java.util.List;
import sv.edu.ues.occ.ingenieria.tpi135.documientos.control.AtributoBean;
import sv.edu.ues.occ.ingenieria.tpi135.documientos.control.MetadatoBean;
import sv.edu.ues.occ.ingenieria.tpi135.documientos.control.TipoDocumentoBean;
import sv.edu.ues.occ.ingenieria.tpi135.documientos.entity.Atributo;
import sv.edu.ues.occ.ingenieria.tpi135.documientos.entity.Metadato;

/**
 *
 * @author alexo
 */
@Path("documento/{idDocumento}/metadato")
public class MetadatoResource {

    @Inject
    MetadatoBean mBean;

    @Inject
    TipoDocumentoBean tdBean;

    @Inject
    AtributoBean aBean;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createMetadato(@PathParam("idDocumento") Long idDocumento, Metadato metadato) {
        // Validación de payload nulo o campos incompletos
        if (metadato == null || metadato.getIdAtributo() == null || metadato.getValor() == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("El payload está incompleto o nulo.")
                    .header(RestResourceHeaderPattern.DETALLE_PARAMETRO_EQUIVOCADO, "Payload incompleto o nulo")
                    .build();
        }

        // Validar la existencia del atributo
        Atributo atributo = metadato.getIdAtributo();
        if (atributo == null || atributo.getIdAtributo() == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("El atributo no puede ser nulo.")
                    .header(RestResourceHeaderPattern.DETALLE_PARAMETRO_EQUIVOCADO, "Atributo nulo")
                    .build();
        }

//        Validar la existencia del tipo de documento asociado al atributo
//        TipoDocumento tipoDocumento = atributo.getIdTipoDocumento();
//        if (tipoDocumento == null || !tipoDocumento.getIdTipoDocumento().equals(idDocumento)) {
//            return Response.status(Response.Status.METHOD_NOT_ALLOWED)
//                    .entity("El tipo de documento asociado al atributo no es válido.")
//                    .header(RestResourceHeaderPattern.DETALLE_PARAMETRO_EQUIVOCADO, "Tipo de documento inválido")
//                    .build();
//        }
        try {
            mBean.create(metadato);
            return Response.status(Response.Status.CREATED)
                    .entity(metadato)
                    .header("Location", "documento/" + idDocumento + "/metadato/" + metadato.getIdMetadata())
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al crear el metadato.")
                    .build();
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Metadato> findRange(
            @QueryParam(value = "first")
            @DefaultValue(value = "0") int first,
            @QueryParam(value = "pagesize")
            @DefaultValue(value = "50") int pageSize
    ) {
        if (first >= 0 && pageSize > 0) {
            return mBean.findRange(first, pageSize);
        }
        return Collections.EMPTY_LIST;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response findById(@PathParam("id") final Long idMetadata) {
        if (idMetadata != null) {
            Metadato found = mBean.findById(idMetadata);
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
