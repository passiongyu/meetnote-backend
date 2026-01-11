package com.worktemperature.meetnote_backend.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(name = "NOTE")
@Getter@Setter
public class Note {

    @Id @GeneratedValue
    private long id;

    @Column(name="TITLE")
    private String title;

    @Column(name="FOLDER_ID")
    private int folderId;

    @Column(name="CONTENT")
    private String content;

    @Column(name="MEET_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime meetDate;



}
