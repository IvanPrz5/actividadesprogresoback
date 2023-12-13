package com.example.actividadesProgreso.Security.Auth;

import lombok.Data;

@Data
public class AuthRegisterCredentials {
    private String nombre;
    private String apPaterno;
    private Long telefono;
    private String email;
    private String password;
}