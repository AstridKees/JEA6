/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import domain.User;
import java.util.List;
import javax.faces.bean.RequestScoped;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import service.KwetterService;

/**
 * REST Web Service
 *
 * @author Luc
 */
@Path("")
@RequestScoped
public class KwetterResource {

    @Context
    private UriInfo context;

    KwetterService kwetterService = new KwetterService();

    /**
     * Creates a new instance of KwetterResource
     */
    public KwetterResource() {
    }

    /**
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("findAllUsers")
    public List<User> findAllUsers() {
        return kwetterService.findAll();
    }

    /**
     * PUT method for updating or creating an instance of KwetterResource
     *
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
