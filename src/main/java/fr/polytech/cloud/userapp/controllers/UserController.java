package fr.polytech.cloud.userapp.controllers;

import fr.polytech.cloud.userapp.dtos.UserDTO;
import fr.polytech.cloud.userapp.entities.UserEntity;
import fr.polytech.cloud.userapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public ResponseEntity<List<UserDTO>> getUsers() {
        return new ResponseEntity<>(userService.mapToDTOs(userService.getUsers()), HttpStatus.OK);
    }

    @PutMapping("/user")
    public ResponseEntity putUsers(@RequestBody List<UserDTO> users) {
        userService.putUsers(userService.mapToEntities(users));
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @DeleteMapping("/user")
    public ResponseEntity deleteUsers() {
        userService.deleteUsers();
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
