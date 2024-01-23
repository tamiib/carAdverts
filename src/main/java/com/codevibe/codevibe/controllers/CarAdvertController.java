package com.codevibe.codevibe.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@GetMapping("/car/adverts")
ResponseEntity<List<CarAdvert>> getAllAdverts(@RequestParam(required = false, defaultValue = "id") String sortBy)
{
    List<CarAdvert> allCarAdverts=carAdvertService.getAllCarAdverts(sortBy);

    return ResponseEntity.status(HttpStatus.OK).body(allCarAdverts);
}

  @GetMapping("car/adverts/{id}")
    public ResponseEntity<?> getAdvertById(@PathVariable Long id) {
        CarAdvert carAdvert = carAdvertService.getCarAdvertById(id);

        if (carAdvert!=null) {
            return ResponseEntity.status(HttpStatus.OK).body(carAdvert);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("No car advert with the given ID was found");
        }
    }

      @PostMapping("car/advert")
    public ResponseEntity<?> createCarAdvert(@RequestBody CarAdvert newAdvert) {
        try {
            carAdvertService.createCarAdvert(newAdvert);
            return ResponseEntity.status(HttpStatus.CREATED).body(carAdvertService.createCarAdvert(newAdvert));
        }
         catch (JsonParseException e)
          {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This is returned if JSON is invalid or cannot be parsed");
        } 
        /*ovdje dodati ispravan exception
        catch (InvalidEntityException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body("Validation failed: " + e.getMessage());
        }*/
    }

     @PutMapping("/{id}")
    public ResponseEntity<?> updateAdvert(@PathVariable Long id, @RequestBody CarAdvert updatedAdvert) {
        // Provera da li postoji oglas sa datim ID-om
        CarAdvert existingAdvertOptional = carAdvertService.getCarAdvertById(id);

        if (existingAdvertOptional!=null) {
           // try {
                // Validacija i ažuriranje oglasa;

                return ResponseEntity.status(HttpStatus.OK).body(carAdvertService.updateCarAdvert(updatedAdvert));
           /*  } 
            catch (Validation e) {
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Validation failed: " + e.getMessage());
            }*/
        } else {
            //nije pronaden oglas
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car advert with ID " + id + " not found");
        }
    }

}
