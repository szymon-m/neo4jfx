package com.archipelago.model;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by szymon on 24.11.16.
 */
@NodeEntity
public class Person {

    @GraphId
    private Long id;

    public Person() {

        this.department = new HashSet<>();
    }



    public Long getId() {

        return id;
    }

    private String name;

    public Person(String name) {

        this();
        this.name = name;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Relationship(type="EMPLOYED_BY")
    Set<Department> department;

    public Set<Department> getDepartment() {
        return department;
    }

    public void setDepartment(Set<Department> department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department=" + department.size() +
                '}';
    }
}
