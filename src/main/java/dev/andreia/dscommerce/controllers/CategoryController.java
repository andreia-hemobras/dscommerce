package dev.andreia.dscommerce.controllers;

import dev.andreia.dscommerce.dto.CategoryDto;
import dev.andreia.dscommerce.services.CategorytService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategorytService service;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> findAll(){
        List<CategoryDto> categories = service.findAll();
        return ResponseEntity.ok().body(categories);
    }
}
