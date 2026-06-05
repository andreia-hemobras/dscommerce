package dev.andreia.dscommerce.dto;

import dev.andreia.dscommerce.entities.Payment;

import java.time.Instant;

public class PaymentDto {

    private Long id;
    private Instant moment;

    public PaymentDto() {
    }

    public PaymentDto(Long id, Instant moment) {
        this.id = id;
        this.moment = moment;
    }

    public PaymentDto(Payment entity) {
        id = entity.getId();
        moment = entity.getMoment();
    }

    public Long getId() {
        return id;
    }

    public Instant getMoment() {
        return moment;
    }
}
