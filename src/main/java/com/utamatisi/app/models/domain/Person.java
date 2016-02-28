package com.utamatisi.app.models.domain;

import com.utamatisi.app.models.milestone.BusinessDateMilestonedImpl;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "person")
@NamedQueries({
        @NamedQuery(name = "Person.findAll", query = Person.GENERIC_SELECT),
        @NamedQuery(name = "Person.deleteById", query = Person.DELETE_BY_ID),
        @NamedQuery(name = "Person.findByName", query = Person.QUERY_BY_NAME)
})
public class Person extends BusinessDateMilestonedImpl {
    public static final String GENERIC_SELECT = "select p from Person p where p.businessDateTo >= CURRENT_TIMESTAMP";
    public static final String QUERY_BY_NAME = GENERIC_SELECT
            + " and lower(p.firstName) like lower(:name) or lower(p.lastName) like lower(:name)";
    public static final String DELETE_BY_ID = "delete from Person where id=:id";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "profession")
    private String profession;

    public Person() {
    }

    public Person(String firstName, String lastName, String profession) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.profession = profession;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Person)) {
            return false;
        }

        final Person that = (Person) o;

        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.firstName, that.firstName) &&
                Objects.equals(this.lastName, that.lastName) &&
                Objects.equals(this.profession, that.profession);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, profession);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
