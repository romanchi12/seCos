package org.romanchi.service;

import org.romanchi.exceptions.NotFoundException;
import org.romanchi.model.AlergenItem;
import org.romanchi.model.User;
import org.romanchi.repository.AlergenRepository;
import org.romanchi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.Set;

@Service
public class AlergenService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AlergenRepository alergenRepository;

    @Autowired
    SecurityService securityService;

    public AlergenItem findById(long id){
        return alergenRepository.findById(id).orElseThrow(()->new NotFoundException("No alergen item by id given"));
    }

    public AlergenItem addAlergenItemToUser(AlergenItem alergenItem){
        User user = securityService.getCurrentUser();
        if(alergenItem.getId() <= 0) {
            throw new InvalidParameterException("Id must not be empty");
        }
        AlergenItem alergenItemDatabase = findById(alergenItem.getId());
        user.getAlergenItems().add(alergenItemDatabase);
        userRepository.save(user);
        return alergenItemDatabase;
    }


    public void removeAlergenItemForUser(long id) {
        User user = securityService.getCurrentUser();
        AlergenItem alergenItemDatabase = findById(id);
        user.getAlergenItems().remove(alergenItemDatabase);
        userRepository.save(user);
    }

    public Set<AlergenItem> findAll(PageRequest id) {
        return new HashSet<>(alergenRepository.findAll(id).getContent());
    }

    public AlergenItem addAlergenItem(AlergenItem alergenItem) {
        if(alergenItem.getId() > 0){
            throw new InvalidParameterException("Id must be empty. To update use PATCH");
        }
        return alergenRepository.save(alergenItem);
    }

    public void deleteById(long id) {
        alergenRepository.delete(findById(id));
    }

}
