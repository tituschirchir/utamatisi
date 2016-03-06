package com.utamatisi.app.db;

import com.utamatisi.app.models.domain.Tree;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by titus.chirchir12
 * Date Created 3/5/2016.
 * Package: ${PACKAGE}
 */
public class TreeDAO extends AbstractDAO<Tree> {
    public TreeDAO(SessionFactory factory) {
        super(factory);
    }
    public List<Tree> findAll() {
        return list(namedQuery("Tree.findAll"));
    }

}
