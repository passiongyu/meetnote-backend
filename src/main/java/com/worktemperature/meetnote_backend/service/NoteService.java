package com.worktemperature.meetnote_backend.service;

import com.worktemperature.meetnote_backend.domain.Member;
import com.worktemperature.meetnote_backend.domain.Note;
import com.worktemperature.meetnote_backend.repository.MemberRepository;
import com.worktemperature.meetnote_backend.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoteService {

    private final NoteRepository noteRepository;

        @Transactional
        public Long createNote(Note note) {
            noteRepository.save(note);
            return note.getId();
        }
}
