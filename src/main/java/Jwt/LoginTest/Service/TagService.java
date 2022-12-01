package Jwt.LoginTest.Service;

import Jwt.LoginTest.Entity.Member;
import Jwt.LoginTest.Entity.MemberTag;
import Jwt.LoginTest.Entity.Tag;
import Jwt.LoginTest.Repository.MemberRepository;
import Jwt.LoginTest.Repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class TagService {
    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private MemberRepository memberRepository;

    public Tag insertTag(Tag tag){
        Optional<Tag> findTags = tagRepository.findByTname(tag.getTname());
        if(!findTags.isEmpty()){
            System.out.println("이미 존재하는 태그입니다.");
            return tag;
        }
        tagRepository.save(tag);
        return tag;
    }

    public List<Tag> findTags(String address){
        //멤버 NULL예외처리 구현 필요
        Member member = memberRepository.findByAddress(address).get();
        List<MemberTag> memberTag = member.getMembertags();
        List<Tag> tags = new ArrayList<>();
        for(MemberTag memTag : memberTag){
            tags.add(memTag.getTag());
        }
        return tags;
    }
}
