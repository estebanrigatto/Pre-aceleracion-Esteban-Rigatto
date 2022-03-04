package com.alkemy.disney.auth.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Getter
@Setter
public class AuthenticationRequest {

    @Email(message = "Username must be an email")
    private String username;
    @Size(min = 8)
    private String password;

}
