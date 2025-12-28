package com.worktemperature.meetnote_backend;

import com.worktemperature.meetnote_backend.domain.Member;
import com.worktemperature.meetnote_backend.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;



@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    public void testMemeber() {
        System.out.println("test");
        //given
        Member testMember = new Member();
        testMember.setName("이민규");
        testMember.setEmail("test@gmail.com");
        testMember.setPassword("1234");
        testMember.setUserCode("mingyu1.lee");


        //when
        memberRepository.save(testMember);
        Member findMember = memberRepository.findOne(testMember.getId());
        System.out.println(findMember.toString());

        //then
        Assertions.assertThat(findMember.getId()).isEqualTo(testMember.getId());
        Assertions.assertThat(findMember.getName()).isEqualTo("이민규");
    }




}