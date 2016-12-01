package com.archipelago.utils;

import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;

import java.io.File;

/**
 * Created by szymon on 23.11.16.
 */
public class Neo4jUtils {

    public static Session getEmbeddedNeo4j() {

        String pathToDB = new File(".").getAbsolutePath();
        String actualPath = pathToDB.substring(0,pathToDB.length()-1)+"neo4jfx.db/";
        System.out.println(actualPath);
        String uri = "file://" + actualPath;

        Configuration configuration = new Configuration();

        configuration
                .driverConfiguration()
                .setDriverClassName("org.neo4j.ogm.drivers.embedded.driver.EmbeddedDriver")
                .setURI(uri);

        final SessionFactory factory = new SessionFactory(configuration, "com.archipelago.model");
        Session session = factory.openSession();

        return session;

    }

    public static Session getBoltNeo4j() {

        Configuration configuration = new Configuration();
        configuration
                .driverConfiguration()
                .setDriverClassName("org.neo4j.ogm.drivers.bolt.driver.BoltDriver")
                .setURI("bolt://neo4j:password@localhost")
                .setCredentials("neo4j","neo4j")
                .setEncryptionLevel("NONE")
                .setTrustStrategy("TRUST_ON_FIRST_USE")
                .setTrustCertFile("/tmp/cert");

        final SessionFactory factory = new SessionFactory(configuration, "com.archipelago.model");
        Session session = factory.openSession();


        return session;

    }

    public static Session getHttpNeo4j() {

        Configuration configuration = new Configuration();

        configuration
                .driverConfiguration()
                .setDriverClassName("org.neo4j.ogm.drivers.http.driver.HttpDriver")
                .setCredentials("neo4j","password")
                .setURI("http://localhost:7474");



        final SessionFactory factory = new SessionFactory(configuration, "com.archipelago.model");
        Session session = factory.openSession();


        return session;

    }
}
