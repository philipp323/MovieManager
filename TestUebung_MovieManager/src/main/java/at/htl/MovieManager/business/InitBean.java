package at.htl.MovieManager.business;

import at.htl.MovieManager.model.Movie;
import at.htl.MovieManager.model.Person;
import io.quarkus.runtime.StartupEvent;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class InitBean {

    @Inject
    @RestClient
    MovieManagerService movieManagerService;

    @Inject
    MovieDAO movieDAO;

    @Inject
    PersonDAO personDAO;

    @Transactional
    void init(@Observes StartupEvent ev)
    {
        System.err.println("Init started!");
        JsonArray peopleJsonArray = movieManagerService.getPeople(20, 0);
        for (JsonValue personJson: peopleJsonArray) {
            JsonObject personJsonObject = personJson.asJsonObject();

            String name = personJsonObject.getString("name");
            String gender = personJsonObject.getString("gender");
            String age = personJsonObject.getString("age");
            Person person = new Person(name, gender, age);
            personDAO.persist(person);
        }


        JsonArray filmsJsonArray = movieManagerService.getFilms(20,0);
        for (JsonValue filmJson: filmsJsonArray) {
            JsonObject movieJsonObject = filmJson.asJsonObject();

            String title = movieJsonObject.getString("title");
            String dir = movieJsonObject.getString("director");
            int date = movieJsonObject.isNull("release_date") ? 0:Integer.parseInt(movieJsonObject.getString("release_date"));
            int score = movieJsonObject.isNull("rt_score") ? 0:Integer.parseInt(movieJsonObject.getString("rt_score"));
            List<Person> persons = personDAO.getAll(0, 100);
            Movie movie = new Movie(title, dir, date, score, persons);
            movieDAO.persist(movie);
        }
    }
}
