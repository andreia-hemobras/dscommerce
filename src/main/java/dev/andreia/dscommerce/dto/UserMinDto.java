package dev.andreia.dscommerce.dto;

import dev.andreia.dscommerce.entities.User;

public class UserMinDto {

    private Long id;
    private String name;

    public UserMinDto() {
    }

    public UserMinDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public UserMinDto(User entity) {
        id = entity.getId();
        name = entity.getName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
