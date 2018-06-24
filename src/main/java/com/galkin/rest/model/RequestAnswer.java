package com.galkin.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class RequestAnswer implements Serializable {

    private String contactId;
    private String applicationId;
    private String dtCreated;
    private String productName;

    public RequestAnswer() {

    }

    public RequestAnswer(final Long contactId, final Application application) {
        this();
        this.contactId = Long.toString(contactId);
        this.applicationId = Long.toString(application.getId());
        this.dtCreated = application.getDtCreated().toString();
        this.productName = application.getProductName();
    }

    @JsonProperty("CONTACT_ID")
    public String getContactId() {
        return this.contactId;
    }

    public void setContactId(final Long contactId) {
        this.contactId = Long.toString(contactId);
    }

    @JsonProperty("APPLICATION_ID")
    public String getApplicationId() {
        return this.applicationId;
    }

    public void setApplicationId(final Long applicationId) {
        this.applicationId = Long.toString(applicationId);
    }

    @JsonProperty("DT_CREATED")
    public String getDtCreated() {
        return this.dtCreated;
    }

    public void setProductName(final String productName) {
        this.productName = productName;
    }

    @JsonProperty("PRODUCT_NAME")
    public String getProductName() {
        return productName;
    }
}

