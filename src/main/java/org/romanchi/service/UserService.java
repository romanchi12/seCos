package org.romanchi.service;

import org.romanchi.exceptions.NotFoundException;
import org.romanchi.model.AlergenItem;
import org.romanchi.model.Role;
import org.romanchi.model.User;
import org.romanchi.model.dto.UserDTO;
import org.romanchi.repository.RoleRepository;
import org.romanchi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SecurityService securityService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    CosmeticItemService cosmeticItemService;
    @Autowired
    RoleRepository roleRepository;

    public Set<AlergenItem> findAllForUser(){
        User user = securityService.getCurrentUser();
        return user.getAlergenItems();
    }

    public Set<AlergenItem> getAlergensByBarcodeForCurrentUser(String barcode) {
        User user = securityService.getCurrentUser();
        Set<AlergenItem> userAlergenItems = user.getAlergenItems();
        Set<AlergenItem> cosmeticAlergenItems = cosmeticItemService.findByBarcode(barcode).getAlergenItems();
        Set<AlergenItem> dangerAlergen = new LinkedHashSet<>();
        cosmeticAlergenItems.forEach(cosmeticAlergenItem -> {
            if(userAlergenItems.contains(cosmeticAlergenItem)){
                dangerAlergen.add(cosmeticAlergenItem);
            }
        });
        return dangerAlergen;
    }

    public User register(User user) {
        if("".equalsIgnoreCase(user.getPassword())){
            throw new InvalidParameterException("Password must not be empty");
        }
        if(user.getUsername().isEmpty()){
            throw new InvalidParameterException("Username must not be empty");
        }
        if(userRepository.existsByUsername(user.getUsername())){
            throw new InvalidParameterException("User with such username already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(Arrays.asList(roleRepository.findById(1L).get(),roleRepository.findById(2L).get())));

        return userRepository.save(user);

    }

    public User findById(long id) {
        return userRepository.findById(id).orElseThrow(()->new NotFoundException("No such user exists with given id"));
    }
}
