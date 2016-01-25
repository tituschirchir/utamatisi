package com.example.helloworld.client;

import com.example.helloworld.auth.ExampleAuthenticator;
import com.example.helloworld.auth.ExampleAuthorizer;
import com.example.helloworld.db.AccountDAO;
import com.example.helloworld.db.PersonDAO;
import com.example.helloworld.filter.DateRequiredFeature;
import com.example.helloworld.models.User;
import com.example.helloworld.resources.*;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.jersey.setup.JerseyEnvironment;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.hibernate.SessionFactory;

/**
 * Created by titus.chirchir12
 * Date Created 1/24/2016.
 * Package: ${PACKAGE}
 */
public class ResourceRegister {

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
        jersey.register(new PeopleResource(dao));
        jersey.register(new AccountResource(new AccountDAO(sessionFactory)));
        jersey.register(new PersonResource(dao));
    }
}
