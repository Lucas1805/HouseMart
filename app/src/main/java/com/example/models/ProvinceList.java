package com.example.models;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Megurine Lucas on 16-04-2016.
 */
public class ProvinceList {
    private List<Province> list = new LinkedList<>();

    public String getProvinceIDByPosition(int position) {
        return list.get(position).getId();
    }

    public String getProvinceIDByName (String provinceName) {
        System.out.println("LIST SIZE " + list.size());
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i);
            System.out.println(list.get(i).getProvinceName());
            if (list.get(i).getProvinceName().equals(provinceName)) {
                return list.get(i).getId();
            }
        }
        return null;
    }

    public void setList(List<Province> list) {
        this.list = list;
    }
}
