package com.worktemperature.meetnote_backend.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.worktemperature.meetnote_backend.domain.Note;
import com.worktemperature.meetnote_backend.service.NoteService;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
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
     * 리스트 조회
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

    /**
     * 수정
     * @param id
     * @param request
     * @return
     */
    @PatchMapping("/api/notes/{id}")
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

    /**
     * 등록
     * @param request
     * @return
     */
    @PostMapping("/api/notesFromJson")
    @ResponseBody
    public CreateNoteResponse createNoteFromJson(HttpEntity<CreateNoteRequest> httpEntity) {
        Note note = new Note();

        CreateNoteRequest createNoteRequest = httpEntity.getBody();

        note.setTitle(createNoteRequest.getTitle());
        note.setFolderId(createNoteRequest.getFolderId());
        note.setContent(createNoteRequest.getContent());
        note.setMeetDate(createNoteRequest.getMeetDate());

        Long id = noteService.createNote(note);

        return new CreateNoteResponse(id);
    }

    /**
     * 등록
     * @param request
     * @return
     */
    @PostMapping("/api/notesFromObjectMapper")
    @ResponseBody
    public CreateNoteResponse createNoteFromObjectMapper(HttpServletRequest request) throws IOException {
        Note note = new Note();

        ServletInputStream in = request.getInputStream();
        String messageBody = StreamUtils.copyToString(in, StandardCharsets.UTF_8);

        ObjectMapper objectMapper = new ObjectMapper();
        CreateNoteRequest noteDto = objectMapper.readValue(messageBody, CreateNoteRequest.class);

        note.setTitle(noteDto.getTitle());
        note.setFolderId(noteDto.getFolderId());
        note.setContent(noteDto.getContent());
        note.setMeetDate(noteDto.getMeetDate());

        Long id = noteService.createNote(note);

        return new CreateNoteResponse(id);
    }

    /**
     * 등록 form-파라미터 방식 또는 쿼리 파라미터 방식
     * @param 여러가지
     * @return
     */
    @PostMapping("/api/notesFromForm")
    public CreateNoteResponse createNoteFromFormData(@ModelAttribute CreateNoteRequest noteDto, @RequestParam String title, @RequestParam Map<String, Object> paramMap, HttpServletRequest request) {
        Note note = new Note();
        String content = request.getParameter("content");
        note.setTitle(title);
        note.setContent(content);
        System.out.println(paramMap.toString());

        Long id = noteService.createNote(note);

        return new CreateNoteResponse(id);
    }

    /**
     * 등록
     * @param body text
     * @return
     */
    @PostMapping("/api/notesFromText")
    public ResponseEntity createNoteFromText(HttpEntity<String> httpEntity) {
        Note note = new Note();

        String text = httpEntity.getBody();
        note.setTitle(text);

        Long id = noteService.createNote(note);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Map.of("id", id));
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
