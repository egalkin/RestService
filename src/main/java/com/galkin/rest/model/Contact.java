package com.galkin.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "contacts")
public class Contact implements Serializable {
    @Id
    @Column(name = "CONTACT_ID", unique = true)
    private long id;


    public Contact() {

    }

    public Contact(final int id) {
        this();
        this.id = id;
    }

    @JsonProperty("CONTACT_ID")
    public long getId() {
        return id;
    }


    public void setId(long id){
        this.id = id;
    }



    @Override
    public String toString() {
        return "CONTACT_ID:" + id + "\n";
    }
}
