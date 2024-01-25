package com.codevibe.codevibe.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codevibe.codevibe.models.CarAdvert;
import com.codevibe.codevibe.services.CarAdvertService;

@RestController
//@RequestMapping("/car/adverts")
public class CarAdvertController {

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
    public ResponseEntity<?> getAllAdverts(@RequestParam(required = false, defaultValue = "id") String sortBy)
    {
        try
        {
            List<CarAdvert> allCarAdverts=carAdvertService.getAllCarAdverts(sortBy);
            return ResponseEntity.status(HttpStatus.OK).body(allCarAdverts);
        } 
        catch (Exception e) 
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Exception caused by internal server error!");
        }
    
}

  @GetMapping("car/adverts/{id}")
    public ResponseEntity<?> getAdvertById(@PathVariable Long id) 
    {
        try 
        {
            CarAdvert carAdvert = carAdvertService.getCarAdvertById(id);
            if (carAdvert!=null) 
            {
                return ResponseEntity.status(HttpStatus.OK).body(carAdvert);
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

  @PostMapping("car/advert")
    public ResponseEntity<?> createCarAdvert(@RequestBody CarAdvert newCarAdvert)
     {
        try 
        {
            // razmisliti gdje stavititi metodu za validaciju, gdje ju smjestiti
            //if(Prode validaciju)
            carAdvertService.createCarAdvert(newCarAdvert);
            return ResponseEntity.status(HttpStatus.CREATED).body(carAdvertService.createCarAdvert(newCarAdvert));
            /*
             * else
             * {
             * return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Validation failed!");
             * }
             */
        }
        //provjeriti kako bad request hendlati
        catch (JsonParseException e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This is returned if JSON is invalid or cannot be parsed");
        } 
        
    }

     @PutMapping("car/adverts/{id}")
    public ResponseEntity<?> updateCarAdvert(@PathVariable Long id, @RequestBody CarAdvert updatedAdvert) 
    {

        CarAdvert existingAdvertOptional = carAdvertService.getCarAdvertById(id);
        if (existingAdvertOptional!=null) 
        {
            //if(prođe Validaciju)
           return ResponseEntity.status(HttpStatus.OK).body(carAdvertService.updateCarAdvert(updatedAdvert));
           /*
            * else 
            {
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Validation failed!");
            }
            */
           
        }
        else 
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car advert with ID: " + id + " not found");
        }

        //jos dodati bad request
    }

    @DeleteMapping("car/adverts/{:id}")
    public ResponseEntity<?> deleteCarAdvert(@PathVariable Long id)
    {
        try {
            carAdvertService.deleteCarAdvert(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("None");
        } catch (Exception e) {
            //kako ovdje provjeriti koji je ecpetion jeli to exception koji je uzrokovan zbog not fount ili nesto drugo?
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This is returned if a car advert with given id is not found");
        }
    }

}
