package com.lunchbox.businesses;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BusinessController {

    private final BusinessRepository repository;

    BusinessController(BusinessRepository repository) {
        this.repository = repository;
    }

    // aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/businesses")
    List<Business> all() {
        return repository.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/businesses")
    Business newBusiness(@RequestBody Business newBusiness) {
        return repository.save(newBusiness);
    }

    @GetMapping("/businesses/{id}")
    Business one(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new BusinessNotFoundException(id));
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
