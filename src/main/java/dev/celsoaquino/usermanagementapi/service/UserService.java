package dev.celsoaquino.usermanagementapi.service;

import dev.celsoaquino.usermanagementapi.dto.UserDTO;
import dev.celsoaquino.usermanagementapi.model.Role;
import dev.celsoaquino.usermanagementapi.model.User;
import dev.celsoaquino.usermanagementapi.repository.RoleRepository;
import dev.celsoaquino.usermanagementapi.repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDTO createUser(UserDTO userDTO) {
       try {
           if (userRepository.existsByUsername(userDTO.name())) {
               throw new IllegalArgumentException(("User already exists"));
           }

           User user = new User();
           user.setUsername(userDTO.name());
           user.setPassword(passwordEncoder.encode(userDTO.password()));

           Set<Role> roles = new HashSet<>();
           Role userRole = roleRepository.findByName("ROLE_USER")
               .orElseThrow(() -> new IllegalArgumentException("Error: Role is not found."));
           roles.add(userRole);
           user.setRoles(roles);

           userRepository.save(user);

           return new UserDTO(user.getId(), user.getUsername(), user.getPassword());

       } catch (DataIntegrityViolationException e) {
           throw new IllegalArgumentException("Erro de integridade de dados: " + e.getMessage());
       }
    }

}
