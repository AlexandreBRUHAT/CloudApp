package fr.polytech.cloud.userapp.services;

import fr.polytech.cloud.userapp.dtos.UserDTO;
import fr.polytech.cloud.userapp.entities.UserEntity;
import fr.polytech.cloud.userapp.repositories.UserRepository;
import main.java.fr.polytech.cloud.userapp.dtos.UserFullDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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

    public List<UserEntity> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable).getContent();
    }

    public UserEntity getUser(String id) {
        return userRepository.findById(id).orElse(new UserEntity());
    }

    public List<UserEntity> putUsers(List<UserEntity> users) {

        return userRepository.saveAll(users);
    }

    public UserEntity putUser(String id, UserEntity entity) {
        entity.setId(id);

        return userRepository.save(entity);
    }

    public UserEntity postUser(UserEntity user) {
        return userRepository.save(user);
    }

    public void deleteUsers() {
        userRepository.deleteAll();
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    public List<UserEntity> mapToEntities(List<UserDTO> dtos) {
        return dtos.stream().map(userDTO -> userDTO.mapToEntity()).collect(Collectors.toList());
    }

    public List<UserDTO> mapToDTOs(List<UserEntity> entities) {
        return entities.stream().map(userEntity -> userEntity.mapToDTO()).collect(Collectors.toList());
    }

    public List<UserFullDTO> mapToFullDTOs(List<UserEntity> entities) {
        return entities.stream().map(userEntity -> userEntity.mapToFullDTO()).collect(Collectors.toList());
    }
}
