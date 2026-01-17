package com.worktemperature.meetnote_backend.repository;

import com.worktemperature.meetnote_backend.domain.NoteFile;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class FileRepository {

    @PersistenceContext
    EntityManager em;

    public Long save(NoteFile file) {
        em.persist(file);

        return file.getId();
    }

}
