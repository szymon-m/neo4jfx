package com.archipelago;


import com.archipelago.model.Department;
import com.archipelago.model.Person;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.neo4j.ogm.model.Result;
import org.neo4j.ogm.session.Session;

import java.util.*;

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
    public void widerPersistance() {

        Map<String, Department> departments = createDepartments("Social Manipulation", "Creative", "Wonderland");
        Map<String, Person> persons = createPersons("Someone #1", "Someone #2","Someone #3", "Someone #4");

//        assignPersonToDepartment(0L,3L);
//        assignPersonToDepartment(0L,4L);
//        assignPersonToDepartment(0L,5L);
//        assignPersonToDepartment(0L,6L);
//        assignPersonToDepartment(1L,4L);
//        assignPersonToDepartment(1L,5L);
//        assignPersonToDepartment(1L,6L);
//        assignPersonToDepartment(2L,3L);
//        assignPersonToDepartment(2L,4L);

          assignPersonToDepartment("Wonderland","Someone #4");

        // department <-[:EMPLOYED_BY]- person
//        assign(departments.get("Social Manipulation").getEmployees(), persons, "Someone #1","Someone #2");
//        assign(departments.get("Creative").getEmployees(), persons, "Someone #3","Someone #4");
//        assign(departments.get("Wonderland").getEmployees(), persons, "Someone #2","Someone #1");





        printDepartmentsAndPersons();

    }

    @Test
    @Ignore
    public void shouldPersistPerson() {

       Department dp1 = session.load(Department.class, 8L);

//        Department dp1 = new Department("HR");
//        Person pr1 = new Person("Someone #1");
        Person pr1 = session.load(Person.class, 5L);

        dp1.getEmployees().add(pr1);
        pr1.getDepartment().add(dp1);

//        pr1.getDepartment().add(dp1);

        //session.save(dp1);

        session.save(dp1);
        session.save(pr1);

        for(Person pr : dp1.getEmployees()) {

            System.out.print("ID: " +
                    pr.getId() +" "
                    + pr.getName() + " deptNo: "
                    );

            for (Department dp : pr.getDepartment()) {

                System.out.print(" "+dp.getId());
            }
            System.out.println();

        }

        //Person pr2 = new Person( "Someone @2");

//        Person pr2 = session.load(Person.class,2L,1);
//        Person pr3 = session.load(Person.class,3L, 1);
//

//       Set<Person> dep2set = new HashSet<>();
//        dep2set.add(pr1);
//        dep2set.add(pr2);
//        dep2set.add(pr3);
//
//        dp1.setEmployees(dep2set);



//
//        session.save(pr1);

        printDepartmentsAndPersons();


//        String query3 = "MATCH (p:Person)-[:EMPLOYED_BY]->(d:Department) where ID(d)=8 return p";
//
//        Result results3 = session.query(query3, Collections.EMPTY_MAP);
//
//        //Iterable<Map<String, Object>> results = session.query(query, Collections.EMPTY_MAP);
//
//        for(Map<String, Object> result:results3) {
//
//            System.out.println(result);
//
//
//        }
    }


    private void printDepartmentsAndPersons() {
        String query = "MATCH (s:Person) return s";
        Result results = session.query(query, Collections.EMPTY_MAP);
        //Iterable<Map<String, Object>> results = session.query(query, Collections.EMPTY_MAP);
        for(Map<String, Object> result:results) {
            System.out.println(result);
        }

        String query2 = "MATCH (s:Department) return s";
        Result results2 = session.query(query2, Collections.EMPTY_MAP);
        //Iterable<Map<String, Object>> results = session.query(query, Collections.EMPTY_MAP);
        for(Map<String, Object> result:results2) {
            System.out.println(result);
        }
    }

    private Map<String, Department> createDepartments(String ... names) {

        Map<String, Department> map = new HashMap<>();
        for(String name: names) {

            Department dp = new Department(name);
            map.put(name, dp);
            session.save(dp);
        }
        return map;

    }

    private Map<String, Person> createPersons(String ... names) {

        Map<String, Person> map = new HashMap<>();
        for(String name: names) {

            Person pr = new Person(name);
            map.put(name, pr);
            session.save(pr);
        }
        return map;

    }

    private void assign(Set to, Map from, String... names) {
        for (String name : names) {
            to.add(from.get(name));
        }
        session.save(to);
    }

    private void assignPersonToDepartment(String department, String person) {

        // not checking for unique constraints !!
        Long firstDepartment = null;
        Long firstPerson = null;

        Map<String, Object> params1 = new HashMap<>();
        params1.put("name", department);
        String query1 = "match (d:Department) where d.deptName={name} return d";
        Iterable<Department> results1 = session.query(Department.class, query1, params1);

        for(Department dep:results1) {

            if(firstDepartment == null) {
                System.out.println("Department ID:"+dep.getId());
                firstDepartment = dep.getId();
                break;
            } else { break; }


        }

        Map<String, Object> params2 = new HashMap<>();
        params2.put("name", person);
        String query2 = "match (p:Person) where p.name={name} return p";
        Iterable<Person> results2 = session.query(Person.class, query2, params2);

        for(Person per:results2) {

            if (firstPerson == null) {
                System.out.println("Person ID:" + per.getId());
                firstPerson = per.getId();
                break;
            } else {
                break;
            }
        }

        Department dep = session.load(Department.class, (Long) firstDepartment);
        Person per = session.load(Person.class, (Long) firstPerson);

        dep.getEmployees().add(per);
        per.getDepartment().add(dep);

        session.save(dep);
        session.save(per);



    }

    private void assignPersonToDepartment(Long department, Long person) {

        Department dp = session.load(Department.class, department);
        Person pr = session.load(Person.class, person);

        dp.getEmployees().add(pr);
        pr.getDepartment().add(dp);

        session.save(dp);
        session.save(pr);


    }

    private void assignPersonToDepartment(Department department, Person person) {

        department.getEmployees().add(person);
        person.getDepartment().add(department);

        session.save(department);
        session.save(person);


     }

}
