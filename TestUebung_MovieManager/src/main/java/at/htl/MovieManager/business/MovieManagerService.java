package at.htl.MovieManager.business;

import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.json.JsonArray;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@RegisterRestClient
@ClientHeaderParam(name = "Testparam", value="test")
public interface MovieManagerService {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/films")
    JsonArray getFilms(@QueryParam("count") int count, @QueryParam("offset") int offset);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/people")
    JsonArray getPeople(@QueryParam("count") int count, @QueryParam("offset") int offset);
}