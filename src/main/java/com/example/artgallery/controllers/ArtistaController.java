package com.example.artgallery.controllers;

import com.example.artgallery.DTO.ArtistaRequest;
import com.example.artgallery.DTO.ArtistaResponse;
import com.example.artgallery.DTO.AtualizaStatusUsuarioRequest;
import com.example.artgallery.DTO.UsuarioResponse;
import com.example.artgallery.entities.Artista;
import com.example.artgallery.entities.Usuario;
import com.example.artgallery.repository.ArtistaRepository;
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
    public ResponseEntity<ArtistaResponse> cadastrarArtista(@RequestBody ArtistaRequest request) {

        Artista artista = new Artista();
        artista.setNome(request.getNome());
        artista.setCpf(request.getCpf());
        artista.setNacionalidade(request.getNacionalidade());

        artista = artistaRepository.save(artista);

        return ResponseEntity.ok(
                new ArtistaResponse(artista.getId(), "Artista cadastrado com sucesso!")
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArtistaResponse> atualizarArtista(
            @PathVariable Long id,
            @RequestBody ArtistaRequest request) {

        Artista artista = artistaRepository.findById(id).orElse(null);

        if (artista != null) {
            artista.setNome(request.getNome());
            artista.setCpf(request.getCpf());
            artista.setNacionalidade(request.getNacionalidade());
            artista.setStatus();

            artistaRepository.save(artista);

            return ResponseEntity.ok(
                    new ArtistaResponse(artista.getId(), "Artista atualizado com sucesso!")
            );
        }

        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<UsuarioResponse>
    AtualizarStatus(@PathVariable Long id, @RequestBody AtualizaStatusUsuarioRequest usuarioRequest){
        //Consulta no banco
        Usuario usuarioBanco = new Usuario();

        if(usuarioBanco != null){
            usuarioBanco.setStatus(usuarioRequest.getStatus());

            return ResponseEntity.ok(new UsuarioResponse(usuarioBanco.getId(),
                    "Status atualizado com sucesso!"));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarArtista(@PathVariable Long id) {

        Artista artista = artistaRepository.findById(id).orElse(null);

        if (artista != null) {
            artistaRepository.delete(artista);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}