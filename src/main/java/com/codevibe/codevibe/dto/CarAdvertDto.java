package com.codevibe.codevibe.dto;

import java.util.Date;

import com.codevibe.codevibe.enums.FuelType;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CarAdvertDto 
{
    @NotNull(message = "Id can not be null")
    @Positive(message = "Id must be a positive number")
    private Long id;

    @NotNull(message = "Title can not be null")
    private String title;

    @NotNull(message = "Title can not be null")
    private FuelType fuelType;

    @NotNull(message = "Id can not be null")
    @Positive(message = "Id can not me negative")
    private Integer price;

    @NotNull(message = "Id can not be null")
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
