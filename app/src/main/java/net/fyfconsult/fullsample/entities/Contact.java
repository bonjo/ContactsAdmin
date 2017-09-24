package net.fyfconsult.fullsample.entities;

import java.io.Serializable;

/**
 * Created by Bonjo on 19/09/2017.
 */

public class Contact implements Serializable {
    private int id;
    private String firstName;
    private String lastName;
    private String phone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Contact(){}

    public Contact(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Contact(String firstName, String lastName, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    public Contact(int id, String firstName, String lastName, String phone){
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.id = id;
    }
}