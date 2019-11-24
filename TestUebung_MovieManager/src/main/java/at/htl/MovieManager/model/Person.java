package at.htl.MovieManager.model;

import javax.persistence.*;

@Entity
@DiscriminatorColumn
@NamedQueries({
        @NamedQuery(name = "Person.findAll", query = "Select p from Person p"),
        @NamedQuery(name = "Person.findById", query = "Select p from Person p where p.id = :id")
})
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String gender;
    private String age;

    public Person() {
    }

    public Person(String name, String gender, String age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
