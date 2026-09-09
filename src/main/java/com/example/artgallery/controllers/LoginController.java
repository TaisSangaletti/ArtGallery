package com.example.artgallery.controllers;

import com.example.artgallery.DTO.LoginRequest;
import com.example.artgallery.DTO.LoginResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    @PostMapping
    public ResponseEntity<LoginResponse> logar(
            @RequestBody LoginRequest loginRequest) {

        if (loginRequest.getLogin().equals("string")
                && loginRequest.getSenha().equals("string")) {

            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setMensagem("Bem vindo! Ao sistema de alunos!");

            return ResponseEntity.ok(loginResponse);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}