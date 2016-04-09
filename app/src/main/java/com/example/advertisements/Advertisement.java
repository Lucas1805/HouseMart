package com.example.advertisements;

import java.sql.Date;

/**
 * Created by Megurine Lucas on 05-04-2016.
 */
public class Advertisement {
    private String id;
    private String ownerName;
    private String address;
    private String district;
    private String province;
    private String phone;
    private String description;
    private String area;
    private String price;
    private String type;
    private String dateCreate;
    private String dateUpdate;
    private String creator;
    private String updator;

    public Advertisement(String address, String area, String creator, String dateCreate
            , String dateUpdate, String description, String district, String id, String ownerName
            , String phone, String price, String province, String type, String updator) {
        this.address = address;
        this.area = area;
        this.creator = creator;
        this.dateCreate = dateCreate;
        this.dateUpdate = dateUpdate;
        this.description = description;
        this.district = district;
        this.id = id;
        this.ownerName = ownerName;
        this.phone = phone;
        this.price = price;
        this.province = province;
        this.type = type;
        this.updator = updator;
    }

    @Override
    public String toString() {
        return id + ownerName + address + district + province + phone + description + area + price
                + type + dateCreate + dateUpdate + creator + updator;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(String dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUpdator() {
        return updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator;
    }
}
