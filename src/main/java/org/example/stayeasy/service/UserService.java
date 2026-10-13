package org.example.stayeasy.service;

import org.example.stayeasy.exception.ResourceNotFoundException;
import org.example.stayeasy.model.Property;
import org.example.stayeasy.model.User;
import org.example.stayeasy.repository.PropertyRepository;
import org.example.stayeasy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    private UserRepository userRepository;
    private PropertyRepository propertyRepository;

    @Autowired
    public UserService(UserRepository userRepository, PropertyRepository propertyRepository) {

        this.userRepository = userRepository;
        this.propertyRepository = propertyRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User get(Long id) {

        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    //User hyr property
    public User addPropertyToUser(Long userId, Long propertyId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found with id: " + propertyId));

        user.getProperties().add(property);

        return userRepository.save(user);
    }

    //Hämta lista över alla properties en user hyr
    public List<Property> getUserProperties(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        return user.getProperties();
    }

    //user slutar hyra en property
    public void removePropertyFromUser(Long userId, Long propertyId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        Property property = propertyRepository.findById(propertyId)
                        .orElseThrow(() -> new ResourceNotFoundException("Property not found with id: " + propertyId));

        user.getProperties().remove(property);
        userRepository.save(user);
    }
}
