package com.ozeeesoftware.realestate.model;

import javax.persistence.*;

@Entity
@Table(name="ads")
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
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

    public Property() {
    }

    public Property(String name, double price, String address, short numberOfBedrooms, short numberOfBathrooms, short numberOfGarageSpaces, int landSize) {
        this.name = name;
        this.price = price;
        this.address = address;
        this.numberOfBedrooms = numberOfBedrooms;
        this.numberOfBathrooms = numberOfBathrooms;
        this.numberOfGarageSpaces = numberOfGarageSpaces;
        this.landSize = landSize;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAdress(String address) {
        this.address = address;
    }

    public short getNumberOfBedrooms() {
        return numberOfBedrooms;
    }

    public void setNumberOfBedrooms(short numberOfBedrooms) {
        this.numberOfBedrooms = numberOfBedrooms;
    }

    public short getNumberOfBathrooms() {
        return numberOfBathrooms;
    }

    public void setNumberOfBathrooms(short numberOfBathrooms) {
        this.numberOfBathrooms = numberOfBathrooms;
    }

    public short getNumberOfGarageSpaces() {
        return numberOfGarageSpaces;
    }

    public void setNumberOfGarageSpaces(short numberOfGarageSpaces) {
        this.numberOfGarageSpaces = numberOfGarageSpaces;
    }

    public int getLandSize() {
        return landSize;
    }

    public void setLandSize(int landSize) {
        this.landSize = landSize;
    }
}
