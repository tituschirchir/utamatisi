package com.utamatisi.app.client;

import com.utamatisi.app.auth.ExampleAuthenticator;
import com.utamatisi.app.auth.ExampleAuthorizer;
import com.utamatisi.app.db.AccountDAO;
import com.utamatisi.app.db.PersonDAO;
import com.utamatisi.app.db.TodoDAO;
import com.utamatisi.app.db.TreeDAO;
import com.utamatisi.app.filter.DateRequiredFeature;
import com.utamatisi.app.models.User;
import com.utamatisi.app.resources.*;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.jersey.setup.JerseyEnvironment;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.hibernate.SessionFactory;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

/**
 * Created by titus.chirchir12
 * Date Created 1/24/2016.
 * Package: ${PACKAGE}
 */
public class ResourceRegister {
    private static final String ALLOWED_HEADERS = "X-Requested-With,Content-Type,Accept,Origin";
    private static final String ALLOWED_METHODS = "OPTIONS,GET,PUT,POST,DELETE,HEAD";
    public static void registerResources(JerseyEnvironment jersey) {
        jersey.register(DateRequiredFeature.class);
        jersey.register(new AuthDynamicFeature(new BasicCredentialAuthFilter.Builder<User>()
                .setAuthenticator(new ExampleAuthenticator())
                .setAuthorizer(new ExampleAuthorizer())
                .setRealm("SUPER SECRET STUFF")
                .buildAuthFilter()));
        jersey.register(new AuthValueFactoryProvider.Binder<>(User.class));
        jersey.register(RolesAllowedDynamicFeature.class);
        jersey.register(new ViewResource());
        jersey.register(new ProtectedResource());
        jersey.register(new FilteredResource());
    }

    public static void registerHibernateResources(JerseyEnvironment jersey, HibernateBundle<DropwizardConfiguration> hibernateBundle) {
        SessionFactory sessionFactory = hibernateBundle.getSessionFactory();
        final PersonDAO dao = new PersonDAO(sessionFactory);
        final TreeDAO treeDAO = new TreeDAO(sessionFactory);
        final TodoDAO todoDAO = new TodoDAO(sessionFactory);
        jersey.register(new PeopleResource(dao));
        jersey.register(new AccountResource(new AccountDAO(sessionFactory)));
        jersey.register(new PersonResource(dao));
        jersey.register(new TodoResource(todoDAO));
        jersey.register(new TreeResource(treeDAO));
    }
    public static void registerCors(Environment environment) {
        final FilterRegistration.Dynamic cors = environment.servlets().addFilter("CORS", CrossOriginFilter.class);
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", ALLOWED_HEADERS);
        cors.setInitParameter("allowedMethods", ALLOWED_METHODS);
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
    }
}
