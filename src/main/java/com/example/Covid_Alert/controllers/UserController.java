package com.example.Covid_Alert.controllers;

import com.example.Covid_Alert.models.User;
import com.example.Covid_Alert.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequestMapping("/api/v1/users")
@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> list() {
        return userRepository.findAll();
    }

    @GetMapping @RequestMapping("{id}")
    public User get(@PathVariable Long id) {
        if(userRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with ID "+id+" not found");
        }
        return userRepository.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody final User user) {
        return  userRepository.saveAndFlush(user);
    }

    @RequestMapping(value="{id}",method = RequestMethod.PUT)
    public Object update(@PathVariable Long id, @RequestBody User user) {
        // TODO: Ajouter ici une validation si tous
        // les champs ont ete passes
        // Sinon, retourner une erreur 400 (Bad Payload)
        if(user.getEmail()!=null || user.getFirst_name()!=null || user.getLast_name()!=null || user.getPassword()!=null || user.getPhone_number()!=null){
            User existingUser = userRepository.getById(id);
            BeanUtils.copyProperties(user,existingUser,"user_id");
            return userRepository.saveAndFlush(existingUser);
        }
        else{
            // Retourner erreur 400
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with ID "+id+" information not completed");
        }
    }

    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    @Secured("ROLE_ADMIN")
    public void delete(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}
