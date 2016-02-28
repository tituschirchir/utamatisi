package com.utamatisi.app.resources;

import com.utamatisi.app.models.domain.Person;
import com.utamatisi.app.db.PersonDAO;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.IntParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/people")
public class PeopleResource {

    private final PersonDAO peopleDAO;

    public PeopleResource(PersonDAO peopleDAO) {
        this.peopleDAO = peopleDAO;
    }

    @POST
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public Person createPerson(Person person) {
        return peopleDAO.create(person);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @Path("/{id}")
    public Response deletePerson(@PathParam("id") IntParam id) {
        peopleDAO.delete(id.get());
        return Response.ok().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @Path("/search/{name}")
    public List<Person> getPersonByName(@PathParam("name") String name) {
        return peopleDAO.findByName(name);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public List<Person> listPeople() {
        return peopleDAO.findAll();
    }

}
