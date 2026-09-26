package com.tailor.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class MeasurementRequest {

    @NotBlank(message = "User ID is required")
    private String userId;

    @NotBlank(message = "Garment type is required (e.g., Shirt, Pant, Suit)")
    private String garmentType;

    @NotNull(message = "Chest measurement is required")
    @Positive(message = "Chest must be positive")
    private Double chest;

    @NotNull(message = "Shoulder measurement is required")
    @Positive(message = "Shoulder must be positive")
    private Double shoulder;

    @NotNull(message = "Sleeve measurement is required")
    @Positive(message = "Sleeve must be positive")
    private Double sleeve;

    @NotNull(message = "Waist measurement is required")
    @Positive(message = "Waist must be positive")
    private Double waist;

    @NotNull(message = "Length measurement is required")
    @Positive(message = "Length must be positive")
    private Double length;

    public MeasurementRequest() {
    }

    public MeasurementRequest(String userId, String garmentType, Double chest, Double shoulder, Double sleeve, Double waist, Double length) {
        this.userId = userId;
        this.garmentType = garmentType;
        this.chest = chest;
        this.shoulder = shoulder;
        this.sleeve = sleeve;
        this.waist = waist;
        this.length = length;
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
