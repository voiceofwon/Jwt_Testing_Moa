package Jwt.LoginTest.Controller;

import Jwt.LoginTest.DTO.MemberLoginRequestDto;
import Jwt.LoginTest.DTO.TokenInfo;
import Jwt.LoginTest.Entity.Member;
import Jwt.LoginTest.Service.CustomUserDetailsService;
import Jwt.LoginTest.Service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/members")
public class MemberController {
    @Autowired
    MemberService memberService;

    private final CustomUserDetailsService customUserDetailsService;

    @PostMapping("/login")
    public TokenInfo login(@RequestBody MemberLoginRequestDto memberLoginRequestDto){
        String address = memberLoginRequestDto.getAddress();
        String password = memberLoginRequestDto.getPassword();
        TokenInfo tokenInfo = memberService.login(address,password);
        return tokenInfo;
    }

    @PostMapping("/test")
    public String test() {
        return "success";
    }

    @PostMapping("/mytag")
    public String myTag(@AuthenticationPrincipal UserDetails userDetails){
        String address = userDetails.getUsername();
        return address;
    }
}
