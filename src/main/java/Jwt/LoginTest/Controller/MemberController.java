package Jwt.LoginTest.Controller;

import Jwt.LoginTest.DTO.MemberLoginRequestDto;
import Jwt.LoginTest.DTO.TokenInfo;
import Jwt.LoginTest.Entity.Member;
import Jwt.LoginTest.Entity.Tag;
import Jwt.LoginTest.Service.CustomUserDetailsService;
import Jwt.LoginTest.Service.MemberService;
import Jwt.LoginTest.Service.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/members")
public class MemberController {
    @Autowired
    MemberService memberService;

    @Autowired
    TagService tagService;

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
    public List<Tag> myTag(@AuthenticationPrincipal UserDetails userDetails){
        String address = userDetails.getUsername();
        List<Tag> userTags = tagService.findTags(address);

        return userTags;
    }

}
