package com.galkin.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "contacts")
public class Contact implements Serializable {
    @Id
    @Column(name = "CONTACT_ID", unique = true, nullable = false)
    private Long id;


    public Contact() {

    }

    public Contact(final Long id) {
        this();
        this.id = id;
    }

    @JsonProperty("CONTACT_ID")
    public Long getId() {
        return id;
    }


    public void setId(final Long id){
        this.id = id;
    }



    @Override
    public String toString() {
        return "CONTACT_ID:" + id + "\n";
    }
}
