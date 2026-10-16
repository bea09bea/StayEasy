package org.example.stayeasy.service;

import jakarta.transaction.Transactional;
import org.example.stayeasy.exception.ResourceNotFoundException;
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

    //GET
    public List<Property> findAll() {
        return propertyRepository.findAll();
    }

    //GET
    public Property findById(Long id) {
        return propertyRepository.findById(id).get();
    }

    //PUT
    public Property update(Long id, Property property) {

        // Kontrollerar att property som ska uppdateras finns i databasen
        Property existingProperty = propertyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Property with id " + id + " does not exist"
                ));

        // Kontrollerar att användaren som anges som host finns i databasen
        Long hostId = property.getHostId();

        if (!userRepository.existsById(hostId)) {
            throw new ResourceNotFoundException(
                    "User with id " + hostId + " does not exist, create user first"
            );
        }

        // Uppdaterar property med de nya värdena
        existingProperty.setTitle(property.getTitle());
        existingProperty.setDescription(property.getDescription());
        existingProperty.setAdress(property.getAdress());
        existingProperty.setPricePerNight(property.getPricePerNight());
        existingProperty.setMaxGuests(property.getMaxGuests());
        existingProperty.setHostId(property.getHostId());

        return propertyRepository.save(existingProperty);
    }

    //POST
    public Property save(Property property) {

        Long hostId = property.getHostId();

        if (!userRepository.existsById(hostId)) {
            throw new ResourceNotFoundException(
                    "User with id " + hostId + " does not exist, create user first"
            );
        }

        return propertyRepository.save(property);
    }

    //DELETE
    public void delete(Long id) {
        if (!propertyRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Property with id " + id + " not found"
            );
        }

        propertyRepository.deleteById(id);
        propertyRepository.flush();
    }
}
