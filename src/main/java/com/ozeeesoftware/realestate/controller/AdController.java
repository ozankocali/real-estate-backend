package com.ozeeesoftware.realestate.controller;

import com.ozeeesoftware.realestate.model.Ad;
import com.ozeeesoftware.realestate.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200")
public class AdController {

    @Autowired
    private AdService adService;


    @GetMapping("/ads")
    public List<Ad> getAllAds(){
        return adService.getAllAds();
    }

    @PostMapping("/ads")
    public Ad createAd(@RequestBody Ad ad){
        return adService.createAd(ad);
    }

    @GetMapping("/ads/{id}")
    public ResponseEntity<Ad> getAdById(@PathVariable Long id){
        return adService.getAdById(id);
    }

    @PutMapping("/ads/{id}")
    public ResponseEntity<Ad> updateAd(@PathVariable Long id,@RequestBody Ad ad){
        return adService.updateAd(id,ad);
    }

    @DeleteMapping("/ads/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteAd(@PathVariable Long id){
        return adService.deleteAd(id);
    }

}
