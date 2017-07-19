package com.crazycookiecoders.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import java.time.LocalDate;
import java.util.*;
import com.crazycookiecoders.db.ReleaseDAO;

@Path("/release")

public class ReleaseResource {

    private final ReleaseDAO dao;

    public ReleaseResource(ReleaseDAO dao) {
        this.dao = dao;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    public List<Map<String, Object>> getAll() {
        return dao.getAllReleases();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{key}")
    public Map<String, Object> fetch(@PathParam("key") String key) {
        return dao.findReleaseByKey(key);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{key}")
    public Response update(@PathParam("key") String key, Map<String, Object> dto) {
        dao.update(key, (String) dto.get("name"), (String) dto.get("description"), parseDate((String) dto.get("date")),
                (String) dto.get("status"));
        return Response.accepted().build();
    }

    private LocalDate parseDate(String date) {
        if (date != null)
            return LocalDate.parse(date);
        else
            return null;
    }

    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{key}")
    public Response add(@PathParam("key") String key, Map<String, Object> dto) {
        dao.insert(key, (String) dto.get("name"), (String) dto.get("description"), parseDate((String) dto.get("date")));
        return Response.accepted().build();
    }
}