package com.company.myorders.mobile.model;

import oracle.ateam.sample.mobile.v2.persistence.model.Entity;


public class Result extends Entity {

    private String locationCode;
    private String locationFullName;


    public String getLocationCode() {
        return this.locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public String getLocationFullName() {
        return this.locationFullName;
    }

    public void setLocationFullName(String locationFullName) {
        this.locationFullName = locationFullName;
    }


}
