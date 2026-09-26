package com.tailor.service;

import com.tailor.dto.MeasurementRequest;
import com.tailor.model.Measurement;
import com.tailor.repository.MeasurementRepository;
import com.tailor.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeasurementService {

    private final MeasurementRepository measurementRepository;
    private final UserRepository userRepository;

    // Constructor Injection
    public MeasurementService(MeasurementRepository measurementRepository, UserRepository userRepository) {
        this.measurementRepository = measurementRepository;
        this.userRepository = userRepository;
    }

    public Measurement addMeasurement(MeasurementRequest request) {
        if (!userRepository.existsById(request.getUserId())) {
            throw new IllegalArgumentException("User not found with id: " + request.getUserId());
        }

        Measurement measurement = new Measurement();
        measurement.setUserId(request.getUserId());
        measurement.setGarmentType(request.getGarmentType().trim());
        measurement.setChest(request.getChest());
        measurement.setShoulder(request.getShoulder());
        measurement.setSleeve(request.getSleeve());
        measurement.setWaist(request.getWaist());
        measurement.setLength(request.getLength());

        return measurementRepository.save(measurement);
    }

    public List<Measurement> getMeasurementsByUserId(String userId) {
        return measurementRepository.findByUserId(userId);
    }

    public Measurement getMeasurementById(String id) {
        return measurementRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Measurement not found with id: " + id));
    }

    public Measurement updateMeasurement(String id, MeasurementRequest request) {
        Measurement measurement = measurementRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Measurement not found with id: " + id));

        if (request.getGarmentType() != null && !request.getGarmentType().isBlank()) {
            measurement.setGarmentType(request.getGarmentType().trim());
        }
        if (request.getChest() != null) {
            measurement.setChest(request.getChest());
        }
        if (request.getShoulder() != null) {
            measurement.setShoulder(request.getShoulder());
        }
        if (request.getSleeve() != null) {
            measurement.setSleeve(request.getSleeve());
        }
        if (request.getWaist() != null) {
            measurement.setWaist(request.getWaist());
        }
        if (request.getLength() != null) {
            measurement.setLength(request.getLength());
        }

        return measurementRepository.save(measurement);
    }

    public void deleteMeasurement(String id) {
        if (!measurementRepository.existsById(id)) {
            throw new IllegalArgumentException("Measurement not found with id: " + id);
        }
        measurementRepository.deleteById(id);
    }

    public List<Measurement> getAllMeasurements() {
        return measurementRepository.findAll();
    }
}
