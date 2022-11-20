package com.example.lab11;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * класс  Animal
 **/
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "name",
        "latin_name",
        "animal_type",
        "active_time",
        "length_min",
        "length_max",
        "weight_min",
        "weight_max",
        "lifespan",
        "habitat",
        "diet",
        "geo_range",
        "image_link"
})

public class Animal implements Serializable {
    @JsonProperty("name")
    private String name;
    @JsonProperty("latin_name")
    private String latinName;
    @JsonProperty("animal_type")
    private String animalType;
    @JsonProperty("active_time")
    private String activeTime;
    @JsonProperty("length_min")
    private Double lenMin;
    @JsonProperty("length_max")
    private Double lenMax;
    @JsonProperty("weight_min")
    private Double wgMin;
    @JsonProperty("weight_max")
    private Double wgMax;
    @JsonProperty("lifespan")
    private Double lifespan;
    @JsonProperty("habitat")
    private String habitat;
    @JsonProperty("diet")
    private String diet;
    @JsonProperty("geo_range")
    private String geoRange;
    @JsonProperty("image_link")
    private String imageLink;

    public Animal(int animalID, String name, String latinName, String animalType, String activeTime, Double lengthMin, Double lengthMax, Double weightMin, Double weightMax, Double lifespan, String habitat, String diet, String geoRange, String imageLink) {
        id = animalID;
        this.name = name;
        this.latinName = latinName;
        this.animalType = animalType;
        this.activeTime = activeTime;
        this.lenMin = lengthMin;
        this.lenMax = lengthMax;
        this.wgMin = weightMin;
        this.wgMax = weightMax;
        this.lifespan = lifespan;
        this.habitat = habitat;
        this.diet = diet;
        this.geoRange = geoRange;
        this.imageLink = imageLink;

    }

    public Animal() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLatinName() {
        return latinName;
    }

    public void setLatinName(String latinName) {
        this.latinName = latinName;
    }

    public String getAnimalType() {
        return animalType;
    }

    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }

    public String getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(String activeTime) {
        this.activeTime = activeTime;
    }

    public Double getLenMin() {
        return lenMin;
    }

    public void setLenMin(Double lenMin) {
        this.lenMin = lenMin;
    }

    public Double getLenMax() {
        return lenMax;
    }

    public void setLenMax(Double lengthMax) {
        this.lenMax = lengthMax;
    }

    public Double getWgMin() {
        return wgMin;
    }

    public void setWgMin(Double wgMin) {
        this.wgMin = wgMin;
    }

    public Double getWgMax() {
        return wgMax;
    }

    public void setWgMax(Double wgMax) {
        this.wgMax = wgMax;
    }

    public Double getLifespan() {
        return lifespan;
    }

    public void setLifespan(Double lifespan) {
        this.lifespan = lifespan;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public String getGeoRange() {
        return geoRange;
    }

    public void setGeoRange(String geoRange) {
        this.geoRange = geoRange;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public int getId() {
        return id;
    }

    public void setId(int animalID) {
        id = animalID;
    }


    @JsonProperty("id")
    private int id;



}
