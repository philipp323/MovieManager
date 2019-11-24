package at.htl.MovieManager.business;

import at.htl.MovieManager.model.Movie;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class MovieDAO {
    @PersistenceContext
    EntityManager em;

    @Transactional
    public Movie persist(Movie entity){
        em.persist(entity);
        return entity;
    }

    public List<Movie> getAll(int offset, int limit) {
        TypedQuery<Movie> query = em.createNamedQuery("Movie.findAll",Movie.class);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    public Movie getById(long id) {
        TypedQuery<Movie> query = em.createNamedQuery("Movie.findById",Movie.class);
        query.setParameter("Id",id);
        return query.getSingleResult();
    }

    public Movie find(Class<Movie> movieClass, long id)
    {
        //movieClass = Movie.class
        return em.find(movieClass,id);
    }

    public Movie merge(Movie entity)
    {
        return em.merge(entity);
    }

    public void remove(Movie entity)
    {
        em.remove(entity);
    }
}
