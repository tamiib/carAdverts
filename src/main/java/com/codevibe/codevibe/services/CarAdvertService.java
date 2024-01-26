package com.codevibe.codevibe.services;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.codevibe.codevibe.converters.ModelsConverter;
import com.codevibe.codevibe.dto.CarAdvertDto;
import com.codevibe.codevibe.repositories.CarAdvertRepository;

@Service
public class CarAdvertService {
     @Autowired
    private final CarAdvertRepository repository;
    
    @Autowired
    private final ModelsConverter converter;

    @Autowired
    public CarAdvertService(CarAdvertRepository _repository,ModelsConverter _converter)
    {
        this.repository=_repository;
        this.converter=_converter;
    }


    public List<CarAdvertDto> getAllCarAdverts(String filedName)
    {
        List<CarAdvertDto> allCarAdverts=converter.convertDomainCarAdvertListToDtoCarAdvertList(repository.getAllCarAdverts(filedName));
        Comparator<CarAdvertDto> comparator = converter.getComparatorForAttribute(filedName);
        Collections.sort(allCarAdverts, comparator);
         return allCarAdverts;
    }

    public CarAdvertDto getCarAdvertById(Long id)
    {
        return converter.convertDomainCarAdvertToDtoCarAdvert(repository.getCarAdvertById(id));
    }

    public CarAdvertDto createCarAdvert(CarAdvertDto carAdvertDto)
    {
       return converter.convertDomainCarAdvertToDtoCarAdvert(repository.createCarAdvert(converter.convertDtoCarAdvertToDomainCarAdvert(carAdvertDto)));
    }

    public CarAdvertDto updateCarAdvert(CarAdvertDto modifiedCarAdvertDto)
    {
       return converter.convertDomainCarAdvertToDtoCarAdvert(repository.updateCarAdvert(converter.convertDtoCarAdvertToDomainCarAdvert(modifiedCarAdvertDto)));
    }

    public void deleteCarAdvert(Long id)
    {
        repository.deleteCarAdvertById(id);
    }
}
