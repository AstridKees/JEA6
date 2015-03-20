/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import domain.Tweet;
import domain.User;
import java.util.List;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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

    @Inject
    KwetterService kwetterService;

    /**
     * Creates a new instance of KwetterResource
     */
    public KwetterResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("api")
    public List<User> findAllUsers() {
        return kwetterService.findAll();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("api")
    public String addTweet(Tweet tweet, @QueryParam("userID") Long userID) {
        Boolean succes;
        String message = "";
        
        tweet.setId(kwetterService.nextTweetID());
        succes = kwetterService.find(userID).addTweet(tweet);
        if (!succes) {
            message = "Error adding tweet";
        }

        return String.format("{\"succes\":\"%b\",\"message\":\"%s\"}", succes, message);
    }
}
