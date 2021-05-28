package com.ozeeesoftware.realestate.controller;

import com.ozeeesoftware.realestate.model.Property;
import com.ozeeesoftware.realestate.service.PropertyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/properties")
@CrossOrigin(origins = "http://localhost:4200")
public class PropertyController {

    @Autowired
    private PropertyServiceImpl propertyServiceImpl;


    @GetMapping
    public ResponseEntity<List<Property>> getAllAds(){
        return propertyServiceImpl.getAllAds();
    }

    @PostMapping
    public ResponseEntity<Property> createAd(@RequestBody Property property){
        return propertyServiceImpl.createAd(property);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Property> getAdById(@PathVariable Long id){
        return propertyServiceImpl.getAdById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Property> updateAd(@PathVariable Long id, @RequestBody Property property){
        return propertyServiceImpl.updateAd(id, property);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteAd(@PathVariable Long id){
        return propertyServiceImpl.deleteAd(id);
    }

}
