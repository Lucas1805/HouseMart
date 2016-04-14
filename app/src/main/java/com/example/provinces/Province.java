package com.example.provinces;

/**
 * Created by Megurine Lucas on 13-04-2016.
 * Use this class to store data for Province and District and load to spinner
 */
public class Province {
    private String districtID;
    private String districtName;
    private String provinceID;
    private String provinceName;

    public Province(String districtID, String districtName, String provinceID, String provinceName) {
        this.districtID = districtID;
        this.districtName = districtName;
        this.provinceID = provinceID;
        this.provinceName = provinceName;
    }

    @Override
    public String toString() {
        return provinceID + provinceName + districtID + districtName;
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
}
