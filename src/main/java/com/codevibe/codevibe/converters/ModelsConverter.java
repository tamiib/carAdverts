package com.codevibe.codevibe.converters;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.codevibe.codevibe.domainModels.CarAdvert;
import com.codevibe.codevibe.dto.CarAdvertDto;

@Service
public class ModelsConverter
{

     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public CarAdvertDto convertDomainCarAdvertToDtoCarAdvert(CarAdvert domainCarAdvert)
    {
        CarAdvertDto dtoCarAdvert=null;
        if(domainCarAdvert!=null)
        {
            dtoCarAdvert=new CarAdvertDto();
    
            dtoCarAdvert.setId(domainCarAdvert.getId());
            dtoCarAdvert.setTitle(domainCarAdvert.getTitle());
            dtoCarAdvert.setFuelType(domainCarAdvert.getFuelType());
            dtoCarAdvert.setIsNew(domainCarAdvert.getIsNew());
            dtoCarAdvert.setPrice(domainCarAdvert.getPrice());
    
            if(domainCarAdvert.getFirstRegistration()!=null)
            {
                dtoCarAdvert.setFirstRegistration(domainCarAdvert.getFirstRegistration());
            }
    
            if(domainCarAdvert.getMileage()!=null)
            {
                dtoCarAdvert.setMileage(domainCarAdvert.getMileage());
            }
        }
        return dtoCarAdvert;

    }

    public CarAdvert convertDtoCarAdvertToDomainCarAdvert(CarAdvertDto dtoCarAdvert)
    {
        CarAdvert domainCarAdvert=null;
        if(dtoCarAdvert!=null)
        {
            domainCarAdvert=new CarAdvert();
    
            domainCarAdvert.setId(dtoCarAdvert.getId());
            domainCarAdvert.setTitle(dtoCarAdvert.getTitle());
            domainCarAdvert.setFuelType(dtoCarAdvert.getFuelType());
            domainCarAdvert.setIsNew(dtoCarAdvert.getIsNew());
            domainCarAdvert.setPrice(dtoCarAdvert.getPrice());
    
            if(dtoCarAdvert.getFirstRegistration()!=null)
            {
                domainCarAdvert.setFirstRegistration(dtoCarAdvert.getFirstRegistration());
            }
    
            if(dtoCarAdvert.getMileage()!=null)
            {
                domainCarAdvert.setMileage(dtoCarAdvert.getMileage());
            }
        }

        return domainCarAdvert;
    }

    public Comparator<CarAdvertDto> getComparatorForAttribute(String sortBy) {
        return switch (sortBy) 
        {
            case "id" -> Comparator.comparing(CarAdvertDto::getId);
            case "title" -> Comparator.comparing(CarAdvertDto::getTitle);
            case "fuelType" -> Comparator.comparing(CarAdvertDto::getFuelType);
            case "price" -> Comparator.comparing(CarAdvertDto::getPrice);
            case "isNew" -> Comparator.comparing(CarAdvertDto::getIsNew);
            case "mileage" -> Comparator.comparing(CarAdvertDto::getMileage);
            case "firstRegistration" -> Comparator.comparing(CarAdvertDto::getFirstRegistration);
          
            default -> throw new IllegalArgumentException("Unsupported sortBy param: " + sortBy);
        };
    }

    public List<CarAdvertDto> convertDomainCarAdvertListToDtoCarAdvertList(List<CarAdvert> domainCarAdvertList)
    {
        List<CarAdvertDto> carAdvertDtoList=new ArrayList<>();
        if(domainCarAdvertList!=null)
        {
            for(CarAdvert domainCarAdvert:domainCarAdvertList)
            {
                carAdvertDtoList.add(this.convertDomainCarAdvertToDtoCarAdvert(domainCarAdvert));
            }
        }
        return carAdvertDtoList;
    }



    
}
