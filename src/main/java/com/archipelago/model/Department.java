package com.archipelago.model;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by szymon on 05.12.16.
 */

@NodeEntity
public class Department {

    private Long id;

    private String deptName;

    @Relationship(type = "EMPLOYS")
    Set<Person> employees = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Set<Person> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Person> employees) {
        this.employees = employees;
    }

    public void addEmployee(Person person) {

        this.employees.add(person);
    }

    public Department() {
//        this.employees = new HashSet<>();

    }

    public Department(String deptName) {
        this();
        this.deptName = deptName;

    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", deptName='" + deptName + '\'' +
                ", employees=" + employees.size() +
                '}';
    }
}
