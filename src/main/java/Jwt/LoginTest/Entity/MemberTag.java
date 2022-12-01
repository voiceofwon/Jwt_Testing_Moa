package Jwt.LoginTest.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class MemberTag {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name ="member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name ="tag_id")
    private Tag tag;

    private int tagAmount;
}
