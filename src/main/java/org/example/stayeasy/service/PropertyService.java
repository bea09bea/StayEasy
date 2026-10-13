package org.example.stayeasy.service;

import jakarta.transaction.Transactional;
import org.example.stayeasy.model.User;
import org.example.stayeasy.repository.PropertyRepository;
import org.example.stayeasy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.example.stayeasy.model.Property;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PropertyService {

    private PropertyRepository propertyRepository;
    private UserRepository userRepository;

    @Autowired
    public void PropertyService(PropertyRepository propertyRepository,  UserRepository userRepository) {
        this.propertyRepository = propertyRepository;
        this.userRepository = userRepository;
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
