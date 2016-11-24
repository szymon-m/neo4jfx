package com.archipelago;


import com.archipelago.entities.Person;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.neo4j.ogm.session.Session;

import java.util.Collections;
import java.util.Map;

import static com.archipelago.neo4jsetup.Neo4jSetup.*;

/**
 * Created by szymon on 24.11.16.
 */
public class TestServerTest {

    private static Session session;

    @BeforeClass
    public static void setupTestServer() {

          session = getHttpNeo4j();

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

        Person pr1 = new Person("Szymon");

        session.save(pr1);

        String query = "MATCH (s:Person) return s";
        System.out.println(session.query(query, Collections.EMPTY_MAP));

    }

}
