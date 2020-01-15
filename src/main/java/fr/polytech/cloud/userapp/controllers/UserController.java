package fr.polytech.cloud.userapp.controllers;

import fr.polytech.cloud.userapp.dtos.UserDTO;
import fr.polytech.cloud.userapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<List<UserDTO>> getUsers(Pageable pageable) {
        return new ResponseEntity<>(userService.mapToDTOs(userService.getUsers(pageable)), HttpStatus.OK);
    }

    @PutMapping("/user")
    public ResponseEntity<List<UserDTO>> putUsers(@RequestBody List<UserDTO> users) {
        userService.putUsers(userService.mapToEntities(users));
        return new ResponseEntity(userService.mapToDTOs(userService.putUsers(userService.mapToEntities(users))), HttpStatus.CREATED);
    }

    @DeleteMapping("/user")
    public ResponseEntity deleteUsers() {
        userService.deleteUsers();
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<UserDTO> getUser(String id) {
        return new ResponseEntity<>(userService.getUser(id).mapToDTO(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<UserDTO> putUser(@RequestBody UserDTO userDTO) {
        return new ResponseEntity(userService.putUser(userDTO.mapToEntity()).mapToDTO(), HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<UserDTO> postUser(@RequestBody UserDTO userDTO) {
        return new ResponseEntity(userService.postUser(userDTO.mapToEntity()).mapToDTO(), HttpStatus.CREATED);
    }
}
