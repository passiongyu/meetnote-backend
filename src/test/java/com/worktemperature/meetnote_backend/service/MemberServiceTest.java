package com.worktemperature.meetnote_backend.service;


import com.worktemperature.meetnote_backend.domain.Member;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Test
    public void memberJoinTest() throws Exception {
        // given
        Member member = new Member();
        member.setName("홍길동");
        member.setEmail("hong@gmail.com");
        member.setUserCode("gildong.hon");
        member.setPassword("4567");

        // when
        Long savedId = memberService.join(member);
        Member findMember = memberService.findMember(savedId);

        // then
        Assertions.assertThat(findMember.getId()).isEqualTo(savedId);
        Assertions.assertThat(findMember.getName()).isEqualTo("홍길동");
        Assertions.assertThat(findMember.getEmail()).isEqualTo("hong@gmail.com");
        Assertions.assertThat(findMember.getUserCode()).isEqualTo("gildong.hon");

    }

    @Test
    public void 중복() throws Exception {
        //given
        Member testMember = new Member();
        testMember.setName("홍길동");
        testMember.setEmail("hong@gmail.com");
        testMember.setUserCode("gildong.hon");
        testMember.setPassword("4567");

        Member testMember2 = new Member();
        testMember2.setName("홍길동");
        testMember2.setEmail("hong@gmail.com");
        testMember2.setUserCode("gildong.hon");
        testMember2.setPassword("4567");

        // when
        memberService.join(testMember);
        try{
            memberService.join(testMember2);
        }catch (IllegalStateException e) {
            return;
        }

        // then

        Assertions.fail();
    }


}