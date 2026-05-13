package com.kna.springrestservice;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Objects;


@Entity
public class Employee {

    private @Id
    @GeneratedValue long id;
    private String name;
    private String role;

    public Employee (){}

    public Employee (String name, String role){
        this.name = name;
        this.role = role;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals (Object o){

        if (this == o){
            return true;
        }

        if (! (o instanceof Employee)){
            return false;
        }

        Employee employee = (Employee) o;

        return Objects.equals(this.id, employee.id) && Objects.equals(this.name, employee.name) && Objects.equals(this.role, employee.role);
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.id, this.name, this.role);
    }


    @Override
    public String toString(){
        return "Employee { id = " + this.id + ", name = " + this.name + ", role = " + this.role + "}";
    }
}


