package com.accenture.franquicia.util.controller;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GeneralResponse<E> {
    private boolean success;
    private Integer httpCode;
    private String message;
    private E data = null;

    public GeneralResponse(boolean success, Integer httpCode, String message, E data) {
        this.success = success;
        this.httpCode = httpCode;
        this.message = message;
        this.data = data;
    }
}
