package Jwt.LoginTest.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Tag")
@Getter
@Setter
public class Tag {

    @Id
    @GeneratedValue
    @Column(name = "tag_id")
    private Long id;


    private String tname;
}
