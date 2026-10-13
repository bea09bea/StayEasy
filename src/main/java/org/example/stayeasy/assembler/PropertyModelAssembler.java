package org.example.stayeasy.assembler;

import org.example.stayeasy.controller.PropertyController;
import org.example.stayeasy.model.Property;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

//returnera HATEOAS-resurser med länkar
@Component
public class PropertyModelAssembler
        implements RepresentationModelAssembler<Property, EntityModel<Property>> {

    @Override
    public EntityModel<Property> toModel(Property property) {
        return EntityModel.of(
                property,
                linkTo(methodOn(PropertyController.class)
                        .getPropertyById(property.getId()))
                        .withSelfRel(),

                linkTo(methodOn(PropertyController.class)
                        .getAllProperties())
                        .withRel("All Properties")
        );
    }
}
