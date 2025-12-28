package com.worktemperature.meetnote_backend;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;



@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    public void testMemeber() {
        System.out.println("test");
        //given
        Member testMember = new Member();
        testMember.setName("이민규");
        testMember.setEmail("test@gmail.com");
        testMember.setPassword("1234");
        testMember.setUserCode("mingyu1.lee");


        //when
        Long id = memberRepository.save(testMember);
        Member findMember = memberRepository.find(id);
        System.out.println(findMember.toString());

        //then
        Assertions.assertThat(findMember.getId()).isEqualTo(testMember.getId());
        Assertions.assertThat(findMember.getName()).isEqualTo("이민규");
    }




}