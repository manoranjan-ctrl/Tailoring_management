package com.tailor.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "measurements")
public class Measurement {

    @Id
    private String id;
    private String userId;
    private String garmentType;
    private Double chest;
    private Double shoulder;
    private Double sleeve;
    private Double waist;
    private Double length;

    public Measurement() {
    }

    public Measurement(String id, String userId, String garmentType, Double chest, Double shoulder, Double sleeve, Double waist, Double length) {
        this.id = id;
        this.userId = userId;
        this.garmentType = garmentType;
        this.chest = chest;
        this.shoulder = shoulder;
        this.sleeve = sleeve;
        this.waist = waist;
        this.length = length;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGarmentType() {
        return garmentType;
    }

    public void setGarmentType(String garmentType) {
        this.garmentType = garmentType;
    }

    public Double getChest() {
        return chest;
    }

    public void setChest(Double chest) {
        this.chest = chest;
    }

    public Double getShoulder() {
        return shoulder;
    }

    public void setShoulder(Double shoulder) {
        this.shoulder = shoulder;
    }

    public Double getSleeve() {
        return sleeve;
    }

    public void setSleeve(Double sleeve) {
        this.sleeve = sleeve;
    }

    public Double getWaist() {
        return waist;
    }

    public void setWaist(Double waist) {
        this.waist = waist;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }
}
