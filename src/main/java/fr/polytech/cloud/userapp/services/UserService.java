package fr.polytech.cloud.userapp.services;

import fr.polytech.cloud.userapp.dtos.UserDTO;
import fr.polytech.cloud.userapp.entities.UserEntity;
import fr.polytech.cloud.userapp.repositories.UserRepository;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> getUsers(Pageable pageable) {
        if (!pageable.isPaged())
            pageable = PageRequest.of(pageable.getPageNumber(), 100);
        return userRepository.findAll(pageable).getContent();
    }

    // TODO : NOT OPTIONAL
    public UserEntity getUser(String id) throws NoSuchElementException {
        return userRepository.findById(id).get();
    }

    public List<UserEntity> putUsers(List<UserEntity> users) {

        return userRepository.saveAll(users);
    }

    public UserEntity putUser(String id, UserEntity entity) throws EntityNotFoundException {

        UserEntity user = userRepository.getOne(id);

        if (entity.getBirthday() != null) {
            user.setBirthday(entity.getBirthday());
        }
        if (entity.getFirstname() != null) {
            user.setFirstname(entity.getFirstname());
        }
        if (entity.getLastname() != null) {
            user.setLastname(entity.getLastname());
        }
        if (entity.getPosition() != null) {
            user.setPosition(entity.getPosition());
        }

        return userRepository.save(user);
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

    public List<UserEntity> getByAgeGreaterThan(int age, Pageable pageable) {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR,-age);

        return userRepository.findByBirthdayLessThan(pageable, calendar.getTime());
    }

    public List<UserEntity> getByAgeEquals(int age, Pageable pageable) {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR,-age - 1);
        calendar.add(Calendar.DAY_OF_YEAR, 1);

        Date start = calendar.getTime();

        calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR,-age + 1);
        calendar.add(Calendar.DAY_OF_YEAR, -1);

        Date end = calendar.getTime();

        return userRepository.findByBirthdayGreaterThanAndBirthdayLessThan(pageable, start, end);
    }

    public List<UserEntity> getByName(String name, Pageable pageable) {
        return userRepository.findByLastnameEquals(pageable, name);
    }

    public List<UserEntity> getPositionNear(double lat, double lon, Pageable page) {

        GeometryFactory geometryFactory = new GeometryFactory();

        return userRepository.findByPosition(lon, lat, page.getPageSize());
    }

    public List<UserEntity> mapToEntities(List<UserDTO> dtos) {
        return dtos.stream().map(userDTO -> userDTO.mapToEntity()).collect(Collectors.toList());
    }

    public List<UserDTO> mapToDTOs(List<UserEntity> entities) {
        return entities.stream().map(userEntity -> userEntity.mapToDTO()).collect(Collectors.toList());
    }
}
