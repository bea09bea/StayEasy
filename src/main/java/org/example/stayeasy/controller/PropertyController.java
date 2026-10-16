package org.example.stayeasy.controller;

import jakarta.validation.Valid;
import org.example.stayeasy.model.Property;
import org.example.stayeasy.service.PropertyService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.example.stayeasy.assembler.PropertyModelAssembler;
import org.springframework.web.bind.annotation.*;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/")
public class PropertyController {

    private PropertyService propertyService;
    private PropertyModelAssembler propertyModelAssembler;

    public PropertyController(PropertyService propertyService, PropertyModelAssembler propertyModelAssembler) {
        this.propertyService = propertyService;
        this.propertyModelAssembler = propertyModelAssembler;
    }

    //hämta alla propety i databasen
    @GetMapping("property/")
    public CollectionModel<EntityModel<Property>> findAll() {
        //HATEOAS
        return propertyModelAssembler.toCollectionModel(propertyService.findAll());
    }

    //hämta ett property med id
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

    //skapa property
    @PostMapping("property/")
    public ResponseEntity<Property> postProperty(
           @Valid @RequestBody Property property) {
        Property savedProperty = propertyService.save(property);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedProperty);
    }

    //updatera property
    @PutMapping("property/{id}")
    public  ResponseEntity<Void> updateProperty(
            @PathVariable Long id,
            @Valid @RequestBody Property property) {
        try {
            propertyService.update(id, property);
            return ResponseEntity.noContent().build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

    }

    //radera property med id
    @DeleteMapping("property/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePropertyById(@PathVariable Long id) {
        propertyService.delete(id);
    }

}
