package at.htl.MovieManager.model;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorColumn
@NamedQueries({
        @NamedQuery(name = "Movie.findAll", query = "Select m from Movie m"),
        @NamedQuery(name = "Movie.findById", query = "Select m from Movie m where m.id = :id")
})
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String director;
    private int release_date;
    private int rt_score;

    @ManyToMany(cascade = {CascadeType.REFRESH, CascadeType.MERGE},fetch = FetchType.EAGER)
    private List<Person> persons;

    public Movie() {
    }

    public Movie(String title, String director, int release_date, int rt_score, List<Person> persons) {
        this.title = title;
        this.director = director;
        this.release_date = release_date;
        this.rt_score = rt_score;
        this.persons = persons;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getRelease_date() {
        return release_date;
    }

    public void setRelease_date(int release_date) {
        this.release_date = release_date;
    }

    public int getRt_score() {
        return rt_score;
    }

    public void setRt_score(int rt_score) {
        this.rt_score = rt_score;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
}
