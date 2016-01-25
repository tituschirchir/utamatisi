package com.example.helloworld.resources;

import com.example.helloworld.models.domain.Person;
import com.example.helloworld.db.PersonDAO;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.IntParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/people")
@Produces(MediaType.APPLICATION_JSON)
public class PeopleResource {

    private final PersonDAO peopleDAO;

    public PeopleResource(PersonDAO peopleDAO) {
        this.peopleDAO = peopleDAO;
    }

    @POST
    @UnitOfWork
    public Person createPerson(Person person) {
        return peopleDAO.create(person);
    }

    @DELETE
    @UnitOfWork
    @Path("/{id}")
    public Response deletePerson(@PathParam("id") IntParam id) {
        peopleDAO.delete(id.get());
        return Response.ok().build();
    }

    @GET
    @UnitOfWork
    @Path("/search/{name}")
    public List<Person> getPersonByName(@PathParam("name") String name) {
        return peopleDAO.findByName(name);
    }

    @GET
    @UnitOfWork
    public List<Person> listPeople() {
        return peopleDAO.findAll();
    }

}
