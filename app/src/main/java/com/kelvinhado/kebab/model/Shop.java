package com.kelvinhado.kebab.model;


import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by kelvin on 14/10/2016.
 */
@IgnoreExtraProperties
public class Shop {

    private String id;
    private String name;
    private String rating;
    private Double price;
    private String address;
    private Double latitude;
    private Double longitude;

    public Shop() {
    }

    public Shop(String id, String name, String rating, Double price, String address, Double latitude, Double longitude) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.price = price;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getId() {
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
