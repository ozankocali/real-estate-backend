package com.ozeeesoftware.realestate.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name="property")
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private double price;
    @Column(name = "address")
    private String address;
    @Column(name = "bedroom")
    private short numberOfBedrooms;
    @Column(name = "bathroom")
    private short numberOfBathrooms;
    @Column(name = "garage")
    private short numberOfGarageSpaces;
    @Column(name = "land_size")
    private int landSize;
    @Column(name = "description")
    private String description;



}
