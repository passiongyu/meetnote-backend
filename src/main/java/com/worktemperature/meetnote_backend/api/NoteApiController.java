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
import java.util.List;
import java.util.stream.Collectors;


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
     * 전체 조회
     * @return
     */
    @GetMapping("/api/notes")
    public Result getNotes() {
        List<NoteDto> noteList= noteService.findNotes().stream()
                .map(m -> new NoteDto(m.getId(), m.getTitle(), m.getFolderId(), m.getContent(), m.getMeetDate()))
                .collect(Collectors.toList());

        int noteListSize = noteList.size();
        return new Result(noteList, noteListSize);
    }

    /**
     * 하나 조회
     * @return
     */
    @GetMapping("/api/notes/{id}")
    public Result getNote(@PathVariable("id") Long id) {
        Note note = noteService.findOne(id);

        int noteListSize = 10;
        return new Result(note, noteListSize);
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
        private int size;
    }

    @Data
    @AllArgsConstructor
    static class NoteDto {
        private Long id;
        private String title;
        private int folderId;
        private String content;
        private LocalDateTime meetDate;
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

    /**
     * 하나 삭제
     * @return
     */
    @DeleteMapping("/api/notes/{id}")
    public DeleteResult deleteNote(@PathVariable("id") Long id) {
        noteService.deleteNote(id);

        return new DeleteResult("삭제완료", "SUCCESS");
    }

    @Data
    @AllArgsConstructor
    static class DeleteResult {
        private String message;
        private String successYn;

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
