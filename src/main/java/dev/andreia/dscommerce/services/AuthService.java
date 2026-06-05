package dev.andreia.dscommerce.services;

import dev.andreia.dscommerce.entities.User;
import dev.andreia.dscommerce.services.exceptions.ForbiddenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    public void validateSelfOrAdmin(Long orderOwnerId){
        User loggedUser = userService.getLoggedUsed();

        if(!loggedUser.getId().equals(orderOwnerId) && !loggedUser.hasRole("ROLE_ADMIN")){
            throw new ForbiddenException("Access denied");
        }
    }
}
