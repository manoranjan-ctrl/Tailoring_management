package com.tailor.dto;

import com.tailor.model.Measurement;
import jakarta.validation.constraints.NotBlank;

public class OrderCreateRequest {

    @NotBlank(message = "User ID is required")
    private String userId;

    @NotBlank(message = "Garment type is required")
    private String garmentType;

    private String measurementId;
    private Double price;

    // Custom Design Fields
    private String designImageUrl;
    private String color;
    private String collarStyle;
    private String sleeveStyle;
    private String pocketStyle;
    private String specialInstructions;
    private Measurement customMeasurements;

    public OrderCreateRequest() {
    }

    public OrderCreateRequest(String userId, String garmentType, String measurementId, Double price) {
        this.userId = userId;
        this.garmentType = garmentType;
        this.measurementId = measurementId;
        this.price = price;
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

    public String getMeasurementId() {
        return measurementId;
    }

    public void setMeasurementId(String measurementId) {
        this.measurementId = measurementId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDesignImageUrl() {
        return designImageUrl;
    }

    public void setDesignImageUrl(String designImageUrl) {
        this.designImageUrl = designImageUrl;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCollarStyle() {
        return collarStyle;
    }

    public void setCollarStyle(String collarStyle) {
        this.collarStyle = collarStyle;
    }

    public String getSleeveStyle() {
        return sleeveStyle;
    }

    public void setSleeveStyle(String sleeveStyle) {
        this.sleeveStyle = sleeveStyle;
    }

    public String getPocketStyle() {
        return pocketStyle;
    }

    public void setPocketStyle(String pocketStyle) {
        this.pocketStyle = pocketStyle;
    }

    public String getSpecialInstructions() {
        return specialInstructions;
    }

    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }

    public Measurement getCustomMeasurements() {
        return customMeasurements;
    }

    public void setCustomMeasurements(Measurement customMeasurements) {
        this.customMeasurements = customMeasurements;
    }
}
