package com.lunchbox.menu;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MenuItemController {

    private final MenuItemRepository repository;

    MenuItemController(MenuItemRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/businesses/{id}/menu")
    List<MenuItem> menu(@PathVariable Long id) {
        return repository.findByBusinessId(id);
    }
}
