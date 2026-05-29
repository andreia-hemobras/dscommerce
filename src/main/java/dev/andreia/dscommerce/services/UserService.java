package dev.andreia.dscommerce.services;

import dev.andreia.dscommerce.entities.Role;
import dev.andreia.dscommerce.entities.User;
import dev.andreia.dscommerce.projections.UserRoleProjection;
import dev.andreia.dscommerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserRoleProjection> projections = userRepository.searchUserAndRolesByEmail(username);
        if(projections.isEmpty()){
            throw new UsernameNotFoundException("Email not found");
        }

        User user = new User();
        user.setEmail(username);
        user.setPassword(projections.get(0).getPassword());

        for (UserRoleProjection projection : projections){
            user.addRole(new Role(projection.getRoleId(), projection.getAuthority()));
        }

        return user;
    }
}
