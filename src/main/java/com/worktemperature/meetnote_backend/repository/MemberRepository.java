package com.worktemperature.meetnote_backend.repository;

import com.worktemperature.meetnote_backend.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByUserCode(String userCode) {
        return em.createQuery("select m from Member m where m.userCode = :userCode", Member.class)
                .setParameter("userCode", userCode)
                .getResultList();
    }
}
