package com.example.backend_unchk.modules.auth.dto;

import lombok.Data;

@Data
public class LoginRequestDTO {
    private String username;
    private String password;
}