package dev.andreia.dscommerce.dto;

import dev.andreia.dscommerce.entities.Order;
import dev.andreia.dscommerce.entities.OrderItem;
import dev.andreia.dscommerce.entities.enums.OrderStatus;
import jakarta.validation.constraints.NotEmpty;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class OrderDto {

    private Long id;
    private Instant moment;
    private OrderStatus status;
    private UserMinDto client;
    private PaymentDto payment;

    @NotEmpty(message = "A lista não pode ser vazia")
    private List<OrderItemDto> items = new ArrayList<>();

    public OrderDto() {
    }

    public OrderDto(Long id, Instant moment, OrderStatus status, UserMinDto client, PaymentDto payment) {
        this.id = id;
        this.moment = moment;
        this.status = status;
        this.client = client;
        this.payment = payment;
    }

    public OrderDto(Order entity) {
        id = entity.getId();
        moment = entity.getMoment();
        status = entity.getStatus();
        client = new UserMinDto(entity.getClient());
        payment = entity.getPayment() == null ? null : new PaymentDto(entity.getPayment());

        for(OrderItem item : entity.getItems()){
            items.add(new OrderItemDto(item));
        }
    }

    public Long getId() {
        return id;
    }

    public Instant getMoment() {
        return moment;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public UserMinDto getClient() {
        return client;
    }

    public PaymentDto getPayment() {
        return payment;
    }

    public List<OrderItemDto> getItems() {
        return items;
    }

    public Double getTotal(){
        return items
                .stream()
                .mapToDouble(OrderItemDto::getSubTotal)
                .reduce(0D, Double::sum);
    }
}
