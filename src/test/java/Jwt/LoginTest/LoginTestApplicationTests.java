package Jwt.LoginTest;

import Jwt.LoginTest.Entity.Member;
import Jwt.LoginTest.Repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class LoginTestApplicationTests {
	@Autowired
	private MemberRepository memberRepository;

	@Test
	@Transactional
	@Rollback(false)
	void testMember() {


		Member member = new Member();
		member.setName("test1");
		member.setAddress("voiceofwon@naver.com");
		member.setS_id(202126978);
		member.setPassword("1102");


		memberRepository.save(member);
		assertEquals(member.getName(),"test1");
	}

}
