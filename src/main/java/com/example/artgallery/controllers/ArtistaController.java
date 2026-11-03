package com.example.artgallery.controllers;

import com.example.artgallery.DTO.ArtistaRequest;
import com.example.artgallery.DTO.ArtistaResponse;
import com.example.artgallery.DTO.AtualizaArtistaRequest;
import com.example.artgallery.entities.Artista;
import com.example.artgallery.repository.ArtistaRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artistas")
@Tag(name = "Artista Controller", description = "Classe responsável por fazer o controle do CRUD de Artistas!")

public class ArtistaController {

    @Autowired
    private  ArtistaRepository artistaRepository;

    @GetMapping
    @Operation(summary = "Listar Todos os Artistas", description = "Método que consulta e retorna uma lista com todos os artistas cadastrados no banco.")
    public List<Artista> getArtistas() {

        return artistaRepository.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Artista por ID", description = "Método que consulta e retorna um artista localizado pelo ID na URL, ou retorna o status de não encontrado caso o registro não exista.")    public ResponseEntity<Artista> artistaPorId(@PathVariable Long id) {
        return artistaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Cadastrar Novo Artista", description = "Método que realiza o cadastro de um novo artista, preenchendo seus dados com base no DTO recebido e salvando o novo registro no banco.")
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
    @Operation(summary = "Atualizar Artista por ID", description = "Método que atualiza os dados de um artista existente localizado pelo ID na URL, recebendo as novas informações via DTO e salvando as alterações no banco.")
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