package com.worktemperature.meetnote_backend.repository;

import com.worktemperature.meetnote_backend.domain.NoteFile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class FileRepositoryTest {

    @Autowired
    FileRepository fileRepository;

    @Test
    public void saveFileTest() {
        //given
        NoteFile file = new NoteFile();
        file.setFilePath("/file");
        file.setNoteId(10);
        file.setFileName("testFile");
        file.setSavedName("testSavedFile");
        file.setCreateDate(LocalDate.now());
        file.setUpdateDate(LocalDate.now());

        //when
        Long id = fileRepository.save(file);
        //then
        System.out.println(id);
    }

}