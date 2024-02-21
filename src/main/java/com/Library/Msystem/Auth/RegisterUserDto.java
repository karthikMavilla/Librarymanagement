package com.Library.Msystem.Auth;

import lombok.Data;

@Data
public class RegisterUserDto {
    private String email;

    private String password;

    private String firstName;

}