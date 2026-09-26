package com.tailor.controller;

import com.tailor.dto.OrderCreateRequest;
import com.tailor.dto.OrderDetailsResponse;
import com.tailor.dto.OrderNotesUpdateRequest;
import com.tailor.dto.OrderPriceUpdateRequest;
import com.tailor.dto.OrderStatusUpdateRequest;
import com.tailor.model.Order;
import com.tailor.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderController {

    private final OrderService orderService;
    private final Path uploadDirectory = Paths.get("uploads");

    // Constructor Injection
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
        try {
            if (!Files.exists(uploadDirectory)) {
                Files.createDirectories(uploadDirectory);
            }
        } catch (IOException e) {
            System.err.println("Could not create upload directory: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@Valid @RequestBody OrderCreateRequest request) {
        Order created = orderService.createOrder(request);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PostMapping(value = "/upload-image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, String>> uploadDesignImage(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Uploaded file cannot be empty"));
        }

        String originalFilename = file.getOriginalFilename();
        String extension = "";
        if (originalFilename != null && originalFilename.contains(".")) {
            extension = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
        }

        if (!extension.matches("\\.(jpg|jpeg|png|webp|gif)")) {
            return ResponseEntity.badRequest().body(Map.of("error", "Only JPG, JPEG, PNG, or WEBP images are supported"));
        }

        try {
            String newFileName = "design_" + UUID.randomUUID() + extension;
            Path targetLocation = this.uploadDirectory.resolve(newFileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            String fileUrl = "/uploads/" + newFileName;
            return ResponseEntity.ok(Map.of(
                    "imageUrl", fileUrl,
                    "filename", newFileName,
                    "message", "Design image uploaded successfully"
            ));
        } catch (IOException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Could not upload file: " + ex.getMessage()));
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> getOrdersByUserId(@PathVariable String userId) {
        List<Order> orders = orderService.getOrdersByUserId(userId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable String id) {
        Order order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<OrderDetailsResponse> getOrderDetails(@PathVariable String id) {
        OrderDetailsResponse details = orderService.getOrderDetailsById(id);
        return ResponseEntity.ok(details);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Order> updateOrderStatus(
            @PathVariable String id,
            @Valid @RequestBody OrderStatusUpdateRequest request) {
        Order updated = orderService.updateOrderStatus(id, request.getStatus());
        return ResponseEntity.ok(updated);
    }

    @PutMapping("/{id}/price")
    public ResponseEntity<Order> updateOrderPrice(
            @PathVariable String id,
            @Valid @RequestBody OrderPriceUpdateRequest request) {
        Order updated = orderService.updateOrderPrice(id, request.getPrice());
        return ResponseEntity.ok(updated);
    }

    @PutMapping("/{id}/notes")
    public ResponseEntity<Order> updateOrderNotes(
            @PathVariable String id,
            @RequestBody OrderNotesUpdateRequest request) {
        Order updated = orderService.updateOrderNotes(id, request.getNotes());
        return ResponseEntity.ok(updated);
    }
}
