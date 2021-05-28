package com.ozeeesoftware.realestate.service;

import com.ozeeesoftware.realestate.model.Property;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface PropertyService {

    ResponseEntity getAllAds();

    ResponseEntity createAd(Property property);

    ResponseEntity getAdById(Long id);

    ResponseEntity updateAd(Long id, Property property);

    ResponseEntity deleteAd(Long id);
}
