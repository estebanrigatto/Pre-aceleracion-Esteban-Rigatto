package com.alkemy.disney.exception;

public enum ExceptionEnum {
    FILMNOTFOUND("Film not found"),
    CHARACTERNOTFOUND("Character not found"),
    GENRENOTFOUND("Genre not found");

    private String message;

    ExceptionEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
