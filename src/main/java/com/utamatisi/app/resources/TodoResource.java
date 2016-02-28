package com.utamatisi.app.resources;

import com.utamatisi.app.db.TodoDAO;
import com.utamatisi.app.models.domain.Todo;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
    public Todo createTodo(Todo todo) {
        return todoDAO.create(todo);
    }
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public Response deleteTodo(@QueryParam("id")LongParam id)
    {
        todoDAO.delete(id.get());
        return Response.ok().build();
    }

}
