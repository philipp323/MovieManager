package at.htl.MovieManager.rest;

import at.htl.MovieManager.business.MovieDAO;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("movie")
@Produces(MediaType.APPLICATION_JSON)
public class MovieEndpoint {
    @Inject
    MovieDAO movieDAO;

    //http://localhost:8080/movie?limit=1&offset=1
    @GET
    @Counted(name = "getAllMovies")
    public Response getMovies(@QueryParam("offset")@DefaultValue("1")int offset, @QueryParam("limit")@DefaultValue("10")int limit){
        return Response.ok(movieDAO.getAll(offset,limit)).build();
    }

    @GET
    @Path("/{id}")
    public Response getMoviesById(@PathParam("id") long id){
        return Response.ok(movieDAO.getById(id)).build();
    }
}