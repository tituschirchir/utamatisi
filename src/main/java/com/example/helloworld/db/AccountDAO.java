package com.example.helloworld.db;

import com.example.helloworld.models.domain.Account;
import com.google.common.base.Optional;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

public class AccountDAO extends AbstractDAO<Account> {
    public AccountDAO(SessionFactory factory) {
        super(factory);
    }

    public Optional<Account> findById(Long id) {
        return Optional.fromNullable(get(id));
    }

    public Account create(Account account) {
        return persist(account);
    }

    public List<Account> findAll() {
        return list(namedQuery("Account.findAll"));
    }
}
