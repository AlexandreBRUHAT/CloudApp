package fr.polytech.cloud.userapp.repositories;

import fr.polytech.cloud.userapp.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    @Query("UPDATE UserEntity u SET u.birthday=:#{#user.birthday} ," +
            " u.firstname=:#{#user.firstname}," +
            " u.lastname=:#{#user.lastname}," +
            " u.position=:#{#user.position}" +
            " WHERE u.id=:#{#user.id}")
    public void update(@Param("user") UserEntity user);
}
