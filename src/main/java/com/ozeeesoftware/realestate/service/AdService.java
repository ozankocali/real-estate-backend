package com.ozeeesoftware.realestate.service;

import com.ozeeesoftware.realestate.exception.NotFoundByIdException;
import com.ozeeesoftware.realestate.model.Ad;
import com.ozeeesoftware.realestate.repository.AdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdService {

    @Autowired
    private AdRepository adRepository;

    public List<Ad> getAllAds(){
        return adRepository.findAll();
    }

    public Ad createAd(Ad ad){
        return adRepository.save(ad);
    }

    public ResponseEntity<Ad> getAdById(Long id){
        Ad ad=adRepository.findById(id).orElseThrow(()->new NotFoundByIdException("Ad not exist with id:"+id));
        return ResponseEntity.ok(ad);
    }

    public ResponseEntity<Ad> updateAd(Long id,Ad ad){
        Ad existingAd=adRepository.findById(id).orElseThrow(()->new NotFoundByIdException("Ad not exist with id:"+id));
        existingAd.setName(ad.getName());
        existingAd.setAdress(ad.getAddress());
        existingAd.setPrice(ad.getPrice());
        existingAd.setNumberOfBedrooms(ad.getNumberOfBedrooms());
        existingAd.setNumberOfBathrooms(ad.getNumberOfBathrooms());
        existingAd.setNumberOfGarageSpaces(ad.getNumberOfGarageSpaces());
        existingAd.setLandSize(ad.getLandSize());
        Ad updatedAd=adRepository.save(existingAd);
        return ResponseEntity.ok(updatedAd);
    }

    public ResponseEntity<Map<String,Boolean>> deleteAd(Long id){
        Ad existingAd=adRepository.findById(id).orElseThrow(()->new NotFoundByIdException("Ad not exist with id:"+id));
        adRepository.delete(existingAd);
        Map<String,Boolean> response=new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
