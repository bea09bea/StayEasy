package org.example.stayeasy.controller;

import jakarta.validation.Valid;
import org.example.stayeasy.model.Property;
import org.example.stayeasy.service.PropertyService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.example.stayeasy.assembler.PropertyModelAssembler;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/")
public class PropertyController {

    private PropertyService propertyService;
    private PropertyModelAssembler propertyModelAssembler;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @GetMapping("property/")
    public CollectionModel<EntityModel<Property>> getAllProperties() {
        //HATEOAS
        return propertyModelAssembler.toCollectionModel(propertyService.findAll());
    }

    @GetMapping("property/{id}")
    public ResponseEntity<EntityModel<Property>> getPropertyById(@PathVariable Long id) {
        //HATEOAS
        try {
            return new ResponseEntity<>(
                    propertyModelAssembler.toModel(propertyService.findById(id)),
                    HttpStatus.OK
            );
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            throw e;
        }
    }

    @PostMapping("property/")
    public ResponseEntity<Property> postProperty(
           @Valid @RequestBody Property property) {
        Property savedProperty = propertyService.save(property);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedProperty);
    }

    @PutMapping("property/{id}")
    public  void updateProperty(@RequestBody Property property) {
        propertyService.save(property);
    }

    @DeleteMapping("property/{id}")
    public void deletePropertyById(@PathVariable Long id) {
        propertyService.delete(id);
    }

}
