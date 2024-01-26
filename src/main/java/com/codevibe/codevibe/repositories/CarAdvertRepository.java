package com.codevibe.codevibe.repositories;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.codevibe.codevibe.domainModels.CarAdvert;
import com.codevibe.codevibe.enums.FuelType;

@Repository
public class CarAdvertRepository {

    List<CarAdvert> allCarAdverts;
    public List<CarAdvert> getAllCarAdverts(String filedName)
    {
        if(allCarAdverts==null)
        {
            allCarAdverts=new ArrayList<>();
            CarAdvert advert1=new CarAdvert();
            advert1.setId((long ) 1);
            advert1.setTitle("Naslov za oglas broj 1");
            advert1.setFuelType(FuelType.DIEZEL);
            advert1.setIsNew(false);
            advert1.setFirstRegistration(new Date());
            advert1.setMileage(1200);
            advert1.setPrice(1000);
            allCarAdverts.add(advert1);
    
    
            CarAdvert advert2=new CarAdvert();
            advert2.setId((long ) 2);
            advert2.setTitle("Naslov za oglas broj 2");
            advert2.setFuelType(FuelType.ELECTRICITY);
            advert2.setIsNew(false);
            advert2.setFirstRegistration(new Date());
            advert2.setMileage(1400);
            allCarAdverts.add(advert2);
            advert2.setPrice(250);
        }

        return allCarAdverts;
    }


    public CarAdvert getCarAdvertById(Long id)
    {
        Optional<CarAdvert> optionalCarAdvert = getAllCarAdverts("id").stream().filter(objekt -> objekt.getId() == id).findFirst();
        return optionalCarAdvert.isPresent() ? optionalCarAdvert.get() : null;
    }

    public CarAdvert createCarAdvert(CarAdvert carAdvert)
    {
        getAllCarAdverts("id").add(carAdvert);
        return getCarAdvertById(carAdvert.getId());
    }

    public CarAdvert updateCarAdvert(CarAdvert modifiedAdvert)
    {
        CarAdvert carAdvert=getCarAdvertById(modifiedAdvert.getId());
        carAdvert.setTitle(modifiedAdvert.getTitle());
        carAdvert.setPrice(modifiedAdvert.getPrice());
        carAdvert.setFirstRegistration(modifiedAdvert.getFirstRegistration());
        carAdvert.setFuelType(modifiedAdvert.getFuelType());
        carAdvert.setIsNew(modifiedAdvert.getIsNew());
        carAdvert.setMileage(modifiedAdvert.getMileage());

        return getCarAdvertById(modifiedAdvert.getId());
    }

    public void deleteCarAdvertById(Long id)
    {
        CarAdvert carAdvertForDelete=getCarAdvertById(id);
        if(carAdvertForDelete!=null)
        {
            int index=getAllCarAdverts("id").indexOf(carAdvertForDelete);
            if(index!=-1)
            {
                getAllCarAdverts("id").remove(index);
            }
        }

    }
    
}
