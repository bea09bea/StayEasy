package org.example.stayeasy.service;

import jakarta.transaction.Transactional;
import org.example.stayeasy.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.example.stayeasy.model.Property;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PropertyService {

    private PropertyRepository propertyRepository;

    @Autowired
    public void PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    public List<Property> findAll() {
        return propertyRepository.findAll();
    }

    public Property findById(Long id) {
        return propertyRepository.findById(id).get();
    }

    public Property save(Property property) {
        return propertyRepository.save(property);
    }

    public void delete(Long id) {
        propertyRepository.deleteById(id);
    }

}
