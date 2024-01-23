package com.codevibe.codevibe.models;

import java.util.Date;

import com.codevibe.codevibe.enums.FuelType;
import com.fasterxml.jackson.annotation.JsonFormat;

public class CarAdvert {
    private Long id;
    private String title;
    private FuelType fuelType;
    private Integer price;
    private Boolean isNew;
    private Integer mileage;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date firstRegistration;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Boolean getIsNew() {
        return isNew;
    }

    public void setIsNew(Boolean isNew) {
        this.isNew = isNew;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public Date getFirstRegistration() {
        return firstRegistration;
    }

    public void setFirstRegistration(Date firstRegistration) {
        this.firstRegistration = firstRegistration;
    }


    
}
