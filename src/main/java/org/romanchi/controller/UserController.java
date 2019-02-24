package org.romanchi.controller;

import org.apache.commons.io.IOUtils;
import org.romanchi.exceptions.ImageLoadingException;
import org.romanchi.model.User;
import org.romanchi.model.dto.UserDTO;
import org.romanchi.model.dto.UserToRegisterDTO;
import org.romanchi.service.SecurityService;
import org.romanchi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final static Logger logger = Logger.getLogger(UserController.class.getName());

    @Autowired
    UserService userService;

    @Autowired
    SecurityService securityService;

    @Transactional
    @RequestMapping(value = {"","/"}, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO addUser(@RequestBody UserToRegisterDTO userToRegisterDTO){
        logger.info(userToRegisterDTO.toString());
        User user = userToRegisterDTO.toEntity();
        user.setActive(true);
        user.setAccountExprired(false);
        user.setAccountLocked(false);
        user.setAccountExprired(false);
        return new UserDTO(userService.register(user));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getUser(@PathVariable long id){
        return new UserDTO(userService.findById(id));
    }

    @PreAuthorize(value = "hasRole('ROLE_USER')")
    @RequestMapping(value = {"/current","/current/"}, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getCurrentUser(){
        return new UserDTO(securityService.getCurrentUser());
    }

    @RequestMapping(value = "/image/{imagePhotoFile}", method = RequestMethod.GET, produces = "image/jpg")
    @ResponseStatus(HttpStatus.OK)
    public byte[] getImage(@PathVariable String imagePhotoFile){
        String imagebase = "C:\\Users\\Roman\\IdeaProjects\\рфсл\\src\\main\\resources\\images\\";
        File imageFile = new File(imagebase + imagePhotoFile);
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(imageFile));
            return IOUtils.toByteArray(bufferedInputStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ImageLoadingException("Something went wrong");
        }
    }
}
