package com.example.advertisements;

import java.util.Date;

/**
 * Created by Megurine Lucas on 05-04-2016.
 */
public class Advertisement {
    private String id;
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
    private String creator;
    private String updator;

    public Advertisement(String address, String area, String creator, Date dateCreate
            , Date dateUpdate, String description, String districtID, String districtName
            , String id, String image1, String image2, String image3, String latitude
            , String longtitude, String title, String phone, String price, String provinceID
            , String provinceName, String type, String updator) {
        this.address = address;
        this.area = area;
        this.creator = creator;
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
        this.updator = updator;
    }

    /**
     * Constructor with no latitude, longtitude, image1,2,3; creator, updator, phone
     * This constructor use for load all advertisements when first start app
     */
    public Advertisement(String address, String area, Date dateCreate
            , Date dateUpdate, String districtID, String districtName
            , String id, String title, String price, String provinceID
            , String provinceName, String type) {
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
        return title;
    }

    public void setOwnerName(String title) {
        this.title = title;
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
