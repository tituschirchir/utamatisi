package com.example.helloworld.resources;

import com.example.helloworld.models.domain.Account;
import com.example.helloworld.db.AccountDAO;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/accounts")
@Produces(MediaType.APPLICATION_JSON)
public class AccountResource {

    private final AccountDAO accountDAO;

    public AccountResource(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @POST
    @UnitOfWork
    public Account createAccount(Account account) {
        return accountDAO.create(account);
    }

    @GET
    @UnitOfWork
    public List<Account> listAccounts() {
        return accountDAO.findAll();
    }

    @GET
    @UnitOfWork
    @Path("/byid/{id}")
    public Account getAccountById(@PathParam("id")LongParam id) {
        return accountDAO.findById(id.get()).get();
    }

}
