package com.example.artgallery.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Obra")

public class ObraDeArteController {

    @GetMapping
    public String ConsultaObra(){
        return "Hello World";
    }

    @GetMapping("/{id}")
    public String ConsultaObraPorId (@PathVariable Long id){
        return "Obra por Id " + id;

    }
    @GetMapping("/obra/{exposicaoId}")
    public String ConsultaObraPorExposicao (@PathVariable Long exposicaoId) {
        return "Obra por exposicao: " + exposicaoId;
    }
}
