package com.tailor.service;

import com.tailor.dto.OrderCreateRequest;
import com.tailor.dto.OrderDetailsResponse;
import com.tailor.model.Measurement;
import com.tailor.model.Order;
import com.tailor.model.User;
import com.tailor.repository.MeasurementRepository;
import com.tailor.repository.OrderRepository;
import com.tailor.repository.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class OrderService {

    public static final Set<String> VALID_STATUSES = Set.of(
            "DESIGN_RECEIVED",
            "ORDER_ACCEPTED",
            "MEASUREMENTS_VERIFIED",
            "IN_PRODUCTION",
            "READY_FOR_TRIAL",
            "READY_FOR_DELIVERY",
            "COMPLETED",
            "REJECTED",
            // Backwards compatibility statuses
            "PENDING",
            "IN_PROGRESS",
            "DELIVERED"
    );

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final MeasurementRepository measurementRepository;

    // Constructor Injection
    public OrderService(OrderRepository orderRepository, UserRepository userRepository, MeasurementRepository measurementRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.measurementRepository = measurementRepository;
    }

    public Order createOrder(OrderCreateRequest request) {
        if (!userRepository.existsById(request.getUserId())) {
            throw new IllegalArgumentException("User not found with id: " + request.getUserId());
        }

        Order order = new Order();
        order.setUserId(request.getUserId());
        order.setGarmentType(request.getGarmentType().trim());
        order.setMeasurementId(request.getMeasurementId());
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("DESIGN_RECEIVED");

        // Custom Design Attributes
        order.setDesignImageUrl(request.getDesignImageUrl());
        order.setColor(request.getColor());
        order.setCollarStyle(request.getCollarStyle());
        order.setSleeveStyle(request.getSleeveStyle());
        order.setPocketStyle(request.getPocketStyle());
        order.setSpecialInstructions(request.getSpecialInstructions());
        order.setCustomMeasurements(request.getCustomMeasurements());

        // Price calculation
        Double calculatedPrice = (request.getPrice() != null && request.getPrice() > 0)
                ? request.getPrice()
                : getDefaultEstimatedPrice(request.getGarmentType());
        order.setPrice(calculatedPrice);

        return orderRepository.save(order);
    }

    public List<Order> getOrdersByUserId(String userId) {
        return orderRepository.findByUserId(userId);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll(Sort.by(Sort.Direction.DESC, "orderDate"));
    }

    public Order getOrderById(String id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found with id: " + id));
    }

    public OrderDetailsResponse getOrderDetailsById(String id) {
        Order order = getOrderById(id);
        User user = userRepository.findById(order.getUserId()).orElse(null);
        Measurement measurement = null;
        if (order.getMeasurementId() != null && !order.getMeasurementId().isBlank()) {
            measurement = measurementRepository.findById(order.getMeasurementId()).orElse(null);
        }

        return OrderDetailsResponse.fromEntity(order, user, measurement);
    }

    public Order updateOrderStatus(String id, String status) {
        if (status == null || !VALID_STATUSES.contains(status.trim().toUpperCase())) {
            throw new IllegalArgumentException("Invalid status. Allowed values: " + VALID_STATUSES);
        }

        Order order = getOrderById(id);
        order.setStatus(status.trim().toUpperCase());
        return orderRepository.save(order);
    }

    public Order updateOrderPrice(String id, Double price) {
        if (price == null || price < 0) {
            throw new IllegalArgumentException("Price must be greater than or equal to 0");
        }

        Order order = getOrderById(id);
        order.setPrice(price);
        return orderRepository.save(order);
    }

    public Order updateOrderNotes(String id, String notes) {
        Order order = getOrderById(id);
        order.setMasterNotes(notes);
        return orderRepository.save(order);
    }

    private Double getDefaultEstimatedPrice(String garmentType) {
        if (garmentType == null) return 500.0;
        return switch (garmentType.toLowerCase()) {
            case "shirt" -> 450.0;
            case "pant", "trousers" -> 550.0;
            case "suit", "blazer" -> 2500.0;
            case "kurta" -> 600.0;
            case "dress", "blouse" -> 700.0;
            default -> 500.0;
        };
    }
}
