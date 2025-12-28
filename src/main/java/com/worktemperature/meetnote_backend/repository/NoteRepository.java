package com.worktemperature.meetnote_backend.repository;

import com.worktemperature.meetnote_backend.domain.Note;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class NoteRepository {

    @PersistenceContext
    EntityManager em;

    public void save(Note note) {
        em.persist(note);
    }

    public Note findOne(Long id) {
        return em.find(Note.class, id);
    }
}
