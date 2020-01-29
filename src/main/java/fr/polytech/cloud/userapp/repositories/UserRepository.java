package fr.polytech.cloud.userapp.repositories;

import fr.polytech.cloud.userapp.entities.UserEntity;
import org.locationtech.jts.geom.Geometry;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    @Query("UPDATE UserEntity u SET u.birthday=:#{#user.birthday} ," +
            " u.firstname=:#{#user.firstname}," +
            " u.lastname=:#{#user.lastname}," +
            " u.position=:#{#user.position}" +
            " WHERE u.id=:#{#user.id}")
    public void update(@Param("user") UserEntity user);

    public List<UserEntity> findByBirthdayLessThan(Pageable page, Date date);

    public List<UserEntity> findByBirthdayGreaterThanAndBirthdayLessThan(Pageable page, Date start, Date end);

    public List<UserEntity> findByLastnameEquals(Pageable page, String equals);

//    public List<UserEntity> findByPositionNear(Geometry point);
}
