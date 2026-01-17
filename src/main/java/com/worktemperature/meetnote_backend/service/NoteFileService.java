package com.worktemperature.meetnote_backend.service;

import com.worktemperature.meetnote_backend.domain.NoteFile;
import com.worktemperature.meetnote_backend.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoteFileService {


    final private FileRepository fileRepository;

    @Transactional
    public Long createNoteFile(NoteFile file) {
        return fileRepository.save(file);
    }
}
