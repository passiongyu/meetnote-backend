package com.worktemperature.meetnote_backend.service;

import com.worktemperature.meetnote_backend.domain.Member;
import com.worktemperature.meetnote_backend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long join(Member member) {
        validateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateMember(Member member) {
        String userCode = member.getUserCode();
        List<Member> findMembers = memberRepository.findByUserCode(userCode);
        if(!findMembers.isEmpty()) {
            throw new IllegalStateException("중복 usercode");
        }
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findMember(Long memberId) {
        return memberRepository.findOne(memberId);
    }




}
