package com.example.provinces;

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

    public void setList(List<Province> list) {
        this.list = list;
    }

    /**
     * Ham su dung de lay ra list cac province name roi dung list do load vao spinner khi moi chay app
     * @return
     */
    public List<String> getListOfProvinceNames(String firstDefaultValue) {
        List<String> tmp = new LinkedList<>();
        for (int i = 0; i < list.size(); i++) {
            tmp.add(list.get(i).getProvinceName());
        }
        tmp.add(0,firstDefaultValue);
        return tmp;
    }

    /**
     * Lay ra tat ca cac district sau khi nguoi dung da chon province, Roi dung de load vo spinner
     * @param provinceName
     * @return
     */
    public List<String> getListOfDistrictNames(String firstDefaultValue, String provinceName) {
        List<String> tmp = new LinkedList<>();
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getProvinceName().equals(provinceName))
                tmp.add(list.get(i).getDistrictName());
        }
        tmp.add(0,firstDefaultValue);
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
            if (list.get(i).getDistrictName().equals(districtName)) {
                return list.get(i).getDistrictID();
            }
        }
        return null;
    }
}
