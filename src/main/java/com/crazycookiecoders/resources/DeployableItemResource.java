package com.crazycookiecoders.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
@Path("/deployableItem/{deployableItem}")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DeployableItemResource{
    @GET
    public String getData(){
        return "['deployableItem']";
    }
}

