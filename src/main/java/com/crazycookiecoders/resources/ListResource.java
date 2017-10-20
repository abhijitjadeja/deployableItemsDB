package com.crazycookiecoders.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import java.util.*;
import com.crazycookiecoders.db.*;

@Path("/list")

public class ListResource {

    private final ListDAO dao;

    public ListResource(ListDAO dao) {
        this.dao = dao;
    }



    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{key}")
    public Map<String, Object> fetch(@PathParam("key") String key) {
        return dao.find(key);
    }

    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{key}")
    public Response add(@PathParam("key") String key, Map<String, Object> dto) {
        dao.insert(key, (String) dto.get("name"), (String) dto.get("json"));
        return Response.accepted().build();
    }

    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public Response delete(@PathParam("key") String key){
        dao.delete(key);
        return Response.ok().build();
    }
}