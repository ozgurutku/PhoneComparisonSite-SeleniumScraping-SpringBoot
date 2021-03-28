package com.example.demo.model;

import com.example.demo.model.util.OtherSites;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Telefonun detayları kısmında kullanılacak
public class Phone {

    // telefonun ismi
    private String name;

    // telefonun açıklaması
    private String description;

    // telefonun resmi (url)
    private String picture;

    // telefonun özellikleri
    private Map<String, Map<String,String >> features = new HashMap<>();

    // telefonun diger sitelerdeki fiyat,resim linki,site linki,açıklaması,kargosu
    private List<OtherSites> otherSites = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Map<String, Map<String, String>> getFeatures() {
        return features;
    }

    public void setFeatures(Map<String, Map<String, String>> features) {
        this.features = features;
    }

    public List<OtherSites> getOtherSites() {
        return otherSites;
    }

    public void setOtherSites(List<OtherSites> otherSites) {
        this.otherSites = otherSites;
    }
}
