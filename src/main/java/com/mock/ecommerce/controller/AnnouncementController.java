package com.mock.ecommerce.controller;

import com.mock.ecommerce.exception.ResourceNotFoundException;
import com.mock.ecommerce.model.Announcement;
import com.mock.ecommerce.repository.AnnouncementRepository;
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
public class AnnouncementController {
    @Autowired
    private AnnouncementRepository announcementRepository;

    @GetMapping("/announcements")
    public List<Announcement> getAllAnnouncements(){
        return announcementRepository.findAll();
    }

    @PostMapping("/announcements")
    @PreAuthorize("hasRole('ADMIN')")
    public Announcement createAnnouncement(@Valid @RequestBody Announcement announcement){
        return announcementRepository.save(announcement);
    }

    @PutMapping("/announcements/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Announcement> updateAnnouncement(@PathVariable("id") String id, @Valid @RequestBody Announcement announcement) throws ResourceNotFoundException {
        Announcement _announcement = announcementRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Announcement not found"));
        _announcement.setImage(announcement.getImage());
        _announcement.setText(announcement.getText());
        final Announcement updatedAnnouncement = announcementRepository.save(_announcement);
        return ResponseEntity.ok(updatedAnnouncement);
    }

    @DeleteMapping("/announcements/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String, Boolean> deleteAnnouncement(@PathVariable(value = "id") String id)
            throws ResourceNotFoundException {
        Announcement announcement = announcementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Announcement not found"));

        announcementRepository.delete(announcement);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
