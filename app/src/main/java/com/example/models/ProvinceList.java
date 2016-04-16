package com.example.models;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Megurine Lucas on 13-04-2016.
 */
public class ProvinceList {
    private List<Province> list;

    public ProvinceList(List<Province> list) {
        this.list = list;
    }

    public ProvinceList() {
    }

    public int getSize() {
        return list.size();
    }

    public List<Province> getList() {
        return list;
    }
    public void setList(List<Province> list) {
        this.list = list;
    }

    /**
     * Ham su dung de lay ra list cac province name roi dung list do load vao spinner khi moi chay app
     * @return
     */
    public List<String> getListOfProvinceNames() {
        List<String> tmp = new LinkedList<>();
        for (int i = 0; i < list.size(); i++) {
            tmp.add(list.get(i).getProvinceName());
        }
        return tmp;
    }

    public List<String> getListOfDistrictName (String provinceName) {
        List<String> tmp = new LinkedList<>();
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getProvinceName().compareTo(provinceName) == 0)
                tmp.add(list.get(i).getDistrictName());
        }
        return tmp;
    }

    public String getProvinceID(String provinceName) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getProvinceName().equals(provinceName)) {
                return list.get(i).getProvinceID();
            }
        }
        return null;
    }

    public String getDistrictID (String districtName) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getDistrictName().compareTo(districtName) == 0) {
                return list.get(i).getDistrictID();
            }
        }
        return null;
    }
}
