package com.buses;

import java.util.List;

/**
 * Created by federico on 21/05/2016.
 */
public class OnRequestCallback {

    List<Pathology> list;


    public OnRequestCallback(List<Pathology> list){
        this.list  = list;
    }

    public List<Pathology> getList() {
        return list;
    }

    public void setList(List<Pathology> list) {
        this.list = list;
    }
}
