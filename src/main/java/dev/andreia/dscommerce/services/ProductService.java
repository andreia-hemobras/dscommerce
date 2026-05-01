package dev.andreia.dscommerce.services;

import dev.andreia.dscommerce.dto.ProductDTO;
import dev.andreia.dscommerce.entities.Product;
import dev.andreia.dscommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id){
        Product product = repository.findById(id).get();
        return new ProductDTO(product);
    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable){
        Page<Product> page = repository.findAll(pageable);
        return page.map(product -> new ProductDTO(product));
    }
}
