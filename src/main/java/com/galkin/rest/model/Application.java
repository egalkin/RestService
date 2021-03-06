package com.galkin.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "applications")
public class Application {
    @Id
    @Column(name = "APPLICATION_ID", unique = true, nullable = false)
    private Long id;

    @Column(name = "PRODUCT_NAME", nullable = false)
    private String productName;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_CREATED", nullable = false, updatable = false)
    @CreatedDate
    private Date dtCreated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CONTACT_ID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Contact contact;

    public Application() {

    }

    public Application(final Long id, final String productName, final Date dtCreated) {
        this();
        this.id = id;
        this.productName = productName;
        this.dtCreated = dtCreated;
    }

    @JsonProperty("DT_CREATED")
    public Date getDtCreated() {
        return this.dtCreated;
    }

    public void setDtCreated(final Date dtCreated) {
        this.dtCreated = dtCreated;
    }


    public void setId(final Long id) {
        this.id = id;
    }

    @JsonProperty("APPLICATION_ID")
    public Long getId() {
        return id;
    }

    public void setProductName(final String name) {
        this.productName = name;
    }

    @JsonProperty("PRODUCT_NAME")
    public String getProductName() {
        return this.productName;
    }

    public void setContact(final Contact contact) {
        this.contact = contact;
    }

    public Contact getContact() {
        return this.contact;
    }


    @Override
    public String toString() {
        return "APPLICATION_ID:" + " " + this.id + "\n" + "PRODUCT_NAME:" + " " + this.productName + "\n" + "DR_CREATED:" + " " + this.dtCreated.toString() + "\n";
    }
}
