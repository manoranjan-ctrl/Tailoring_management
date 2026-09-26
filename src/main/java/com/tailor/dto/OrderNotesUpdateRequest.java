package com.tailor.dto;

public class OrderNotesUpdateRequest {

    private String notes;

    public OrderNotesUpdateRequest() {
    }

    public OrderNotesUpdateRequest(String notes) {
        this.notes = notes;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
