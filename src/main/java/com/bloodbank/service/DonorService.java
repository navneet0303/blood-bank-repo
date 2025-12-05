package com.bloodbank.service;

import com.bloodbank.model.Donor;
import com.bloodbank.repository.DonorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DonorService {

    private final DonorRepository repo;

    public DonorService(DonorRepository repo) {
        this.repo = repo;
    }

    public Donor save(Donor donor) {
        return repo.save(donor);
    }

    public List<Donor> getAll() {
        return repo.findAll();
    }

    public Optional<Donor> getById(Long id) {
        return repo.findById(id);
    }

    public List<Donor> getByGroup(String bg) {
        return repo.findByBloodGroup(bg);
    }

    public List<Donor> getByCity(String city) {
        return repo.findByCity(city);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}
