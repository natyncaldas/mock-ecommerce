package com.mock.ecommerce.controller;

import com.mock.ecommerce.exception.ResourceNotFoundException;
import com.mock.ecommerce.model.SellerRequest;
import com.mock.ecommerce.repository.SellerRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class SellerRequestController {
    @Autowired
    private SellerRequestRepository sellerRequestRepository;

    @GetMapping("/sellers/requests")
    @PreAuthorize("hasRole('ADMIN')")
    public List<SellerRequest> getAllSellerRequests(){
        return sellerRequestRepository.findAll();
    }

    @GetMapping("/users/{id}/sellers/requests")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER') and #id == authentication.principal.id")
    public ResponseEntity<SellerRequest> getSellerRequestByUserId(@PathVariable("id") String id) {
        SellerRequest sellerRequest = sellerRequestRepository.findByUserId(id);
        return ResponseEntity.ok(sellerRequest);
    }

    @PostMapping("/sellers/requests")
    @PreAuthorize("!hasRole('MODERATOR') and #sellerRequest.userId == authentication.principal.id")
    public SellerRequest createSellerRequest(@Valid @RequestBody SellerRequest sellerRequest){
        return sellerRequestRepository.save(sellerRequest);
    }

    @DeleteMapping("/sellers/requests/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String, Boolean> deleteSellerRequestById(@PathVariable(value = "id") String id)
            throws ResourceNotFoundException {
            SellerRequest sellerRequest = sellerRequestRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Seller request not found"));
        sellerRequestRepository.delete(sellerRequest);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
