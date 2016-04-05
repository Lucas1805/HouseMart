package com.example.advertisements;

import java.sql.Date;

/**
 * Created by Megurine Lucas on 05-04-2016.
 */
public class Advertisement {
    private String ownerName;
    private String address;
    private String district;
    private String city;
    private int phone;
    private String description;
    private double area;
    private double price;
    private int type;
    private Date dateCreate;
    private Date dateUpdate;
    private String creator;
    private String updator;

    public Advertisement() {
    }

    public Advertisement(String address, double area, String city, String creator, Date dateCreate
            , Date dateUpdate, String description, String district, String ownerName, int phone
            , double price, int type, String updator) {
        this.setAddress(address);
        this.setArea(area);
        this.setCity(city);
        this.setCreator(creator);
        this.setDateCreate(dateCreate);
        this.setDateUpdate(dateUpdate);
        this.setDescription(description);
        this.setDistrict(district);
        this.setOwnerName(ownerName);
        this.setPhone(phone);
        this.setPrice(price);
        this.setType(type);
        this.setUpdator(updator);
    }


    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getUpdator() {
        return updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator;
    }
}
