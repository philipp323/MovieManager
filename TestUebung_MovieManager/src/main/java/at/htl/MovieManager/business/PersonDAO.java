package at.htl.MovieManager.business;

import at.htl.MovieManager.model.Person;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class PersonDAO {
    @PersistenceContext
    EntityManager em;

    @Transactional
    public Person persist(Person entity){
        em.persist(entity);
        return entity;
    }

    public List<Person> getAll(int offset, int limit) {
        TypedQuery<Person> query = em.createNamedQuery("Person.findAll",Person.class);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    public Person getById(long id) {
        TypedQuery<Person> query = em.createNamedQuery("Person.findById",Person.class);
        query.setParameter("Id",id);
        return query.getSingleResult();
    }
}
