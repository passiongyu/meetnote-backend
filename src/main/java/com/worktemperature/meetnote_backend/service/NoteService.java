package com.worktemperature.meetnote_backend.service;

import com.worktemperature.meetnote_backend.domain.Member;
import com.worktemperature.meetnote_backend.domain.Note;
import com.worktemperature.meetnote_backend.repository.MemberRepository;
import com.worktemperature.meetnote_backend.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

        @Transactional
        public Note findOne(Long id) {
            Note note = noteRepository.findOne(id);
            return note;
        }

        public List<Note> findNotes() {
            return noteRepository.findAll();
        }

        @Transactional
        public void updateNote(Long id, Note updateNote) {
            Note note = noteRepository.findOne(id);

            note.setTitle(updateNote.getTitle());
            note.setMeetDate(updateNote.getMeetDate());
            note.setFolderId(updateNote.getFolderId());
            note.setContent(updateNote.getContent());
        }

        @Transactional
        public void deleteNote(Long id) {
            noteRepository.delete(id);
        }
}
