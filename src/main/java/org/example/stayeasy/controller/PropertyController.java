package org.example.stayeasy.controller;

import org.example.stayeasy.model.Property;
import org.example.stayeasy.service.PropertyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class PropertyController {

    private PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @GetMapping("property/")
    public List<Property> getAllProperties() {
        return propertyService.findAll();
    }

    @GetMapping("property/{id}")
    public Property getPropertyById(Long id) {
        return propertyService.findById(id);
    }

    @PostMapping("property/")
    public void postProperty(@RequestBody Property property) {
        propertyService.save(property);
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
