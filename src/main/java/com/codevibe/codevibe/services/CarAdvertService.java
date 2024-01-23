package com.codevibe.codevibe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codevibe.codevibe.models.CarAdvert;
import com.codevibe.codevibe.repositories.CarAdvertRepository;

@Service
public class CarAdvertService {
     @Autowired
    private final CarAdvertRepository repository;

    @Autowired
    public CarAdvertService(CarAdvertRepository _repository)
    {
        this.repository=_repository;
    }


    public List<CarAdvert> getAllCarAdverts(String filedName)
    {
         return repository.getAllCarAdverts(filedName);
    }

    public CarAdvert getCarAdvertById(Long id)
    {
        return repository.getCarAdvertById(id);
    }

    public CarAdvert createCarAdvert(CarAdvert carAdvert)
    {
       return  repository.createCarAdvert(carAdvert);
    }

    public CarAdvert updateCarAdvert(CarAdvert modifiedCarAdvert)
    {
       return repository.updateCarAdvert(modifiedCarAdvert);
    }
}
