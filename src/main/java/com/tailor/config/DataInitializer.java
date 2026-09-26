package com.tailor.config;

import com.tailor.model.User;
import com.tailor.repository.MeasurementRepository;
import com.tailor.repository.OrderRepository;
import com.tailor.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initMasterAccount(UserRepository userRepository,
                                               MeasurementRepository measurementRepository,
                                               OrderRepository orderRepository,
                                               PasswordEncoder passwordEncoder) {
        return args -> {
            // Clean legacy demo / sample test accounts if present
            userRepository.findByEmail("customer@example.com").ifPresent(u -> {
                orderRepository.deleteByUserId(u.getId());
                measurementRepository.deleteByUserId(u.getId());
                userRepository.delete(u);
            });
            userRepository.findByEmail("admin@tailor.com").ifPresent(userRepository::delete);

            // Clean orphaned sample demo records with dummy customer IDs
            orderRepository.findAll().forEach(o -> {
                if (o.getUserId() == null || !userRepository.existsById(o.getUserId())) {
                    orderRepository.delete(o);
                }
            });
            measurementRepository.findAll().forEach(m -> {
                if (m.getUserId() == null || !userRepository.existsById(m.getUserId())) {
                    measurementRepository.delete(m);
                }
            });

            // Ensure ONE legitimate Master account exists for authorized Master operations with BCrypt password
            User master = userRepository.findByEmail("master@tailor.com").orElseGet(User::new);
            master.setName("Master Tailor");
            master.setEmail("master@tailor.com");
            master.setPassword(passwordEncoder.encode("MasterPassword123!"));
            master.setPhone("9876543210");
            master.setRole("MASTER");
            userRepository.save(master);
        };
    }
}
