package org.example.stayeasy.assembler;

import org.example.stayeasy.controller.UserController;
import org.example.stayeasy.model.User;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import java.util.Collection;

//returnera HATEOAS-resurser med länkar
@Component
public class UserModelAssembler implements RepresentationModelAssembler<User, EntityModel<User>> {

    @Override
    public EntityModel<User> toModel(User user) {
        EntityModel<User> entityModel = EntityModel.of(
                user,
                linkTo(
                        methodOn(UserController.class).getUserById(user.getId())
                ).withSelfRel(),
                linkTo(
                        methodOn(UserController.class).findAll()
                ).withRel("users")
        );

        return entityModel;
    }

    public CollectionModel<EntityModel<User>> toCollectionModel(Collection<User> users) {
        return RepresentationModelAssembler.super.toCollectionModel(users)
                .add(
                        linkTo(methodOn(UserController.class).findAll()).withSelfRel()
                );
    }
}
