package com.tailor.dto;

import com.tailor.model.Measurement;
import com.tailor.model.Order;
import com.tailor.model.User;

import java.time.LocalDateTime;

public class OrderDetailsResponse {

    private String id;
    private String userId;
    private String customerName;
    private String customerEmail;
    private String customerPhone;
    
    private String garmentType;
    private String measurementId;
    private LocalDateTime orderDate;
    private String status;
    private Double price;

    // Custom Design Attributes
    private String designImageUrl;
    private String color;
    private String collarStyle;
    private String sleeveStyle;
    private String pocketStyle;
    private String specialInstructions;
    private String masterNotes;

    // Measurement Details (Resolved from DB or embedded custom)
    private Measurement measurement;

    public OrderDetailsResponse() {
    }

    public static OrderDetailsResponse fromEntity(Order order, User user, Measurement measurement) {
        OrderDetailsResponse response = new OrderDetailsResponse();
        response.setId(order.getId());
        response.setUserId(order.getUserId());
        if (user != null) {
            response.setCustomerName(user.getName());
            response.setCustomerEmail(user.getEmail());
            response.setCustomerPhone(user.getPhone());
        } else {
            response.setCustomerName("Valued Customer");
        }
        response.setGarmentType(order.getGarmentType());
        response.setMeasurementId(order.getMeasurementId());
        response.setOrderDate(order.getOrderDate());
        response.setStatus(order.getStatus());
        response.setPrice(order.getPrice());

        response.setDesignImageUrl(order.getDesignImageUrl());
        response.setColor(order.getColor());
        response.setCollarStyle(order.getCollarStyle());
        response.setSleeveStyle(order.getSleeveStyle());
        response.setPocketStyle(order.getPocketStyle());
        response.setSpecialInstructions(order.getSpecialInstructions());
        response.setMasterNotes(order.getMasterNotes());

        if (order.getCustomMeasurements() != null) {
            response.setMeasurement(order.getCustomMeasurements());
        } else {
            response.setMeasurement(measurement);
        }

        return response;
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
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

    public Measurement getMeasurement() {
        return measurement;
    }

    public void setMeasurement(Measurement measurement) {
        this.measurement = measurement;
    }
}
