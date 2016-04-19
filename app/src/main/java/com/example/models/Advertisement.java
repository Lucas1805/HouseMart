package com.example.models;

import java.util.Date;

/**
 * Created by Megurine Lucas on 05-04-2016.
 */
public class Advertisement {
    private String id;
    private String ownerName;
    private String title;
    private String address;
    private String districtID;
    private String districtName;
    private String provinceID;
    private String provinceName;
    private String phone;
    private String description;
    private String area;
    private String price;
    private String type;
    private String latitude;
    private String longtitude;
    private String image1;
    private String image2;
    private String image3;
    private Date dateCreate;
    private Date dateUpdate;
    private String creatorID;
    private String creatorName;
    private String updatorID;
    private String updatorName;

    public Advertisement(String address, String area, String creatorID, Date dateCreate
            , Date dateUpdate, String description, String districtID, String districtName
            , String id, String image1, String image2, String image3, String latitude
            , String longtitude, String title, String phone, String price, String provinceID
            , String provinceName, String type, String updatorID) {
        this.address = address;
        this.area = area;
        this.creatorID = creatorID;
        this.dateCreate = dateCreate;
        this.dateUpdate = dateUpdate;
        this.description = description;
        this.districtID = districtID;
        this.districtName = districtName;
        this.id = id;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.title = title;
        this.phone = phone;
        this.price = price;
        this.provinceID = provinceID;
        this.provinceName = provinceName;
        this.type = type;
        this.updatorID = updatorID;
    }

    /**
     * Constructor with no latitude, longtitude, image1,2,3; creator, updator, phone
     * This constructor use for load all advertisements when first start app
     */
    public Advertisement(String address, String area, Date dateCreate
            , Date dateUpdate, String districtID, String districtName
            , String id, String title, String price, String provinceID
            , String provinceName, String type, String image1) {
        this.address = address;
        this.area = area;
        this.dateCreate = dateCreate;
        this.dateUpdate = dateUpdate;
        this.districtID = districtID;
        this.districtName = districtName;
        this.id = id;
        this.title = title;
        this.price = price;
        this.provinceID = provinceID;
        this.provinceName = provinceName;
        this.type = type;
        this.image1=image1;
    }

    /**
     * This constructor is use for load post info with detail flag is OFF. NO LATITUDE, LANGTITUDE
     */
    public Advertisement(String id, String ownerName, String title, String address, String districtID
            , String districtName, String provinceID, String provinceName, String phone, String description
            , String area, String price, String type, String image1
            , String image2, String image3, Date dateCreate, Date dateUpdate) {
        this.id = id;
        this.ownerName = ownerName;
        this.title = title;
        this.address = address;
        this.districtID = districtID;
        this.districtName = districtName;
        this.provinceID = provinceID;
        this.provinceName = provinceName;
        this.phone = phone;
        this.description = description;
        this.area = area;
        this.price = price;
        this.type = type;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.dateCreate = dateCreate;
        this.dateUpdate = dateUpdate;
    }


    /**
     * This constructor is use for load post info with detail flag is ON. NO LATITUDE, LANGTITUDE
     */
    public Advertisement(String id, String ownerName, String title, String address, String districtID
            , String districtName, String provinceID, String provinceName, String phone, String description
            , String area, String price, String type, String image1
            , String image2, String image3, Date dateCreate, Date dateUpdate, String creatorID, String creatorName
            , String updatorID, String updatorName) {
        this.id = id;
        this.ownerName = ownerName;
        this.title = title;
        this.address = address;
        this.districtID = districtID;
        this.districtName = districtName;
        this.provinceID = provinceID;
        this.provinceName = provinceName;
        this.phone = phone;
        this.description = description;
        this.area = area;
        this.price = price;
        this.type = type;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.dateCreate = dateCreate;
        this.dateUpdate = dateUpdate;
        this.creatorID = creatorID;
        this.creatorName = creatorName;
        this.updatorID = updatorID;
        this.updatorName = updatorName;
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

    public String getCreatorID() {
        return creatorID;
    }

    public void setCreatorID(String creatorID) {
        this.creatorID = creatorID;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDistrictID() {
        return districtID;
    }

    public void setDistrictID(String districtID) {
        this.districtID = districtID;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(String longtitude) {
        this.longtitude = longtitude;
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

    public String getProvinceID() {
        return provinceID;
    }

    public void setProvinceID(String provinceID) {
        this.provinceID = provinceID;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUpdatorID() {
        return updatorID;
    }

    public void setUpdatorID(String updatorID) {
        this.updatorID = updatorID;
    }

    public String getUpdatorName() {
        return updatorName;
    }

    public void setUpdatorName(String updatorName) {
        this.updatorName = updatorName;
    }
}
