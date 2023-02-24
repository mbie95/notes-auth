package com.marcinbielenit.auth.controller;

import com.marcinbielenit.auth.model.User;
import com.marcinbielenit.auth.logic.UserService;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author marcin
 */
@RestController
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }
    
    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome in AUTH_SERVICE! ";
    }
    
    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.OK)
    public void registration(@RequestBody @Valid User newUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.getFieldError().getDefaultMessage());
        service.addNewUser(newUser);
    }
}
