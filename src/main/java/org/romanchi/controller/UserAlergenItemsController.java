package org.romanchi.controller;

import org.romanchi.model.AlergenItem;
import org.romanchi.model.dto.AlergenItemDTO;
import org.romanchi.service.AlergenService;
import org.romanchi.service.SecurityService;
import org.romanchi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/user/alergenitems")
public class UserAlergenItemsController {

    private final static Logger logger = Logger.getLogger(UserAlergenItemsController.class.getName());

    @Autowired
    UserService userService;

    @Autowired
    AlergenService alergenService;


    @PreAuthorize(value = "hasRole('ROLE_USER')")
    @RequestMapping(value = {"","/"}, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Set<AlergenItemDTO> getAlergenItemsForUser(){
        return userService.findAllForUser()
                .stream()
                .map(AlergenItemDTO::new)
                .collect(Collectors.toSet());
    }

    @Transactional
    @PreAuthorize(value = "hasRole('ROLE_USER')")
    @RequestMapping(value = {"","/"}, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public AlergenItemDTO addAlergenItem(@RequestBody AlergenItemDTO alergenItemDTO){
        AlergenItem alergenItem = alergenItemDTO.toEntity();
        logger.info(alergenItemDTO.toString());
        return new AlergenItemDTO(alergenService.addAlergenItemToUser(alergenItem));
    }

    @Transactional
    @PreAuthorize(value = "hasRole('ROLE_USER')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeAlergenItem(@PathVariable long id){
        alergenService.removeAlergenItemForUser(id);
    }

    @PreAuthorize(value = "hasRole('ROLE_USER')")
    @RequestMapping(value = "/{barcode}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Set<AlergenItemDTO> getAlergensForBarCode(@PathVariable String barcode){
        Set<AlergenItem> dangerAlergens = userService.getAlergensByBarcodeForCurrentUser(barcode);
        return dangerAlergens.stream().map(AlergenItemDTO::new).collect(Collectors.toSet());
    }


}
