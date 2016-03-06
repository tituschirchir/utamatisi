package com.utamatisi.app.client;

import com.codahale.metrics.health.HealthCheck;
import com.utamatisi.app.cli.RenderCommand;
import com.utamatisi.app.models.domain.Account;
import com.utamatisi.app.models.domain.Person;
import com.utamatisi.app.models.domain.Todo;
import com.utamatisi.app.models.domain.Tree;
import com.utamatisi.app.models.milestone.BusinessDateMilestonedImpl;
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
import org.eclipse.jetty.webapp.WebAppContext;

import java.io.FileInputStream;
import java.util.Map;
import java.util.Properties;

public class DropwizardGateway extends Application<DropwizardConfiguration> {

    public static final String APP_DIR = "src/main/webapp/";
    public static final String WEB_XML_LOCATION = "/WEB-INF/web.xml";
    private final HibernateBundle<DropwizardConfiguration> hibernateBundle =
            new HibernateBundle<DropwizardConfiguration>(Person.class, Account.class,Tree.class, Todo.class, BusinessDateMilestonedImpl.class) {
                @Override
                public DataSourceFactory getDataSourceFactory(DropwizardConfiguration configuration) {
                    return configuration.getDataSourceFactory();
                }
            };

    public static void main(String[] args) throws Exception {
        loadSystemProperties();
        startRestServices(args);
        startWebApp();
    }

    private static void loadSystemProperties() throws Exception {
        FileInputStream propFile = new FileInputStream("system.properties");
        Properties properties = new Properties(System.getProperties());
        properties.load(propFile);
        System.setProperties(properties);
    }

    private static void startRestServices(String[] args) throws Exception {
        new DropwizardGateway().run(args);
    }

    private static void startWebApp() throws Exception {
        String webPort = System.getProperty("webport");

        Server server = new Server(Integer.valueOf(webPort));
        WebAppContext webapp = new WebAppContext();
        webapp.setContextPath("/");
        webapp.setDescriptor(APP_DIR + WEB_XML_LOCATION);
        webapp.setResourceBase(APP_DIR);
        server.setHandler(webapp);
        server.start();
        server.join();
    }

    @Override
    public void initialize(Bootstrap<DropwizardConfiguration> bootstrap) {
        bootstrap.setConfigurationSourceProvider(new SubstitutingSourceProvider(
                        bootstrap.getConfigurationSourceProvider(),
                        new EnvironmentVariableSubstitutor(false))
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
        ResourceRegister.registerCors(environment);
    }

}
