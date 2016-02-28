package com.utamatisi.app.db;

import com.google.common.base.Optional;
import com.utamatisi.app.models.domain.Todo;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

public class TodoDAO extends AbstractDAO<Todo> {
    public TodoDAO(SessionFactory factory) {
        super(factory);
    }

    public Optional<Todo> findById(Long id) {
        return Optional.fromNullable(get(id));
    }

    public Todo create(Todo Todo) {
        Todo persist = persist(Todo);
        return persist;
    }
    public List<Todo> findAll() {
        return list(namedQuery("Todo.findAll"));
    }

    public void delete(Long id) {
        Todo todo = findById(id).get();
        todo.delete();
        currentSession().saveOrUpdate(todo);
    }

}

