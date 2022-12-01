package Jwt.LoginTest.Service;


import Jwt.LoginTest.DTO.TokenInfo;
import Jwt.LoginTest.Entity.Member;
import Jwt.LoginTest.Entity.Tag;
import Jwt.LoginTest.Jwt.JwtTokenProvider;
import Jwt.LoginTest.Repository.MemberRepository;
import Jwt.LoginTest.Repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    TagRepository tagRepository;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public TokenInfo login(String address, String password){
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(address,password);

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);

        return tokenInfo;
    }

    public Member join(Member member){
        Optional<Member> findMembers = memberRepository.findByAddress(member.getAddress());
        if(!findMembers.isEmpty()){
            System.out.println("이미 존재하는 회원입니다.");
        }
        else{
            memberRepository.save(member);
        }
        return member;
    }
}
