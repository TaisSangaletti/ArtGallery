package com.example.artgallery.controllers;

import com.example.artgallery.DTO.LoginRequest;
import com.example.artgallery.DTO.LoginRequest;
import com.example.artgallery.DTO.LoginResponse;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")


public class LoginController {

    @PostMapping
    public ResponseEntity<LoginResponse> logar(@RequestBody LoginRequest loginrequest){
        if (loginrequest.getLogin ().equals ("string")
            && loginrequest.getSenha().equals("string")){

            LoginResponse loginsResponse = new LoginResponse();
            loginsResponse.setMensagem(

            return ResponseEntity.ok(loginsResponse);


        }

    return ResponseEntity.status(UN)


    }
}

