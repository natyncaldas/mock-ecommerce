package com.mock.ecommerce.controller;

import com.mock.ecommerce.exception.ResourceNotFoundException;
import com.mock.ecommerce.model.Order;
import com.mock.ecommerce.model.Product;
import com.mock.ecommerce.model.User;
import com.mock.ecommerce.repository.OrderRepository;
import com.mock.ecommerce.repository.ProductRepository;
import com.mock.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class OrderController {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/orders")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    @GetMapping("/users/{id}/orders")
    @PreAuthorize("hasAnyRole('USER','MODERATOR', 'ADMIN') and #id == authentication.principal.id")
    public List<Order> getOrderByUserId(@PathVariable("id") String id){
        return orderRepository.findByUserId(id);
    }

    //TODO fix adding products
    @PostMapping("/orders")
    @PreAuthorize("hasAnyRole('USER','MODERATOR', 'ADMIN') and #order.userId == authentication.principal.id")
    public Order createOrder(@Valid @RequestBody Order order) throws ResourceNotFoundException {
        User user = userRepository.findById(order.getUserId()).orElseThrow(()->new ResourceNotFoundException("User not found"));
        order.setProducts(user.getCart());
        return orderRepository.save(order);
    }

    @DeleteMapping("/users/{userId}/orders/{id}")
    public Map<String, Boolean> deleteOrderById(@PathVariable(value = "id") String id, @PathVariable("userId") String userId)
            throws ResourceNotFoundException {
        Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        if(!userId.equals(order.getUserId())){
            throw new AccessDeniedException("Access Denied");
        }
        orderRepository.delete(order);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
