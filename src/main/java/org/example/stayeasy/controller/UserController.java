package org.example.stayeasy.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.example.stayeasy.model.Property;
import org.example.stayeasy.model.User;
import org.example.stayeasy.repository.UserRepository;
import org.example.stayeasy.service.UserService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.stayeasy.assembler.UserModelAssembler;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/")
public class UserController {

    private UserService userService;
    private UserModelAssembler userModelAssembler;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("user/")
    public CollectionModel<EntityModel<User>> findAll() {
        //HATEOAS
        return userModelAssembler.toCollectionModel(userService.findAll());
    }

    @GetMapping("user/{id}")
    public ResponseEntity<EntityModel<User>> getUserById(@PathVariable Long id) {

       //HATEOAS
        try {
            return new ResponseEntity<>(
                    userModelAssembler.toModel(userService.get(id)), HttpStatus.OK
            );
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            throw e;
        }
    }

    @PostMapping("user/")
    public void post(@RequestBody User user) {
        userService.save(user);
    }

    @PutMapping("user/{id}")
    public void updateUser(@RequestBody User user) {
         userService.save(user);
    }

    @DeleteMapping("user/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }


    //Jointable
    @PostMapping("user/{userId}/properties/{propertyId}")
    public User addPropertyToUser(
            @PathVariable Long userId,
            @PathVariable Long propertyId) {
        return userService.addPropertyToUser(userId, propertyId);
    }

    @Operation(summary = "Get all properties rented by a user")
    @GetMapping("user/{userId}/properties")
    public List<Property> getPropertyToUser(
            @PathVariable Long userId) {
        return userService.getUserProperties(userId);
    }

    @DeleteMapping("user/{userId}/properties/{propertyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removePropertyFromUser(
            @PathVariable Long userId,
            @PathVariable Long propertyId) {

        userService.removePropertyFromUser(userId, propertyId);
    }
}
