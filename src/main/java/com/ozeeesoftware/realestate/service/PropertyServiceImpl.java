package com.ozeeesoftware.realestate.service;

import com.ozeeesoftware.realestate.exception.NotFoundByIdException;
import com.ozeeesoftware.realestate.model.Property;
import com.ozeeesoftware.realestate.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PropertyServiceImpl {

    @Autowired
    private PropertyRepository propertyRepository;

    public ResponseEntity<List<Property>> getAllAds(){
        return new ResponseEntity<List<Property>>(propertyRepository.findAll(),HttpStatus.OK);
    }

    public ResponseEntity<Property> createAd(Property property){
        return new ResponseEntity<Property>(propertyRepository.save(property), HttpStatus.OK);
    }

    public ResponseEntity<Property> getAdById(Long id){
        Property property = propertyRepository.findById(id).orElseThrow(()->new NotFoundByIdException("Ad not exist with id:"+id));
        return new ResponseEntity<Property>(property,HttpStatus.OK);
    }

    public ResponseEntity<Property> updateAd(Long id, Property property){
        Property existingProperty = propertyRepository.findById(id).orElseThrow(()->new NotFoundByIdException("Ad not exist with id:"+id));
        existingProperty.setName(property.getName());
        existingProperty.setAddress(property.getAddress());
        existingProperty.setPrice(property.getPrice());
        existingProperty.setNumberOfBedrooms(property.getNumberOfBedrooms());
        existingProperty.setNumberOfBathrooms(property.getNumberOfBathrooms());
        existingProperty.setNumberOfGarageSpaces(property.getNumberOfGarageSpaces());
        existingProperty.setLandSize(property.getLandSize());
        existingProperty.setDescription(property.getDescription());
        Property updatedProperty = propertyRepository.save(existingProperty);
        return new ResponseEntity<Property>(updatedProperty,HttpStatus.OK);
    }

    public ResponseEntity<Map<String,Boolean>> deleteAd(Long id){
        Property existingProperty = propertyRepository.findById(id).orElseThrow(()->new NotFoundByIdException("Ad not exist with id:"+id));
        propertyRepository.delete(existingProperty);
        Map<String,Boolean> response=new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return new ResponseEntity<Map<String,Boolean>>(response,HttpStatus.OK);
    }

}
