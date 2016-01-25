package com.example.helloworld.db;

import com.example.helloworld.models.domain.Person;
import com.google.common.base.Optional;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

public class PersonDAO extends AbstractDAO<Person> {
    public PersonDAO(SessionFactory factory) {
        super(factory);
    }

    public Optional<Person> findById(Long id) {
        return Optional.fromNullable(get(id));
    }

    public Person create(Person person) {
        Person persist = persist(person);
        return persist;
    }
    public void delete(int id) {
        Optional<Person> byId = findById((long) id);
        Person person = byId.get();
        person.delete();
        currentSession().saveOrUpdate(person);
    }

    public List<Person> findAll() {
        return list(namedQuery("Person.findAll"));
    }

    public List<Person> findByName(String name) {
        StringBuilder builder = new StringBuilder("%");
        builder.append(name).append("%");
        return list(
                namedQuery("Person.findByName")
                        .setParameter("name", builder.toString())
        );
    }
}

