package fr.polytech.cloud.userapp.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import fr.polytech.cloud.userapp.entities.UserEntity;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import fr.polytech.cloud.userapp.dtos.Position;

import java.sql.Date;

public class UserDTO {

    private String id;
    private String firstName;
    private String lastName;
    private Position position;
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date birthDay;

    public UserDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public UserEntity mapToEntity() {
        GeometryFactory geometryFactory = new GeometryFactory();

        UserEntity userEntity = new UserEntity();
        userEntity.setFirstname(this.firstName);
        userEntity.setLastname(this.lastName);
        userEntity.setBirthday(this.birthDay);

        if (this.position != null)
            userEntity.setPosition(geometryFactory.createPoint(new Coordinate(this.position.getLat(), this.position.getLon())));

        return userEntity;
    }
}
