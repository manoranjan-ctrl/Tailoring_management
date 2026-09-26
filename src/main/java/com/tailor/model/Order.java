package com.tailor.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "orders")
public class Order {

    @Id
    private String id;
    private String userId;
    private String garmentType;
    private String measurementId;
    private LocalDateTime orderDate;
    private String status = "DESIGN_RECEIVED"; // DESIGN_RECEIVED, ORDER_ACCEPTED, MEASUREMENTS_VERIFIED, IN_PRODUCTION, READY_FOR_TRIAL, READY_FOR_DELIVERY, COMPLETED, REJECTED, PENDING, IN_PROGRESS, DELIVERED
    private Double price = 0.0;

    // Custom Design Attributes
    private String designImageUrl;
    private String color;
    private String collarStyle;
    private String sleeveStyle;
    private String pocketStyle;
    private String specialInstructions;
    private String masterNotes;
    
    // Inline / embedded measurements if custom entered
    private Measurement customMeasurements;

    public Order() {
        this.orderDate = LocalDateTime.now();
        this.status = "DESIGN_RECEIVED";
    }

    public Order(String id, String userId, String garmentType, String measurementId, LocalDateTime orderDate, String status, Double price) {
        this.id = id;
        this.userId = userId;
        this.garmentType = garmentType;
        this.measurementId = measurementId;
        this.orderDate = (orderDate != null) ? orderDate : LocalDateTime.now();
        this.status = (status != null && !status.isBlank()) ? status : "DESIGN_RECEIVED";
        this.price = (price != null) ? price : 0.0;
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

    public String getMeasurementId() {
        return measurementId;
    }

    public void setMeasurementId(String measurementId) {
        this.measurementId = measurementId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getMasterNotes() {
        return masterNotes;
    }

    public void setMasterNotes(String masterNotes) {
        this.masterNotes = masterNotes;
    }

    public Measurement getCustomMeasurements() {
        return customMeasurements;
    }

    public void setCustomMeasurements(Measurement customMeasurements) {
        this.customMeasurements = customMeasurements;
    }
}
