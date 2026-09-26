package com.tailor.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class OrderStatusUpdateRequest {

    @NotBlank(message = "Status is required")
    @Pattern(regexp = "DESIGN_RECEIVED|ORDER_ACCEPTED|MEASUREMENTS_VERIFIED|IN_PRODUCTION|READY_FOR_TRIAL|READY_FOR_DELIVERY|COMPLETED|REJECTED|PENDING|IN_PROGRESS|DELIVERED", 
             message = "Status must be one of: DESIGN_RECEIVED, ORDER_ACCEPTED, MEASUREMENTS_VERIFIED, IN_PRODUCTION, READY_FOR_TRIAL, READY_FOR_DELIVERY, COMPLETED, REJECTED, PENDING, IN_PROGRESS, DELIVERED")
    private String status;

    public OrderStatusUpdateRequest() {
    }

    public OrderStatusUpdateRequest(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
