package mk.ukim.finki.wpaud.service;

import mk.ukim.finki.wpaud.model.Manufacturer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ManufacturerService {
    List<Manufacturer> findAll();
    Optional<Manufacturer> findById(Long Id);
    Optional<Manufacturer> save(String name, String address);
    void deleteById(Long id);
}
