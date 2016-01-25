package com.example.helloworld.client;

import com.codahale.metrics.health.HealthCheck;
import com.example.helloworld.cli.RenderCommand;
import com.example.helloworld.models.domain.Account;
import com.example.helloworld.models.milestone.BusinessDateMilestonedImpl;
import com.example.helloworld.models.domain.Person;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.jersey.setup.JerseyEnvironment;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.eclipse.jetty.webapp.WebAppContext;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;
import java.util.Map;

public class DropwizardGateway extends Application<DropwizardConfiguration> {

    public static final String APP_DIR = "src/main/webapp/";
    public static final String ALLOWED_HEADERS = "X-Requested-With,Content-Type,Accept,Origin";
    public static final String ALLOWED_METHODS = "OPTIONS,GET,PUT,POST,DELETE,HEAD";
    public static final String WEB_XML_LOCATION = "/WEB-INF/web.xml";
    private final HibernateBundle<DropwizardConfiguration> hibernateBundle =
            new HibernateBundle<DropwizardConfiguration>(Person.class, Account.class, BusinessDateMilestonedImpl.class) {
                @Override
                public DataSourceFactory getDataSourceFactory(DropwizardConfiguration configuration) {
                    return configuration.getDataSourceFactory();
                }
            };
    public static void main(String[] args) throws Exception {
        startRestServices(args);
        startWebApp();
    }

    private static void startRestServices(String[] args) throws Exception {
        new DropwizardGateway().run(args);
    }

    @Override
    public void initialize(Bootstrap<DropwizardConfiguration> bootstrap) {
        // Enable variable substitution with environment variables
        bootstrap.setConfigurationSourceProvider(new SubstitutingSourceProvider(
                        bootstrap.getConfigurationSourceProvider(),
                        new EnvironmentVariableSubstitutor(false)
                )
        );

        bootstrap.addCommand(new RenderCommand());
        bootstrap.addBundle(new AssetsBundle());
        bootstrap.addBundle(new MigrationsBundle<DropwizardConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(DropwizardConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });
        bootstrap.addBundle(hibernateBundle);
        bootstrap.addBundle(new ViewBundle<DropwizardConfiguration>() {
            @Override
            public Map<String, Map<String, String>> getViewConfiguration(DropwizardConfiguration configuration) {
                return configuration.getViewRendererConfiguration();
            }
        });
    }

    @Override
    public void run(DropwizardConfiguration configuration, Environment environment) {

        JerseyEnvironment jersey = environment.jersey();
        environment.healthChecks().register("template", new HealthCheck() {
            @Override
            protected Result check() throws Exception {
                return Result.healthy();
            }
        });
        ResourceRegister.registerResources(jersey);
        ResourceRegister.registerHibernateResources(jersey, this.hibernateBundle);

        final FilterRegistration.Dynamic cors = environment.servlets().addFilter("CORS", CrossOriginFilter.class);
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", ALLOWED_HEADERS);
        cors.setInitParameter("allowedMethods", ALLOWED_METHODS);

        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
    }

    private static void startWebApp() throws Exception {
        String webPort = System.getenv("PORT");
        if (webPort == null || webPort.isEmpty()) {
            webPort = "9000";
        }
        Server server = new Server(Integer.valueOf(webPort));
        WebAppContext webapp = new WebAppContext();
        webapp.setContextPath("/");
        webapp.setDescriptor(APP_DIR + WEB_XML_LOCATION);
        webapp.setResourceBase(APP_DIR);
        server.setHandler(webapp);
        server.start();
        server.join();
    }

}
