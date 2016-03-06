package com.utamatisi.app.resources;

import com.utamatisi.app.db.TreeDAO;
import com.utamatisi.app.models.domain.Tree;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by titus.chirchir12
 * Date Created 3/5/2016.
 * Package: ${PACKAGE}
 */
@Path("/trees")
public class TreeResource {

    private final TreeDAO treeDAO;

    public TreeResource(TreeDAO treeDAO)
    {
        this.treeDAO = treeDAO;
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public List<Tree> listTrees() {
        return treeDAO.findAll();
    }
}
