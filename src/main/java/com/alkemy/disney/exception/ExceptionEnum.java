package com.alkemy.disney.exception;

public enum ExceptionEnum {
    FILMNOTFOUND("Film not found"),
    CHARACTERNOTFOUND("Character not found"),
    GENRENOTFOUND("Genre not found"),
    USERALREADYEXIST("User already exist in database"),
    USERNAMENOTFOUND("User not found"),
    INVALIDUSERNAMEORPASSWORD("Invalid Username or Password");

    private String message;

    ExceptionEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
