package com.worktemperature.meetnote_backend.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Entity
@Table(name = "note")
@Getter@Setter
public class Note {

    @Id @GeneratedValue
    private long id;
    private String title;
    private int folderId;
    private String registerUserCode;
    private LocalDate meetDate;
    private String soundFilePath;
    private String soundFileContent;

}
