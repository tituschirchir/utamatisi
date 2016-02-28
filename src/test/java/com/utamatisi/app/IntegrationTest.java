package com.utamatisi.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.utamatisi.app.client.DropwizardConfiguration;
import com.utamatisi.app.client.DropwizardGateway;
import com.utamatisi.app.models.domain.Person;
import io.dropwizard.jackson.Jackson;
import io.dropwizard.testing.ConfigOverride;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.io.File;
import java.io.IOException;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;

public class IntegrationTest {

    private static final String TMP_FILE = createTempFile();
    private static final String CONFIG_PATH = ResourceHelpers.resourceFilePath("test-example.yml");

    @ClassRule
    public static final DropwizardAppRule<DropwizardConfiguration> RULE = new DropwizardAppRule<>(
            DropwizardGateway.class, CONFIG_PATH,
            ConfigOverride.config("database.url", "jdbc:h2:" + TMP_FILE));

    private Client client;

    @BeforeClass
    public static void migrateDb() throws Exception {
        RULE.getApplication().run("db", "migrate", CONFIG_PATH);
    }

    @Before
    public void setUp() throws Exception {
        client = ClientBuilder.newClient();
    }

    @After
    public void tearDown() throws Exception {
        client.close();
    }

    private static String createTempFile() {
        try {
            return File.createTempFile("test-example", null).getAbsolutePath();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void testPostPerson() throws Exception {
        /*final Person person = new Person("Dr. IntegrationTest", "Chief", "Wizard");
        person.setId(23);
        Person newPerson = client.target("http://localhost:" + RULE.getLocalPort() + "/people")
                .request()
                .post(Entity.entity(person, MediaType.APPLICATION_JSON))
                .readEntity(Person.class);
        assertThat(newPerson.getId()).isNotNull();*//*
        assertThat(newPerson.getLastName()).isEqualTo(person.getLastName());
        assertThat(newPerson.getFirstName()).isEqualTo(person.getFirstName());
        assertThat(newPerson.getProfession()).isEqualTo(person.getProfession());*/
    }

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void serializesToJSON() throws Exception {
        final Person person = new Person("Walter", "White", "Cook");
        File file = new File("C:\\Users\\titus.chirchir12\\IdeaProjects\\utamatisi\\src\\test\\resources\\fixtures\\actualPerson.json");
        if(!file.exists())
        {
            file.createNewFile();
        }
        MAPPER.writeValue(file, person);
        Person readPerson = MAPPER.readValue(fixture("fixtures\\actualPerson.json"), Person.class);
        assertThat(readPerson.getLastName()).isEqualTo(person.getLastName());

    }

    @Test
    public void deserializesFromJSON() throws Exception {
        final Person person = new Person("Walter", "White", "Cook");
        assertThat(MAPPER.readValue(fixture("fixtures/person.json"), Person.class))
                .isEqualTo(person);
    }
}
