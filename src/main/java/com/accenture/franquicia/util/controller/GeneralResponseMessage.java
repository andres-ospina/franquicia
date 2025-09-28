package com.accenture.franquicia.util.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeneralResponseMessage {
    private boolean success;
    private Integer httpCode;
    private String message;

    public GeneralResponseMessage(boolean success, Integer httpCode, String message) {
        this.success = success;
        this.httpCode = httpCode;
        this.message = message;
    }
}
