package com.codevibe.codevibe.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.codevibe.codevibe.dto.CarAdvertDto;
import com.codevibe.codevibe.services.CarAdvertService;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@RestController
public class CarAdvertController
 {

    @Autowired
    private CarAdvertService carAdvertService;

    @Autowired
    public CarAdvertController(CarAdvertService _carAdvertService)
    {
        this.carAdvertService=_carAdvertService;
    }
    

    private String testString="ovo je testni string";

    @GetMapping("/test")
public String test() {
    return testString;
}

//mozda napraviti objekt za errore ?
    @GetMapping("/car/adverts")
    public ResponseEntity<?> getAllAdverts(@RequestParam(required = false, defaultValue = "id",name ="sortBy") String sortBy)
    {
        List<String> allowedSortBy = List.of("id", "title", "fuel_type", "is_new", "price","first_registration","mileage");
        if(allowedSortBy.contains(sortBy.toLowerCase()))
        {
            try
            {
                List<CarAdvertDto> allDtoCarAdverts=carAdvertService.getAllCarAdverts(sortBy);
                return ResponseEntity.status(HttpStatus.OK).body(allDtoCarAdverts);
            } 
            catch (Exception e) 
            {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Exception caused by internal server error!");
            }
        }
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You entered an invalid sortBy queryparam!");
    } 


  @GetMapping("car/adverts/{id}")
    public ResponseEntity<?> getAdvertById(@PathVariable(name ="id") Long id) 
    {
        try 
        {
            CarAdvertDto carAdvertDto = carAdvertService.getCarAdvertById(id);
            if (carAdvertDto!=null) 
            {
                return ResponseEntity.status(HttpStatus.OK).body(carAdvertDto);
            }
            else 
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No car advert with the given ID was found.");
            }
        } 
        catch (Exception e) 
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Exception caused by internal server error!");
        }
       
    }

    //ovdje heldnat eror 400 bag request
  @PostMapping("car/advert")
    public ResponseEntity<?> createCarAdvert(@Valid @RequestBody CarAdvertDto newDtoCarAdvert)
     {
        try 
        {
            CarAdvertDto createdCarAdvertDto=carAdvertService.createCarAdvert(newDtoCarAdvert);
            if(createdCarAdvertDto!=null)
            {
                return ResponseEntity.status(HttpStatus.CREATED).body(createdCarAdvertDto);
            }
            else 
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No car advert with the given ID was found.");
            }
          
        }
        //provjeriti kako bad request hendlati
        catch (JsonParseException e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ddd");
        } 
        
    }

     @PutMapping("car/adverts/{id}")
    public ResponseEntity<?> updateCarAdvert(@PathVariable(name = "id") Long id, @Valid @RequestBody CarAdvertDto updatedAdvertDto) 
    {
        CarAdvertDto existingCarAdvert = carAdvertService.getCarAdvertById(id);
        if (existingCarAdvert!=null) 
        {
           return ResponseEntity.status(HttpStatus.OK).body(carAdvertService.updateCarAdvert(id,updatedAdvertDto));
        }
        else 
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car advert with ID: " + id + " not found");
        }
    }

    @DeleteMapping("car/adverts/{id}")
    public ResponseEntity<?> deleteCarAdvert(@PathVariable(name = "id",required = true) Long id)
    {
        CarAdvertDto carAdvertForDelete = carAdvertService.getCarAdvertById(id);
        if(carAdvertForDelete!=null)
        {
            carAdvertService.deleteCarAdvert(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("None");
        }
        else 
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No car advert with the given ID was found.");
        }
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) 
    {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
        String fieldName = ((FieldError) error).getField();
        String errorMessage = error.getDefaultMessage();
        errors.put(fieldName, errorMessage);
    });
    return errors;
}




}
