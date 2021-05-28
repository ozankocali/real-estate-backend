package com.ozeeesoftware.realestate.property;


import com.ozeeesoftware.realestate.model.Property;
import com.ozeeesoftware.realestate.repository.PropertyRepository;
import com.ozeeesoftware.realestate.service.PropertyServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class PropertyServiceTest {

    @MockBean
    private PropertyRepository propertyRepository;

    @InjectMocks
    private PropertyServiceImpl propertyService;

    @Test
    public void testGetAllAds(){
        List<Property> propertyList=new ArrayList<Property>();

        propertyList.add(new Property(1L,"test-name",1,"test-address",(short)1,(short)1,(short)1,(short)1,"test-description"));

        propertyList.add(new Property(1L,"test-name2",1,"test-address2",(short)1,(short)1,(short)1,(short)1,"test-description2"));

        when(propertyRepository.findAll()).thenReturn(propertyList);

        assertEquals(2,propertyService.getAllAds().size());

    }

    @Test
    public void testGetAdById(){
        Property property=new Property(1L,"test-name",1,"test-address",(short)1,(short)1,(short)1,(short)1,"test-description");

        when(propertyRepository.findById(1L)).thenReturn(java.util.Optional.of(property));

        assertEquals(property,propertyService.getAdById(1L).getBody());

    }

    @Test
    public void testCreateAd(){
        Property property=new Property(1L,"test-name",1,"test-address",(short)1,(short)1,(short)1,(short)1,"test-description");

        when(propertyRepository.save(property)).thenReturn(property);

        assertEquals(property,propertyService.createAd(property).getBody());

    }

    @Test
    public void testDeleteAd(){
        Property property=new Property(1L,"test-name",1,"test-address",(short)1,(short)1,(short)1,(short)1,"test-description");

        when(propertyRepository.findById(1L)).thenReturn(java.util.Optional.of(property));

        propertyService.deleteAd(property.getId());

        verify(propertyRepository,times(1)).delete(property);


    }

}
