package com.lunchbox.businesses;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class BusinessModelAssembler implements RepresentationModelAssembler<Business, EntityModel<Business>> {

    @Override
    public EntityModel<Business> toModel(Business business) {

        return EntityModel.of(business,
                linkTo(methodOn(BusinessController.class).one(business.getId())).withSelfRel(),
                linkTo(methodOn(BusinessController.class).all()).withRel("businesses"));
    }
}
