package com.utamatisi.app.resources;

import com.utamatisi.app.models.domain.Account;
import com.utamatisi.app.db.AccountDAO;
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
