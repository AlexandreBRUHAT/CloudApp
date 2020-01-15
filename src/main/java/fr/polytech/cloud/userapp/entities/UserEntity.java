package fr.polytech.cloud.userapp.entities;

import fr.polytech.cloud.userapp.dtos.Position;
import fr.polytech.cloud.userapp.dtos.UserDTO;
import org.hibernate.annotations.GenericGenerator;
import org.locationtech.jts.geom.Geometry;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "USER", schema = "bwnxxqpx94u6xym578mp", catalog = "")
public class UserEntity {
    @Column(name = "id", nullable = false, length = 36)
    private String id;
    @Column(name = "firstname", nullable = false, length = 35)
    private String firstname;
    @Column(name = "lastname", nullable = false, length = 35)
    private String lastname;
    @Column(name = "birthday", nullable = true)
    private Date birthday;
    @Column(name = "position", nullable = false, columnDefinition = "geometry")
    private Geometry position;

    @Id @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid",
    strategy = "uuid")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Basic
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Basic
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Basic
    public Geometry getPosition() {
        return position;
    }

    public void setPosition(Geometry position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(firstname, that.firstname) &&
                Objects.equals(lastname, that.lastname) &&
                Objects.equals(birthday, that.birthday) &&
                Objects.equals(position, that.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname, birthday, position);
    }

    public UserDTO mapToDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName(this.firstname);
        userDTO.setLastName(this.lastname);
        userDTO.setPosition(new Position(this.position.getCoordinate().getX(), this.position.getCoordinate().getY()));
        userDTO.setBirthDay(this.birthday);

        return userDTO;
    }
}
