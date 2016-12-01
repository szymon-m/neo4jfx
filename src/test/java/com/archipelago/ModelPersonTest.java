package com.archipelago;


import com.archipelago.model.Person;
import org.junit.BeforeClass;
import org.junit.Test;
import org.neo4j.ogm.session.Session;

import java.util.Collections;

import static com.archipelago.utils.Neo4jUtils.*;

/**
 * Created by szymon-m on 24.11.16.
 */
public class ModelPersonTest {

    private static Session session;

    @BeforeClass
    public static void setupTestServer() {

          session = getEmbeddedNeo4j();

//        String pathToDB = new File(".").getAbsolutePath();
//        String actualPath = pathToDB.substring(0,pathToDB.length()-1)+"neo4jfx.db/";
//        System.out.println(actualPath);
//        String uri = "file://" + actualPath;
//
//        Configuration configuration = new Configuration();
//
//        configuration
//                .driverConfiguration()
//                .setDriverClassName("org.neo4j.ogm.drivers.embedded.driver.EmbeddedDriver")
//                .setURI(uri);
//
//        DriverConfiguration config = new DriverConfiguration(configuration);

    }


    @Test
    public void shouldPersistPerson() {

        Person pr1 = new Person("Someone");

        session.save(pr1);

        String query = "MATCH (s:Person) return s";
        System.out.println(session.query(query, Collections.EMPTY_MAP));

    }

}
