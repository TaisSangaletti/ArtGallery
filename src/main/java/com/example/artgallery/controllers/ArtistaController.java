package com.example.artgallery.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Artista")

public class ArtistaController {

    @GetMapping
    public String ConsultaArtista (){
        return "Hello World!";
    }

    @GetMapping("/{id}")
    public String ConsultaArtistaPorId(@PathVariable Long id){
        return "Usuario por ID" + id;

    }
    @GetMapping("/empresa/{exposicaoId}")
    public String ConsultaArtistaPorExposicao(@PathVariable Long empresaId){
        return "Empresa por exposicao: "+ empresaId;
    }
}