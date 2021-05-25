package com.example.demo.model;


// Telefonun ön arama kısmında kullanılacak
public class PreviewPhone {

    // telefon ismi
    private String name;

    // telefonun urli
    private String phoneUrl;

    //telefonun resim urli
    private String imageUrl;

    //telefonun fiyatı
    private String price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneUrl() {
        return phoneUrl;
    }

    public void setPhoneUrl(String phoneUrl) {
        this.phoneUrl = phoneUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
