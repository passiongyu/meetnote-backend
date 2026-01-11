package com.worktemperature.meetnote_backend.api;

import com.worktemperature.meetnote_backend.domain.Note;
import com.worktemperature.meetnote_backend.service.NoteService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


@RestController
@RequiredArgsConstructor
public class NoteApiController {

    private final NoteService noteService;

    /**
     * 등록
      * @param request
     * @return
     */
    @PostMapping("/api/notes")
    public CreateNoteResponse createNote(@RequestBody @Validated CreateNoteRequest request) {
        Note note = new Note();

        note.setTitle(request.getTitle());
        note.setFolderId(request.getFolderId());
        note.setContent(request.getContent());
        note.setMeetDate(request.getMeetDate());

        Long id = noteService.createNote(note);

        return new CreateNoteResponse(id);
    }

    /**
     * 수정
     * @param id
     * @param request
     * @return
     */
    @PutMapping("/api/notes/{id}")
    public UpdateNoteResponse updateNote(@PathVariable("id") Long id, @RequestBody @Validated UpdateNoteRequest request) {

        Note note = new Note();

        note.setTitle(request.getTitle());
        note.setFolderId(request.getFolderId());
        note.setContent(request.getContent());
        note.setMeetDate(request.getMeetDate());

        noteService.updateNote(id, note);
        Note resultNote = noteService.findOne(id);
        return new UpdateNoteResponse(id, resultNote.getTitle(), resultNote.getFolderId(), resultNote.getContent(), resultNote.getMeetDate());
    }

    @Data
    @AllArgsConstructor
    static class UpdateNoteResponse {
        private Long id;
        private String title;
        private int folderId;
        private String content;
        // meetDate: "2026-01-11T14:30:00" 문자열로 보내면 됨
        private LocalDateTime meetDate;
    }

    @Data
    static class UpdateNoteRequest {
        private String title;
        private int folderId;
        private String content;
        // meetDate: "2026-01-11T14:30:00" 문자열로 보내면 됨
        private LocalDateTime meetDate;

    }


    @Data
    static class CreateNoteRequest {

        private String title;
        private int folderId;
        private String content;
        // meetDate: "2026-01-11T14:30:00" 문자열로 보내면 됨
        private LocalDateTime meetDate;

    }

    @Data
    static class CreateNoteResponse {

        private Long id;

        public CreateNoteResponse(Long id) {
            this.id = id;
        }
    }

}
