package at.htl.MovieManager.rest;

import at.htl.MovieManager.business.MovieDAO;
import at.htl.MovieManager.model.Movie;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

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

    /*
         {
         "director":"Philipp Auinger",
         "persons":[],
         "release_date":2019,
         "rt_score":100,
         "title":"Das neue Abenteuer!"
         }
  */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response postMovie(Movie movie){
        movieDAO.persist(movie);
        return Response.created(URI.create("http://localhost:8080/api/product/" + movie.getId())).build();
    }

    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response deleteMovie(@PathParam("id") long id){
        Movie entity = movieDAO.find(Movie.class, id);
        if(entity != null){
            entity = movieDAO.merge(entity);
            movieDAO.remove(entity);
        }
        return Response.noContent().build();
    }

    /*
        {
         "director":"Philipp Auinger Jr.",
         "id":21,
         "persons":[],
         "release_date":2019,
         "rt_score":100,
         "title":"Das neue neue Abenteuer!"
         }
    */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(Movie movie){
        movie = movieDAO.merge(movie);
        //em.flush();
        //em.refresh(product);
        return Response.ok().entity(movie).build();
    }
}