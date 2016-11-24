package com.archipelago.entities;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * Created by szymon on 24.11.16.
 */
@NodeEntity
public class Person {

    @GraphId
    private Long id;

    public Person() {
    }

    public Long getId() {

        return id;
    }

    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
