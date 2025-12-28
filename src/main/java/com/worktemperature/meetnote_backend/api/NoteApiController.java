package com.worktemperature.meetnote_backend.api;

import com.worktemperature.meetnote_backend.domain.Note;
import com.worktemperature.meetnote_backend.service.NoteService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;


@RestController
@RequiredArgsConstructor
public class NoteApiController {

    private final NoteService noteService;

    @PostMapping("/api/notes")
    @ResponseBody
    public CreateNoteResponse createNote(@RequestBody @Validated CreateNoteRequest noteRequest) {
        Note note = new Note();
        note.setTitle(noteRequest.getTitle());
        note.setMeetDate(noteRequest.getNoteDate());

        Long id = noteService.createNote(note);

        return new CreateNoteResponse(id, note.getTitle());
    }



    @Data
    static class CreateNoteRequest {

        private String title;
        private LocalDate noteDate;
    }

    @Data
    static class CreateNoteResponse {

        private Long id;
        private String title;

        public CreateNoteResponse(Long id, String title) {
            this.id = id;
            this.title = title;
        }

    }

}
