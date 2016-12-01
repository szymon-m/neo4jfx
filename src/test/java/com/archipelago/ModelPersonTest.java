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
    }


    @Test
    public void shouldPersistPerson() {

        Person pr1 = new Person("Someone");

        session.save(pr1);

        String query = "MATCH (s:Person) return s";
        System.out.println(session.query(query, Collections.EMPTY_MAP));

    }

}
