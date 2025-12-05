package com.bloodbank.controller;

import com.bloodbank.model.Donor;
import com.bloodbank.service.DonorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/donors")
public class DonorController {

    private final DonorService service;

    public DonorController(DonorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Donor> addDonor(@RequestBody Donor donor) {
        Donor saved = service.save(donor);
        return ResponseEntity.created(URI.create("/api/donors/" + saved.getId())).body(saved);
    }

    @GetMapping
    public List<Donor> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Donor> getById(@PathVariable Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/group/{bg}")
    public List<Donor> byGroup(@PathVariable String bg) {
        return service.getByGroup(bg);
    }

    @GetMapping("/city/{city}")
    public List<Donor> byCity(@PathVariable String city) {
        return service.getByCity(city);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
