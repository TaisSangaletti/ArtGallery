package com.example.artgallery.controllers;

import com.example.artgallery.DTO.LoginRequest;
import com.example.artgallery.DTO.LoginResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    @PostMapping
    public ResponseEntity<LoginResponse> realizarLogin(
            @RequestBody LoginRequest request) {

        if ("admin".equals(request.getLogin())
                && "123".equals(request.getSenha())) {

            return ResponseEntity.ok(
                    new LoginResponse(
                            "Login realizado com sucesso!"
                    )
            );
        }

        return ResponseEntity.badRequest().body(
                new LoginResponse(
                        "Usuário ou senha inválidos!"
                )
        );
    }
}