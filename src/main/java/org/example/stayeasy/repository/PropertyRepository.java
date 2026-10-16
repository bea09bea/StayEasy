package org.example.stayeasy.repository;

import org.example.stayeasy.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;

//kommunicerar med databas
public interface PropertyRepository extends JpaRepository<Property, Long> {
    boolean existsByHostId(Long hostId);
}
