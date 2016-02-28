package com.utamatisi.app.resources;

import com.utamatisi.app.db.TodoDAO;
import com.utamatisi.app.models.domain.Todo;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by titus.chirchir12
 * Date Created 2/27/2016.
 * Package: ${PACKAGE}
 */
@Path("/todos")
@Produces(MediaType.APPLICATION_JSON)
public class TodoResource {
    private final TodoDAO todoDAO;

    public TodoResource(TodoDAO todoDAO) {
        this.todoDAO = todoDAO;
    }

    @GET
    @UnitOfWork
    public List<Todo> listTodos() {
        return todoDAO.findAll();
    }

    @POST
    @UnitOfWork
    public Todo createTodo(Todo person) {
        return todoDAO.create(person);
    }

}
