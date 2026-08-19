package com.example.artgallery.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exposicao")

public class ExposicaoController {

    @GetMapping
    public String ConsultaExposicao (){
        return "Hello World!";
    }

    @GetMapping ("/{id}")
    public String ConsultaExposicaoPorId (@PathVariable Long id) {
        return "Exposicao por id " + id;

    }

    @GetMapping("exposicao/{artistaId}")
    public String ConsultaExposicaoPorArtista(@PathVariable Long artistaId){
        return "Artista por Id: " + artistaId;
    }
}
