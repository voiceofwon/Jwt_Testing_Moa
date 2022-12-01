package Jwt.LoginTest.Repository;

import Jwt.LoginTest.Entity.MemberTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembertagRepository extends JpaRepository<MemberTag,Long> {
}
