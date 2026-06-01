package dev.andreia.dscommerce.controllers;

import dev.andreia.dscommerce.dto.UserDto;
import dev.andreia.dscommerce.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @PreAuthorize(value = "hasAnyRole('ROLE_CLIENT', 'ROLE_ADMIN')")
    @GetMapping("/me")
    public ResponseEntity<UserDto> getMe(){
        UserDto dto = service.getMe();
        return ResponseEntity.ok(dto);
    }
}
