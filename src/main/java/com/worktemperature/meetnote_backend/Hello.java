package com.worktemperature.meetnote_backend;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Hello {
    private String data;

    public void hello() {
        System.out.println("=======");
    }
}
