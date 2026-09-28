package com.example.artgallery.controllers;

import com.example.artgallery.DTO.LoginRequest;
import com.example.artgallery.DTO.LoginResponse;
import com.example.artgallery.entities.Usuario;
import com.example.artgallery.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<LoginResponse> realizarLogin(
            @RequestBody LoginRequest loginRequest) {

        if ( usuarioRepository.existsUsuarioByCpfAndSenha(loginRequest.getLogin(),
                loginRequest.getSenha()) ){

            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setMensagem("Bem vindo! Ao sistema de alunos!");

            return ResponseEntity.ok(loginResponse);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }


}