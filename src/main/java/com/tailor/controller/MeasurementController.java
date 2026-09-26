package com.tailor.controller;

import com.tailor.dto.MeasurementRequest;
import com.tailor.model.Measurement;
import com.tailor.service.MeasurementService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/measurements")
@CrossOrigin(origins = "*")
public class MeasurementController {

    private final MeasurementService measurementService;

    // Constructor Injection
    public MeasurementController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    @PostMapping
    public ResponseEntity<Measurement> addMeasurement(@Valid @RequestBody MeasurementRequest request) {
        Measurement created = measurementService.addMeasurement(request);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Measurement>> getMeasurementsByUserId(@PathVariable String userId) {
        List<Measurement> measurements = measurementService.getMeasurementsByUserId(userId);
        return ResponseEntity.ok(measurements);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Measurement> getMeasurementById(@PathVariable String id) {
        Measurement measurement = measurementService.getMeasurementById(id);
        return ResponseEntity.ok(measurement);
    }

    @GetMapping
    public ResponseEntity<List<Measurement>> getAllMeasurements() {
        List<Measurement> measurements = measurementService.getAllMeasurements();
        return ResponseEntity.ok(measurements);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Measurement> updateMeasurement(@PathVariable String id, @Valid @RequestBody MeasurementRequest request) {
        Measurement updated = measurementService.updateMeasurement(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteMeasurement(@PathVariable String id) {
        measurementService.deleteMeasurement(id);
        return ResponseEntity.ok(Map.of("message", "Measurement deleted successfully", "id", id));
    }
}
