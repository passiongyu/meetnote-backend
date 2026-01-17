package com.worktemperature.meetnote_backend.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "NOTE_FILE")
public class NoteFile {

    @Id
    @GeneratedValue
    private long id;

    @Column(name="FOLDER_ID")
    private int noteId;

    @Column(name="FILE_NAME")
    private String fileName;

    @Column(name="SAVED_NAME")
    private String savedName;

    @Column(name="PATH")
    private String filePath;

    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.DATE)
    private LocalDate createDate;

    @Column(name = "UPDATE_DATE")
    @Temporal(TemporalType.DATE)
    private LocalDate updateDate;

}
