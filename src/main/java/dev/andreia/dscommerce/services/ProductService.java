package dev.andreia.dscommerce.services;

import dev.andreia.dscommerce.dto.CategoryDto;
import dev.andreia.dscommerce.dto.ProductDTO;
import dev.andreia.dscommerce.dto.ProductMinDto;
import dev.andreia.dscommerce.entities.Category;
import dev.andreia.dscommerce.entities.Product;
import dev.andreia.dscommerce.repositories.CategoryRepository;
import dev.andreia.dscommerce.repositories.ProductRepository;
import dev.andreia.dscommerce.services.exceptions.DatabaseException;
import dev.andreia.dscommerce.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id){
        Product product = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado"));
        return new ProductDTO(product);
    }

    @Transactional(readOnly = true)
    public Page<ProductMinDto> findAll(String name, Pageable pageable){
        Page<Product> page = repository.searchByName(name, pageable);
        return page.map(product -> new ProductMinDto(product));
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto){
        Product entity = new Product();
        copyDtoToEntity(dto, entity);
        repository.save(entity);
        return new ProductDTO(entity);
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto){
        try{
            Product entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            repository.save(entity);
            return new ProductDTO(entity);
        } catch(EntityNotFoundException ex){
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }

    //@Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id){
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException("Recurso não encontrado");
        }

        try{
            repository.deleteById(id);
        } catch(DataIntegrityViolationException ex){
            throw new DatabaseException("Falha de integridade referencial");
        }

    }

    private void copyDtoToEntity(ProductDTO dto, Product entity) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setImgUrl(dto.getImgUrl());

        entity.getCategories().clear();

        for(CategoryDto categoryDto : dto.getCategories()){
            Category category = categoryRepository.getReferenceById(categoryDto.getId());
            entity.getCategories().add(category);
        }
    }

}