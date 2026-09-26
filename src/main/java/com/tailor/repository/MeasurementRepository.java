package com.tailor.repository;

import com.tailor.model.Measurement;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeasurementRepository extends MongoRepository<Measurement, String> {
    List<Measurement> findByUserId(String userId);
    void deleteByUserId(String userId);
}
