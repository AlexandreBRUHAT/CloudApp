package fr.polytech.cloud.userapp.services;

import fr.polytech.cloud.userapp.dtos.UserDTO;
import fr.polytech.cloud.userapp.entities.UserEntity;
import fr.polytech.cloud.userapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> getUsers() {
        return userRepository.findAll();
    }

    public void putUsers(List<UserEntity> users) {

        userRepository.saveAll(users);
    }

    public void deleteUsers() {
        userRepository.deleteAll();
    }

    public List<UserEntity> mapToEntities(List<UserDTO> dtos) {
        return dtos.stream().map(userDTO -> userDTO.mapToEntity()).collect(Collectors.toList());
    }

    public List<UserDTO> mapToDTOs(List<UserEntity> entities) {
        return entities.stream().map(userEntity -> userEntity.mapToDTO()).collect(Collectors.toList());
    }
}
