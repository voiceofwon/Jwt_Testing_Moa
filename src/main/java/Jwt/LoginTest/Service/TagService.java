package Jwt.LoginTest.Service;

import Jwt.LoginTest.Entity.Tag;
import Jwt.LoginTest.Repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class TagService {
    @Autowired
    private TagRepository tagRepository;

    public Tag insertTag(Tag tag){
        Optional<Tag> findTags = tagRepository.findByTname(tag.getTname());
        if(!findTags.isEmpty()){
            System.out.println("이미 존재하는 태그입니다.");
            return tag;
        }
        tagRepository.save(tag);
        return tag;
    }
}
