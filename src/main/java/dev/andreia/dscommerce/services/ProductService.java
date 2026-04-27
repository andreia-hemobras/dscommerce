package dev.andreia.dscommerce.services;

import dev.andreia.dscommerce.dto.ProductDTO;
import dev.andreia.dscommerce.entities.Product;
import dev.andreia.dscommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public ProductDTO findById(Long id){
        Product product = repository.findById(id).get();
        return new ProductDTO(product);
    }
}
