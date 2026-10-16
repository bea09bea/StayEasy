package org.example.stayeasy.service;

import org.example.stayeasy.exception.DuplicationResourceException;
import org.example.stayeasy.exception.ResourceNotFoundException;
import org.example.stayeasy.model.Property;
import org.example.stayeasy.model.User;
import org.example.stayeasy.repository.PropertyRepository;
import org.example.stayeasy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;
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

    //GET
    public List<User> findAll() {
        return userRepository.findAll();
    }

    //GET
    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));
    }

    //PUT
    public User updateUser(Long id, User user) {

        // Kontrollerar att användaren finns i databasen, annars kastas ett 404-fel
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User with id " + id + " does not exist"
                ));

        // Kontrollerar att telefonnummer och email inte används av en annan användare
        if (userRepository.existsByPhoneAndIdNot(user.getPhone(), id)) {
            throw new DuplicationResourceException(
                    "Phone number already exists"
            );
        }

        if (userRepository.existsByEmailAndIdNot(user.getEmail(), id)) {
            throw new DuplicationResourceException(
                    "Email already exists"
            );
        }

        // Uppdaterar användarens uppgifter med de nya värdena
        existingUser.setName(user.getName());
        existingUser.setPhone(user.getPhone());
        existingUser.setEmail(user.getEmail());

        return userRepository.save(existingUser);
    }

    //POST
    public void save(User user) {

        // Kontrollerar att telefonnummer och email inte används av en annan användare
        if (userRepository.existsByPhone(user.getPhone())) {
            throw new DuplicationResourceException(
                    "Phone number already exists"
            );
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new DuplicationResourceException(
                    "Email already exists"
            );
        }

        userRepository.save(user);
    }

    //DELETE
    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "User with id " + id + " not found"
            );
        }

        if (propertyRepository.existsByHostId(id)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Cannot delete user because it owns properties"
            );
        }

        userRepository.deleteById(id);
    }

    //User hyr property - POST
    public User addPropertyToUser(Long userId, Long propertyId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found with id: " + propertyId));

        user.getProperties().add(property);

        return userRepository.save(user);
    }

    //Hämta lista över alla properties en user hyr - GET
    public List<Property> getUserProperties(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        return user.getProperties();
    }

    //user slutar hyra en property - DELETE
    public void removePropertyFromUser(Long userId, Long propertyId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found with id: " + propertyId));

        user.getProperties().remove(property);
        userRepository.save(user);
    }
}
