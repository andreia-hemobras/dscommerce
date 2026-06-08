package dev.andreia.dscommerce.services;

import dev.andreia.dscommerce.dto.CategoryDto;
import dev.andreia.dscommerce.entities.Category;
import dev.andreia.dscommerce.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategorytService {

    @Autowired
    private CategoryRepository repository;

    @Transactional(readOnly = true)
    public List<CategoryDto> findAll(){
        List<Category> categories = repository.findAll();
        return categories.stream().map(CategoryDto::new).toList();
    }
}
