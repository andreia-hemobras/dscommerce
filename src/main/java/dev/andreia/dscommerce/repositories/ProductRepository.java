package dev.andreia.dscommerce.repositories;

import dev.andreia.dscommerce.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT p " +
            "FROM Product p " +
            "WHERE UPPER(p.name) LIKE CONCAT('%', UPPER(:name), '%' )"
    )
    Page<Product> searchByName(String name, Pageable pageable);
}
