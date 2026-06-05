package dev.andreia.dscommerce.services;

import dev.andreia.dscommerce.dto.OrderDto;
import dev.andreia.dscommerce.dto.OrderItemDto;
import dev.andreia.dscommerce.entities.Order;
import dev.andreia.dscommerce.entities.OrderItem;
import dev.andreia.dscommerce.entities.Product;
import dev.andreia.dscommerce.entities.enums.OrderStatus;
import dev.andreia.dscommerce.repositories.OrderRepository;
import dev.andreia.dscommerce.repositories.ProductRepository;
import dev.andreia.dscommerce.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @Transactional(readOnly = true)
    public OrderDto findById(Long id){
        Order order = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado"));
        authService.validateSelfOrAdmin(order.getClient().getId());
        return new OrderDto(order);
    }

    @Transactional
    public OrderDto insert(OrderDto dto){
        Order order = new Order();
        order.setMoment(Instant.now());
        order.setStatus(OrderStatus.WAITING_PAYMENT);
        order.setClient(userService.getLoggedUsed());

        for(OrderItemDto itemDto : dto.getItems()){
            Product product = productRepository.getReferenceById(itemDto.getProductId());
            OrderItem item = new OrderItem(order, product, itemDto.getQuantity(), product.getPrice());
            order.getItems().add(item);
        }

        repository.save(order);

        return new OrderDto(order);
    }
}
