package com.lunchbox.businesses;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
public class BusinessController {

    private final BusinessRepository repository;
    private final BusinessModelAssembler assembler;

    BusinessController(BusinessRepository repository, BusinessModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    // aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/businesses")
    CollectionModel<EntityModel<Business>> all() {
        List<EntityModel<Business>> businesses = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(businesses, linkTo(methodOn(BusinessController.class).all()).withSelfRel());
    }
    // end::get-aggregate-root[]

    @PostMapping("/businesses")
    Business newBusiness(@RequestBody Business newBusiness) {
        return repository.save(newBusiness);
    }

    @GetMapping("/businesses/{id}")
    EntityModel<Business> one(@PathVariable Long id) {
        Business business = repository.findById(id)
                .orElseThrow(() -> new BusinessNotFoundException(id));

        return assembler.toModel(business);
    }

    @PutMapping("/businesses/{id}")
    Business replaceBusiness(@RequestBody Business newBusiness, @PathVariable Long id) {
        return repository.findById(id).map(business -> {
            business.setName(newBusiness.getName());
            business.setType(newBusiness.getType());
            business.setAddress(newBusiness.getAddress());
            business.setPhoneNumber(newBusiness.getPhoneNumber());
            business.setEmail(newBusiness.getEmail());
            business.setDescription(newBusiness.getDescription());
            business.setPriceRange(newBusiness.getPriceRange());
            business.setIsActive(newBusiness.getIsActive());
            return repository.save(business);
        }).orElseGet(() -> repository.save(newBusiness));
    }

    @DeleteMapping("/businesses/{id}")
    Business deleteBusiness(@PathVariable Long id) {
        return repository.findById(id).map(business -> {
            business.setIsActive(false);
            return repository.save(business);
        }).orElseThrow(() -> new BusinessNotFoundException(id));
    }
}
