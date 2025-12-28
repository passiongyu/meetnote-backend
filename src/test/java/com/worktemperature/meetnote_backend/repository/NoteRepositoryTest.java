package com.worktemperature.meetnote_backend.repository;

import com.worktemperature.meetnote_backend.domain.Note;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class NoteRepositoryTest {

    @Autowired
    NoteRepository noteRepository;

    @Test
    public void testNote() throws Exception {
        //given
        Note note = new Note();
        note.setTitle("testNote");
        note.setMeetDate(LocalDateTime.now());
        //when
        noteRepository.save(note);
        Note findNote = noteRepository.findOne(note.getId());

        //then
        Assertions.assertEquals("testNote", findNote.getTitle());

    }
}