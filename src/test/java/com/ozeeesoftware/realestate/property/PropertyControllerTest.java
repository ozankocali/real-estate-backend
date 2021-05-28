package com.ozeeesoftware.realestate.property;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ozeeesoftware.realestate.controller.PropertyController;
import com.ozeeesoftware.realestate.model.Property;
import com.ozeeesoftware.realestate.repository.PropertyRepository;
import com.ozeeesoftware.realestate.service.PropertyService;
import com.ozeeesoftware.realestate.service.PropertyServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PropertyController.class)
public class PropertyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PropertyServiceImpl propertyService;

    @MockBean
    private PropertyRepository propertyRepository;

    String url="/api/v1/properties";

    @Test
    public void testGetAllAds() throws Exception {
        List<Property> propertyList=new ArrayList<Property>();

        propertyList.add(new Property(1L,"test-name",1,"test-address",(short)1,(short)1,(short)1,(short)1,"test-description"));

        propertyList.add(new Property(1L,"test-name2",1,"test-address2",(short)1,(short)1,(short)1,(short)1,"test-description2"));

        when(propertyService.getAllAds()).thenReturn(new ResponseEntity<List<Property>>(propertyList, HttpStatus.OK));

        MvcResult mvcResult=mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String actualJsonResponse=mvcResult.getResponse().getContentAsString();

        String expectedJsonResponse=objectMapper.writeValueAsString(propertyList);

        assertThat(actualJsonResponse).isEqualToIgnoringWhitespace(expectedJsonResponse);
    }

    @Test
    public void testGetAdById() throws Exception {
        Property property=new Property(1L,"test-name",1,"test-address",(short)1,(short)1,(short)1,(short)1,"test-description");

        when(propertyService.getAdById(1L)).thenReturn(new ResponseEntity<Property>(property,HttpStatus.OK));

        MvcResult mvcResult=mockMvc.perform(get(url+"/"+1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String actualJsonResponse=mvcResult.getResponse().getContentAsString();

        String expectedJsonResponse=objectMapper.writeValueAsString(property);

        assertThat(actualJsonResponse).isEqualToIgnoringWhitespace(expectedJsonResponse);

    }

    @Test
    public void testCreateAd() throws Exception {
        Property property=new Property(1L,"test-name",1,"test-address",(short)1,(short)1,(short)1,(short)1,"test-description");

        when(propertyService.createAd(property)).thenReturn(new ResponseEntity<Property>(property,HttpStatus.OK));

        MvcResult mvcResult=mockMvc.perform(post(url)
                .content(objectMapper.writeValueAsString(property))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String actualJsonResponse=mvcResult.getResponse().getContentAsString();

        String expectedJsonRespnse=objectMapper.writeValueAsString(property);

        assertThat(actualJsonResponse).isEqualToIgnoringWhitespace(expectedJsonRespnse);

    }

    @Test
    public void testUpdateAd() throws Exception{
        Property existingProperty = new Property(1L,"test-name",1,"test-address",(short)1,(short)1,(short)1,(short)1,"test-description");


        Property updatedProperty =new Property(1L,"test-name2",1,"test-address2",(short)1,(short)1,(short)1,(short)1,"test-description2");

        when(propertyService.updateAd(existingProperty.getId(),updatedProperty))
                .thenReturn(new ResponseEntity<Property>(updatedProperty,HttpStatus.OK));


        MvcResult mvcResult=mockMvc.perform(put(url+"/"+1)
                .content(objectMapper.writeValueAsString(updatedProperty))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String actualJsonResponse=mvcResult.getResponse().getContentAsString();

        String expectedJsonRespnse=objectMapper.writeValueAsString(updatedProperty);

        assertThat(actualJsonResponse).isEqualToIgnoringWhitespace(expectedJsonRespnse);

        assertThat(objectMapper.writeValueAsString(existingProperty)).isNotEqualTo(expectedJsonRespnse);
    }

    @Test
    public void testDeleteAd() throws Exception{
        Property property=new Property(1L,"test-name",1,"test-address",(short)1,(short)1,(short)1,(short)1,"test-description");

        Map<String,Boolean> response=new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        when(propertyService.deleteAd(property.getId())).thenReturn(new ResponseEntity<>(response,HttpStatus.OK));

        mockMvc.perform(delete(url+"/"+1)
        .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

    }



}
