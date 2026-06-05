package dev.andreia.dscommerce.services;

import dev.andreia.dscommerce.dto.OrderDto;
import dev.andreia.dscommerce.entities.Order;
import dev.andreia.dscommerce.repositories.OrderRepository;
import dev.andreia.dscommerce.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Transactional(readOnly = true)
    public OrderDto findById(Long id){
        Order order = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado"));

        return new OrderDto(order);
    }
}
