package com.example.artgallery.controllers;

import com.example.artgallery.DTO.ArtistaRequest;
import com.example.artgallery.DTO.ArtistaResponse;
import com.example.artgallery.DTO.AtualizaArtistaRequest;
import com.example.artgallery.entities.Artista;
import com.example.artgallery.repository.ArtistaRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artistas")
public class ArtistaController {

    private final ArtistaRepository artistaRepository;

    public ArtistaController(ArtistaRepository artistaRepository) {
        this.artistaRepository = artistaRepository;
    }

    @GetMapping
    public List<Artista> getArtistas() {
        return artistaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Artista> artistaPorId(@PathVariable Long id) {
        return artistaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ArtistaResponse> cadastrarArtista(
            @Valid @RequestBody ArtistaRequest request) {

        Artista artista = new Artista();
        artista.setNome(request.getNome());
        artista.setCpf(request.getCpf());
        artista.setNacionalidade(request.getNacionalidade());

        Artista artistaSalvo = artistaRepository.save(artista);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ArtistaResponse(
                        artistaSalvo.getId(),
                        "Artista cadastrado com sucesso!"
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArtistaResponse> atualizarArtista(
            @PathVariable Long id,
            @Valid @RequestBody AtualizaArtistaRequest request) {

        Artista artista = artistaRepository.findById(id).orElse(null);

        if (artista == null) {
            return ResponseEntity.notFound().build();
        }

        artista.setNome(request.getNome());
        artista.setCpf(request.getCpf());
        artista.setNacionalidade(request.getNacionalidade());

        Artista artistaAtualizado = artistaRepository.save(artista);

        return ResponseEntity.ok(
                new ArtistaResponse(
                        artistaAtualizado.getId(),
                        "Artista atualizado com sucesso!"
                )
        );
    }
}