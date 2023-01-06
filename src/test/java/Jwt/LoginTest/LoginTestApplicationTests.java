package Jwt.LoginTest;


import Jwt.LoginTest.DTO.MemberLoginRequestDto;
import Jwt.LoginTest.DTO.TokenInfo;
import Jwt.LoginTest.Entity.Member;
import Jwt.LoginTest.Jwt.JwtAuthenticationFilter;
import Jwt.LoginTest.Jwt.JwtTokenProvider;
import Jwt.LoginTest.Repository.MemberRepository;
import Jwt.LoginTest.Service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class LoginTestApplicationTests {
	@Autowired
	private MemberService memberService;
	@Autowired
	MockMvc mvc;
	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	JwtTokenProvider jwtTokenProvider;



	private final String address = "voiceofwon@naver.com";
	private final String password = "1102";

	@Test
	void TokenGeneration() throws Exception {
		//given
		MemberLoginRequestDto testDTO = new MemberLoginRequestDto();
		testDTO.setAddress(address);
		testDTO.setPassword(password);

		//when
		TokenInfo tokenInfo = memberService.login(testDTO.getAddress(), testDTO.getPassword());

		String granttype = tokenInfo.getGrantType();
		String accessToken = tokenInfo.getAccessToken();

		Boolean validateToken = jwtTokenProvider.validateToken(accessToken);


		//then
		Assertions.assertEquals(validateToken,Boolean.TRUE);
		Assertions.assertEquals(granttype,"Bearer");

	}

	@Test
	public void login_Controller_Test() throws Exception {
		//given
		MemberLoginRequestDto testDTO = new MemberLoginRequestDto();
		testDTO.setAddress(address);
		testDTO.setPassword(password);
		//when
		String content = objectMapper.writeValueAsString(testDTO);
		//then
		mvc.perform(post("/members/login")
				.content(content)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(print());

	}

	@Test
	public void access_Test() throws Exception {
		//given
		MemberLoginRequestDto testDTO = new MemberLoginRequestDto();
		testDTO.setAddress(address);
		testDTO.setPassword(password);

		TokenInfo tokenInfo = memberService.login(testDTO.getAddress(), testDTO.getPassword());

		String granttype = tokenInfo.getGrantType();
		String accessToken = tokenInfo.getAccessToken();

		mvc.perform(post("/members/test")
				.header(HttpHeaders.AUTHORIZATION,granttype + " " + accessToken))
				.andExpect(status().isOk())
				.andDo(print());



	}

}