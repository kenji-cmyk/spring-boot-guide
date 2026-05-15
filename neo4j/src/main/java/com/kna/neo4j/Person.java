package com.kna.neo4j;


import jakarta.annotation.Nullable;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.*;
import java.util.stream.Collectors;

@Node
public class Person {

    @Id
    @GeneratedValue
    private @Nullable Long id;

    private String name;

    private Person(){

    }

    public Person(String name){
        this.name = name;
    }

    @Relationship(type = "REFERED")
    public Set<Person> refferals;

    public void referred (Person person){
        if(refferals == null){
            refferals = new HashSet<>();
        }
        refferals.add(person);
    }
    public String toString(){
        return this.name + "refered => "
                + Optional.ofNullable(this.refferals).orElse(
                Collections.emptySet()).stream()
                .map(Person::getName)
                .toList();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
