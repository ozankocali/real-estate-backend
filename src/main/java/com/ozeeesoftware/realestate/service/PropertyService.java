package com.ozeeesoftware.realestate.service;

import com.ozeeesoftware.realestate.exception.NotFoundByIdException;
import com.ozeeesoftware.realestate.model.Property;
import com.ozeeesoftware.realestate.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    public List<Property> getAllAds(){
        return propertyRepository.findAll();
    }

    public Property createAd(Property property){
        return propertyRepository.save(property);
    }

    public ResponseEntity<Property> getAdById(Long id){
        Property property = propertyRepository.findById(id).orElseThrow(()->new NotFoundByIdException("Ad not exist with id:"+id));
        return ResponseEntity.ok(property);
    }

    public ResponseEntity<Property> updateAd(Long id, Property property){
        Property existingProperty = propertyRepository.findById(id).orElseThrow(()->new NotFoundByIdException("Ad not exist with id:"+id));
        existingProperty.setName(property.getName());
        existingProperty.setAdress(property.getAddress());
        existingProperty.setPrice(property.getPrice());
        existingProperty.setNumberOfBedrooms(property.getNumberOfBedrooms());
        existingProperty.setNumberOfBathrooms(property.getNumberOfBathrooms());
        existingProperty.setNumberOfGarageSpaces(property.getNumberOfGarageSpaces());
        existingProperty.setLandSize(property.getLandSize());
        Property updatedProperty = propertyRepository.save(existingProperty);
        return ResponseEntity.ok(updatedProperty);
    }

    public ResponseEntity<Map<String,Boolean>> deleteAd(Long id){
        Property existingProperty = propertyRepository.findById(id).orElseThrow(()->new NotFoundByIdException("Ad not exist with id:"+id));
        propertyRepository.delete(existingProperty);
        Map<String,Boolean> response=new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
